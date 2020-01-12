package com.caps.dev.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

//This program is used to Insert the values in JDBC
public class JDBCDelete {

	public static void main(String[] args) {

		Connection conn=null;
		PreparedStatement pstmt= null;

		try {

			//load the driver
			java.sql.Driver div = new com.mysql.jdbc.Driver();

			DriverManager.registerDriver(div);
			System.out.println("Driver Loaded...");

			//Get the connection via Driver
			String dbUrl = "jdbc:mysql://Localhost:123/caps_mumbai";
			Scanner sc = new Scanner (System.in);
			System.out.println("Enter the username...");
			String user =  sc.nextLine();
			System.out.println("Enter the password...");
			String password=sc.nextLine();
			conn = DriverManager.getConnection(dbUrl, user, password);
			System.out.println("Connection Est...");
			System.out.println("*********************************");

			//Issue SQL Query via conn
			String query = "DELETE from users_info"
					+ " where user_id=?";	
			pstmt = conn.prepareStatement(query);
			System.out.println("Enter the user_id to be Deleted");
			int userid = Integer.parseInt(sc.nextLine());

			pstmt.setInt(1,userid);
			int i = pstmt.executeUpdate();

			//Process the Results..
			if(i>0) {
				System.out.println("Data Deleted...");
			}else {
				System.out.println("Failed to Delete the Data..");
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Close all the JDBC Objects
		finally {
			if(conn!=null) {
				try {
					conn.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}




















