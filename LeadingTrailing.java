import java.util.*;
class LeadingTrailing
{
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		int n=in.nextInt(),i=0,c=0,j,k,x;
		char symb[]=new char[n];String str;
		String prod[]=new String[n];
		String lead[]=new String[n];
		String trail[]=new String[n];
		for(i=0;i<n;i++)
		{
			str=in.next();
			symb[i]=str.charAt(0);
			prod[i]=str.substring(3);
		}
		i=0;
		while(i<n)				//To compute LEAD!
		{
			str=prod[i];
			x=str.indexOf(symb[i])+1;
			if(x<str.length())
				lead[c]+=str.charAt(x)+'\t';
			for(j=i+1;j<n;j++)
			{
				if(symb[i]!=symb[j])
					break;
				str=prod[j];
				for(k=0;k<str.length();k++)
				{
					if(str.charAt(k)>90||str.charAt(k)<65)
						lead[c]+=str.charAt(k)+'\t';
				}
			}
			c++;i=j;
		}
		i=0;c=0;
		for(i=0;i<=c;i++)
			System.out.println(lead[i]);
		/*while(i<n)
		{

		}*/
	}
}