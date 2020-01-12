package com.caps.dev.JDBC;


import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCEVer3 {
	
	
	public static void main(String[] args) {
		Connection conn= null;
		ResultSet rs=null;
		Statement stmt=null;

				
				try {
					//Load the Driver
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("Driver Loaded...");
					
					//Get the Connection via Driver
					//version 3 of getConnection();
					String url="jdbc:mysql://127.0.0.1:123/caps_mumbai" + "?user=root&password=root";
					String path="C:/Users/Nisha/Desktop/dbver3Connection/db.properties";
					FileReader reader = new FileReader(path);
					Properties prop = new Properties ();
					prop.load(reader);
					
					conn=DriverManager.getConnection(url, prop);
					System.out.println("connection Established...");
					System.out.println("*************************************************");
					
					//Issue SQL Query via connection
					String query = "SELECT * FROM users_info";
					stmt = conn.createStatement();
					rs= stmt.executeQuery(query);
					System.out.println("Query Issued...");
					System.out.println("*******************************************");

					//Process the Results
					while(rs.next()) {
						int userid= rs.getInt("user_id");
						String username=rs.getString("username");
						String email= rs.getString("email");

						System.out.println(userid);
						System.out.println(username);
						System.out.println(email);
						System.out.println("*************************************");
					}
				}
				
				catch(Exception e) {
					e.printStackTrace();
				}
				finally {
					if(conn!=null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						if(stmt!=null) {
							try {
								stmt.close();
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



	
	
	
	
	
	
	


