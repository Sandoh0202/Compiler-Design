import java.util.*;
class REtoNFA
{
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		String arr[][]=new String[10][2];
		String str=in.next(),s1="",s2="",s3="",s4="";
		int i,j,k,x;
		for(i=0;i<10;i++)
		{
			arr[i][0]="";
			arr[i][1]="";
		}
		i=str.indexOf('(');
		s1=str.substring(0,i++);
		j=str.indexOf(')');
		k=str.indexOf('|');
		s2=str.substring(i,k++);
		s3=str.substring(k,j++);
		s4=str.substring(j);
		System.out.println("Symbol\ta\tb");
		for(i=0,k=0;i<s1.length();i++)
		{
			j=s1.charAt(i);
			if(j==97||j==98)
				arr[k][j-97]+=(char)(++k+65);
			else
			{
				j=s1.charAt(i-1);
				arr[k][j-97]+=(char)(k+65);
			}
		}
		for(i=0,x=k-1;i<s2.length();i++)
		{
			j=s2.charAt(i);
			if(j==97||j==98)
				arr[k][j-97]+=(char)(++k+65);
		}
		j=s3.charAt(0);
		arr[x][j-97]+=(char)(k+65);
		for(i=1;i<s3.length();i++)
		{
			j=s3.charAt(i);
			if(j==97||j==98)
				arr[k][j-97]+=(char)(++k+65);
		}
		for(i=0;i<s4.length();i++)
		{
			j=s4.charAt(i);
			if(j==97||j==98)
				arr[k][j-97]+=(char)(++k+65);
		}
		for(i=0;i<=k;i++)
		{
			System.out.print((char)(65+i)+":\t");
			for(j=0;j<2;j++)
			{
				if(arr[i][j]=="")
					System.out.print("*\t");
				else
					System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
	}
}