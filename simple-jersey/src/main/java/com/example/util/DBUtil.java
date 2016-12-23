package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	static Connection conn = null;
	
	/**
	 * get Connection
	 * 
	 * @return opening Connection or a new Connection
	 * 
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {

    	final String url = "jdbc:hsqldb:mem:mymemdb";
    	final String username = "jersey";
    	final String password = "jersey";
    	
		try {
			
			if (null == conn || conn.isClosed()) {
				conn = DriverManager.getConnection(url, username, password);
			}
		} catch (SQLException se) {
			se.printStackTrace();
			throw se;
		}
		return conn;
	}
	
	/**
	 * close Connection if Connection is opening
	 * 
	 */
	public static void closeConnection() {
		
		try {
			if (null != conn && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
