import java.io.*;
class LexAnalyser
{
	public static void main(String args[])throws IOException
	{
		File f=new File("data.txt");
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String str=br.readLine(),temp="";
		int i;char ch;
		while(str!=null)
		{
			str+=' ';
			for(i=0;i<str.length();i++)
			{
				ch=str.charAt(i);
				if(ch!=32)
					temp+=ch;
				else
				{
					check(temp);
					temp="";
				}
			}
			str=br.readLine();
		}
	}
	static void check(String str)
	{
		if(str.length()==1)
		{
			char ch=str.charAt(0);
			if(ch==','||ch=='|'||ch==';'||ch=='('||ch==')'||ch=='{'||ch=='}'||ch=='['||ch==']'||ch=='\''||ch=='\"'||ch=='?')
				System.out.println("Separator: "+ch);
			else if(ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='%')
				System.out.println("Mathematical Operator: "+ch);
			else if(ch=='^'||ch=='~')
				System.out.println("Bitwise Operator: "+ch);
			else if(ch=='>'||ch=='<')
				System.out.println("Relational Operator: "+ch);
			else if(ch=='=')
				System.out.println("Assignment Operator: "+ch);
			else
				System.out.println("Identifier: "+ch);
		}
		else
		{
			if(str.equals("++")==true||str.equals("--")==true||str.equals("+=")==true||str.equals("-=")==true||str.equals("*=")==true||str.equals("/=")==true||str.equals("%=")==true)
				System.out.println("Shorthand operators: "+str);
			else if(str.equals(">=")==true||str.equals("<=")==true||str.equals("==")==true||str.equals("!=")==true)
				System.out.println("Relational Operator: "+str);
			else if(str.equals("&&")==true||str.equals("||")==true)
				System.out.println("Logical Operator: "+str);
			else if(keyCheck(str)==true)
				System.out.println("KeyWord/Reserved Word: "+str);
			else
				System.out.println("Identifier: "+str);
		}
	}
	static boolean keyCheck(String str)
	{
		String arr[]={"int","double","float","void","class","public","static","if","else","for","while","do"};
		int i;
		for(i=0;i<12;i++)
		{
			if(str.equals(arr[i])==true)
				return true;
		}
		return false;
	}
}