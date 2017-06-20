import java.util.*;
import java.io.*;

class SLR
{
    public char[] expression; 
    public int position; 
    public Stack stack;
    public Grammar G;
    public Alphabet alphabet;
    public int[][] LRShiftTable;
    int terminal;
    public int[][] LRReduceTable;
    
    public SLR(String exp, Grammar grammar)
    {
		G = grammar;
		alphabet = G.alphabet;
		try
		{
		    InfoDFA a = LRAutomaton().toInfoDFA();
		    LRShiftTable = LRShiftTable(a);
		    LRReduceTable = LRReduceTable(a);
		    terminal = a.terminal;
		}
		catch(Exception e)
		{
		    System.out.print(e);
		    System.exit(1);
		}
		expression = exp.toCharArray();
		stack = new Stack();
		stack.push(new Integer(0));
    }
    public void advance()
    {
		position++;
    }
    
    public short current()
    {
		while(expression[position] == ' ')
	   		advance();
		char c = expression[position];
		return (Character.isDigit(c))? 
	    alphabet.toShort('c') : alphabet.toShort(c);
    }
    public void push(short c)
    {
		Integer p = (Integer) stack.peek();
		int q = LRShiftTable[p.intValue()][c];
		stack.push(new Integer(q));
    }
    void reduce(int n)
    {
		Production[] P = G.productionsArray;
		short[] right = alphabet.toShort(P[n].right);
		int le = right.length;
		for(int i = 0; i < le; i++)
		    stack.pop();
		short v = alphabet.toShort(P[n].left);
		push(v);
   	 }
    public InfoNFA LRAutomaton()
    {
		InfoNFA a = new InfoNFA(G.lgProductions + G.nbProductions, G.alphabet);
		int[] initState=new int[G.nbProductions];
		for(int i = 0; i < a.nbStates; i++)
		    a.info[i] = -1;
		    //create the paths along right sides
		for(int i = 0, p = 0; i < G.nbProductions; i++)
		{  
		    String s = G.productionsArray[i].right;
			initState[i] = p;
			int j;
		    for(j = 0; j < s.length(); j++)
		    {
				char[] c = {s.charAt(j)};
				a.next[p + j].add(new HalfEdge(new String(c), p + j + 1));
	    	}
	    //the value of info indicates the rule to be used for reduction.
	    	a.info[p + j] = i;
			if(G.initial == i)
			a.terminal = p+j;
	    	p = p + j + 1;
		}
	//add the epsilon transitions
		for(int i = 0; i < G.nbProductions; i++){   
	    	short[] r = alphabet.toShort(G.productionsArray[i].right);
		    int p = initState[i];
		    for(int j = 0; j < r.length; j++)
		    {
				Set s = G.derive[r[j]];
				for(Iterator k = s.iterator(); k.hasNext(); )
				{
				    Integer I = (Integer) k.next();
				    a.next[p+j].add(new HalfEdge("", initState[I.intValue()]));
				}
	    	}
		}
		a.initial.add(new HalfEdge(initState[G.initial]));
		return a;
    }
	
	public  int[][] LRShiftTable(InfoDFA a)
	{
		int[][] table = new int[a.nbStates][a.nbLetters];
		for(int i = 0; i < a.nbStates; i++)
		for(char c = 0; c < a.nbLetters; c++)
		{
			int q = a.next[i][c];
			if(q == a.sink)
			    table[i][c] = -1;
			else
			    table[i][c] = q;
		}
		return table;
    }
    public  int[][] LRReduceTable(InfoDFA a) throws Exception
    {
		int[][] table = new int[a.nbStates][a.nbLetters];
		for(int i = 0; i < a.nbStates; i++)
	    for(short c = 0; c < a.nbLetters; c++)
	    {
			int n = a.info[i];
			if(n >= 0)
			{   //  reduction by production n.
			    Production p = G.productionsArray[n];
			    short v = G.variables.toShort(p.left);
			    if(G.follow(v).contains(new Short(c)))
			    {
					if(LRShiftTable[i][c] != -1)
					    throw new Exception("Grammar not SLR: S/R conflict");
					table[i][c] = n;
			    }
			    else
				table[i][c] = -1;
			}
			else 
			    table[i][c] = -1;
	    }
	return table;
    }
    void lRParse() throws Exception
    {
		System.out.println(stack);
		while(true)
		{
		    short c = current();
		    int p = ((Integer) stack.peek()).intValue();
		    int q = LRShiftTable[p][c];
		    int r = LRReduceTable[p][c];
		    if(q != -1)
		    {
				stack.push(new Integer(q));
				advance();
				System.out.println(stack);
				if(c == alphabet.toShort('$'))
			    if( q == terminal)
			    {
					System.out.print("input accepted\n");
						break;
			    }
			    else
				throw new Exception("end of input reached");
		    }
		    else if (r >= 0)
		    {
			    reduce(r);
			    System.out.println(stack);
		    } 
		    else
				throw new Exception("syntax error"); 
		}
	}
    static void printTable(String s,int[][] table)
    {
		System.out.println(s);
		for(int i = 0; i < table.length; i++)
		{
		    System.out.print(i+" ");
		    for(int j = 0; j < table[i].length; j++)
				System.out.print(table[i][j]+" ");
		    System.out.println();
		}
    }
    static void printTable(String s, int[] table)
    {
		System.out.println(s);
		for(int i = 0; i < table.length; i++)
		    System.out.print(table[i]+" ");
		System.out.println();
   	}
    
    public static void main(String[] args) throws IOException
    {
		String exp = args[0]+'$';
		SLR input = new SLR(exp, Grammar.fromFile(args[1]));
		printTable("shift",input.LRShiftTable);
		printTable("reduce",input.LRReduceTable);
		try
		{
		    input.lRParse();
		}
		catch(Exception e)
		{
		    System.out.println(e);
		    System.exit(1);
		}
    }
}