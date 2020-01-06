import java.io.*;
import java.text.*;
import java.util.*;
import java.sql.*;
public class mainClass {

		public static void main(String args[]) {
			int intInsCode = -1;
			String insCode;
			boolean intOk;
			Scanner sc = new Scanner(System.in);
			do {
				intOk = true;
				System.out.print("Insert the code of the rental you want to delete:");
				insCode = sc.nextLine();
				try {
					intInsCode = Integer.parseInt(insCode);
				}catch (Exception e) {
					System.out.println("Please insert an Integer.");
					intOk = false;
				}
			}while (!intOk);
			String url = "jdbc:sqlserver://195.251.249.161;" +
		                   "databaseName=DB36;user=G536;password=26th5e985;";
			Connection dbcon ;
			Statement stmt;
		  	ResultSet rs;
			/* declare ODBC conectivity */
		  	try {Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");}
		  	catch(java.lang.ClassNotFoundException e)
		  	{System.out.print("ClassNotFoundException: ");
		  	System.out.println(e.getMessage());}
		  		/* execute SQL statements */
		  	try {
		  		dbcon = DriverManager.getConnection(url);
		  		stmt = dbcon.createStatement();
		  		String sql = "DELETE FROM Payments WHERE rental_code=" + intInsCode;  
		  	    stmt.executeUpdate(sql);
		  		sql = "DELETE FROM Rentals WHERE rental_code=" + intInsCode;  
		  	    stmt.executeUpdate(sql);
		  	    stmt.close();
		  	    dbcon.close();
		  	}
		  	catch(SQLException e)
		  	{
		  	System.out.print("SQLException: ");
		  	System.out.println(e.getMessage());}}



}