package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	/*static String db_url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test";
	static String db_user = "sa";
	static String db_pass = "Gg56997951";
	
	public static Connection getConn () {
		Connection conn = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//載入驅動
			conn = DriverManager.getConnection(db_url,db_user,db_pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}*/
	
	
	/*static String db_url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test";
	static String db_user = "sa";
	static String db_password = "Gg56997951";
	
	public static Connection getConn () throws SQLException {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (Exception e) {
			
			e.printStackTrace();
		}//載入驅動
		Connection conn = DriverManager.getConnection(db_url,db_user,db_password);
		
		return conn;
		
	}*/
	
	
	public static String db_url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test";
	public static String db_user = "sa";
	public static String db_pass = "Gg56997951";
	
	public static Connection getConn () {
		Connection conn = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//載入驅動
			conn = DriverManager.getConnection(db_url, db_user, db_pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * 關閉連線
	 * @param state
	 * @param conn
	 */
	public static void close (Statement state, Connection conn) {
		if (state != null) {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close (ResultSet rs, Statement state, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (state != null) {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
