import java.util.*;
class SlrParser
{
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		int n=in.nextInt(),
		String prod[][]=new String[n][3];
		String str,temp="";
		for(i=0;i<n;i++)
		{
			str=in.next();
			for(j=0,c=0;j<str.length();j++)
			{
				if(str.charAt(j)!=32)
					temp+=str.charAt(j);
				else
				{
					temp='.';
					prod[i][c++]=temp;
					temp="";
				}
			}
		}

	}
}