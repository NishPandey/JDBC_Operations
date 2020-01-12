package com.caps.dev.JDBC;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;

public class JDBCIDRetrieval {

	public static void main(String[] args) {
		Connection conn = null;
		ResultSet rs=null;
		Statement stmt=null;

		try {

			// Load the Driver
			java.sql.Driver div = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(div);
			System.out.println("Driver Loaded....");

			//Get DB connection via driver
			String url="jdbc:mysql://localhost:123/caps_mumbai" + "?user=root&password=root";
			conn = DriverManager.getConnection(url);
			System.out.println("connection Established...");
			System.out.println("*************************************************");

			//Issue SQL Query via connection
			String query = "SELECT * FROM users_info";
			stmt = conn.createStatement();
			rs= stmt.executeQuery(query);
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the user_id:");
			int id = Integer.parseInt(sc.nextLine());

			//Process the Results
			while(rs.next()) {
				if(rs.getInt("user_id")==id)
				{
					int userid= rs.getInt("user_id");
					String username=rs.getString("username");
					String email= rs.getString("email");

					System.out.println(userid);
					System.out.println(username);
					System.out.println(email);
					System.out.println("*************************************");
				}
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		//close all the jdbc object
		finally {
			if(conn!=null) {
				try {
					conn.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
				if(stmt!=null) {
					try {
						stmt.close();
					} 
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(rs!=null) {
					try {
						rs.close();
					} 
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

		}

}


