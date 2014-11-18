package dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MainClass{
	
	Connection conn;
	public MainClass(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javachat", "root", "");
	
		}catch(Exception e){System.out.println("hata : "+ e);}
	}
	
	

	
	
	public static void main(String args []){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javachat", "root", "");
			
			firstOpenFrame a=new firstOpenFrame(conn);
			
			}catch(Exception e){System.out.println("hata : "+ e);}
	}
	



	
}
