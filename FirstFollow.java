import java.util.*;
class FirstFollow
{
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		String prod[][]=new String[3][3];
		String first[]=new String[3];
		String follow[]=new String[3];
		String str,temp;
		int i,j,c,k;char ch,t;
		for(k=0;k<3;k++)
		{
			first[k]=follow[k]="";
			System.out.print("\n"+(char)(65+k)+" -> ");
			str=in.next();str+='/';temp="";
			for(i=0,c=0;i<str.length();i++)
			{
				if(str.charAt(i)!='/')
					temp+=str.charAt(i);
				else
				{
					prod[k][c++]=temp;
					temp="";
				}
			}
		}
		//Now we compute FIRST of the productions!!!
		for(i=2;i>0;i--)
		{
			for(j=0;j<3;j++)
			{
				if(prod[i][j]==null)
					break;
				if(prod[i][j].charAt(0)<123 && prod[i][j].charAt(0)>96)
					first[i]+=prod[i][j].charAt(0)+";";
			}
		}
		for(i=0,j=0;j<3;j++)
		{
			if(prod[i][j]==null)
				break;
			if(prod[i][j].charAt(0)<123 && prod[i][j].charAt(0)>96)
					first[i]+=prod[i][j].charAt(0)+";";
			else
			{
				k=(prod[i][j].charAt(0))-65;
				first[i]+=first[k];
			}
		}
		System.out.println(first[0]);
		System.out.println(first[1]);
		System.out.println(first[2]);
		//Now we compute FOLLOW of the productions!!!
		for(k=0;k<3;k++)
		{
			ch=(char)(65+k);
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					if(prod[i][j]==null)
						break;
					c=prod[i][j].indexOf(ch);
					if(c<0)
						continue;
					else if(c<prod[i][j].length()-1)
					{
						t=prod[i][j].charAt(c+1);
						if(t<123 && t>96)
							follow[k]+=(char)t+';';
						else
							follow[k]+=first[t-65];
					}
				}
			}
		}
		System.out.println(follow[0]);
		System.out.println(follow[1]);
		System.out.println(follow[2]);
	}
}