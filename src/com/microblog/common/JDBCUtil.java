package com.microblog.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCUtil {
	
	private static String url="jdbc:mysql://127.0.0.1:3306/blog?useUnicode=true&characterEncoding=utf-8";
	private static String user = "root";
	private static String password = "123456";

	public static Connection getConn() {
		Connection connection = null;
		try {
		
			Class.forName("com.mysql.jdbc.Driver");
		
			connection = DriverManager.getConnection(url, user, password);
         
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeDB(Connection connection,Statement stat,ResultSet rs){
		
			try {
				if(null != connection)
				connection.close();
				if(null != stat)
					stat.close();
				if(null != rs)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
