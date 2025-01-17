package com.caps.dev.JDBC;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;

public class JDBCCallable {

	public static void main(String[] args) {

		Connection conn = null;
		CallableStatement cstmt= null;
		ResultSet rs = null;


		String url = "jdbc:mysql://Localhost:123/caps_mumbai"
				+ "?user=root&password=root";

		try {

			conn=DriverManager.getConnection(url);
			System.out.println("Conn Est...");

			String query = "CALL SP1()";
			cstmt = conn.prepareCall(query);
			boolean b = cstmt.execute();

			if(b) {

				rs=cstmt.getResultSet();
				while(rs.next()) {
					int userid= rs.getInt("user_id");
					String username=rs.getString("username");
					String email= rs.getString("email");

					System.out.println(userid);
					System.out.println(username);
					System.out.println(email);
					System.out.println("*************************************");
				}
			}else{
				int i =cstmt.getUpdateCount();

				if(i>0)
				{
					System.out.println("Query ok ,"+i+" Row changed.. ");
				}
			}
		}catch(SQLException e) {

		}
		finally {
			if(conn!=null) {
				try {
					conn.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				if(cstmt!=null) {
					try {
						cstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(rs!=null) {
						try {
							rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}

			}
		}

	} 

}
