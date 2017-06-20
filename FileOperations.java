import java.io.*;
import java.util.*;
class FileOperations
{
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		File f=new File("data.txt");
		File tmp=new File("temp.txt");
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		PrintWriter pw=new PrintWriter(new FileOutputStream(f),true);
		int ch,line;String str="";
		while(true)
		{
			System.out.print("\nOperations-\n1.Add\n2.Remove\nChoice: ");
			ch=in.nextInt();
			if(ch==0)
				break;
			switch(ch)
			{
				case 1:	System.out.print("\nEnter append: ");
						str=in.nextInt();
						pw.println(str);
						break;
				case 2: System.out.print("\nEnter line to be removed: ");
						line=in.nextInt();
						PrintWriter pw=new PrintWriter(new FileOutputStream(tmp),true);
						while(true)
						{
							str=br.readLine();
							if(--line!=0)
								pw.println(str);
						}
						PrintWriter pw=new PrintWriter(new FileOutputStream(f),true);
						BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(tmp)));
						while(true)
						{
							str=br.readLine();
							if(str==null)
								break;
							pw.println(str);
						}
						break;
			}
		}
	}
}