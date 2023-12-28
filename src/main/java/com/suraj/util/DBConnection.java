package com.suraj.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/onlinestore", "root", "Suraj123@");
		} catch (Exception e){
			System.out.println(e);			
		}
	
		return conn;
	}
	
}
