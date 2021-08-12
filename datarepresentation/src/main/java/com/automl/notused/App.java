package com.automl.notused;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String args[]) {
		
		String url = "jdbc:mysql://localhost:3306/mydb";
		String user = "root";
		String password = "";
		final String DATABASE_STRUCTURE =	"SELECT \r\n" + 
											"    TABLE_NAME, \r\n" + 
											"    COLUMN_NAME, \r\n" + 
											"    CONSTRAINT_NAME, \r\n" + 
											"    REFERENCED_TABLE_NAME, \r\n" + 
											"    REFERENCED_COLUMN_NAME \r\n" + 
											"FROM \r\n" + 
											"    INFORMATION_SCHEMA.KEY_COLUMN_USAGE \r\n" + 
											"WHERE \r\n" + 
											"	REFERENCED_TABLE_SCHEMA = 'mydb' ";
		try {
			
			Connection con = DriverManager.getConnection(url, user, password);
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(DATABASE_STRUCTURE);
			
			System.out.println(	"TABLE_NAME, COLUMN_NAME, CONSTRAINT_NAME, REFERENCED_TABLE_NAME, REFERENCED_COLUMN_NAME ");
						
			while (rs.next()) {
				
				System.out.println(	rs.getString(1) + "  " + 
									rs.getString(2) + "  " + 
									rs.getString(3) + "  " + 
									rs.getString(4) + "  " + 
									rs.getString(5));
			}
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
