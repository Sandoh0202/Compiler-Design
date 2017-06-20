import java.util.*;
class SrParser
{
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		int i,result=0,c=0;
		String stack[]=new String[50];
		String str=in.next();
		char ch,sign='+';str+='$';
		System.out.println("STACK\t\t\tSTRING");
		for(i=0;i<str.length()-1;i++)
		{
			stack[i]="";
			ch=str.charAt(i);
			if(ch<58 && ch>47)
			{
				if(i>0)
					stack[i]=stack[i-1];
				stack[i]+="id";
				System.out.println(stack[i]+"\t\t\t"+str.substring(i+1));
				if(sign=='+')
					result+=(ch-48);
				else if(sign=='*')
					result*=(ch-48);
			}
			else
			{
				sign=ch;
				stack[i]=stack[i-1];
				stack[i]+=sign;
				System.out.println(stack[i]+"\t\t\t"+str.substring(i+1));
			}
		}
		System.out.println("Result: "+result);
	}
}