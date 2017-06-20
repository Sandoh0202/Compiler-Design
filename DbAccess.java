import java.sql.*;
import java.util.*;
class DbAccess
{
	public void work()throws ClassNotFoundException
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=rootpassword"); 
			String str="words";
			String query="CREATE DATABASE "+str;
			Statement s=con.createStatement();
			int r=s.executeUpdate(query);			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String args[])throws ClassNotFoundException
	{
		DbAccess o=new DbAccess();
		o.work();
	}
}