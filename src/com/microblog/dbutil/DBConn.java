package com.microblog.dbutil;

import java.sql.*;

public class DBConn {
   //三大属性，四大方法
	//三大核心接口(属性)
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	//4方法	
	//method1:创建数据库连接
	public void getConnection(){		
		try {
			//step1:加载连接驱动，java反射原理
			Class.forName(Config.CLASS_NAME);
			//step2:创建connection接口对象，用于获取mysql数据库连接对象。三个参数 url连接的字符串，username账号，password密码
			String url="jdbc:mysql://127.0.0.1:3306/blog?useUnicode=true&characterEncoding=utf-8";
			//step3:创建数据库连接
			conn=DriverManager.getConnection(url,Config.USERNAME,Config.PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//method2:关闭数据库方法
    public void closeConn(){
    	//关闭顺序：ResultSet PreparedStatement Connection    	
    	//关闭：ResultSet
    	if(rs!=null){
    		try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	//关闭：PreparedStatement
    	if(pstmt!=null){
    		try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}    	
    	//关闭：Connection
    	if(conn!=null){
    		try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }	
    //method3:用于发送 增、删、改 的方法
    public int execOther(final String sql,final Object[] params){
    	//step1:获取数据库连接
    	this.getConnection();	
    	try {
    		//step3:创建Statement接口对象
			pstmt=conn.prepareStatement(sql);
			//step4:动态为pstmt对象赋值参数
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
			//step5:使用Statement对象发送sql语句
			int affectedRows=pstmt.executeUpdate();
			 //step6:返回结果
			return affectedRows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}    	
    }
    //method4:用于发送 查询结果 的方法
    public ResultSet execQuery(final String sql,final Object[] params){
    	//step1:获取数据库连接
    	this.getConnection();   	
    	try {
    		//step3:创建PreparedStatement接口对象
			pstmt=conn.prepareStatement(sql);
			//step4:动态为pstmt对象赋值参数
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
			//step5:使用Statement对象发送sql语句
			rs=pstmt.executeQuery();
			//step6:返回结果
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		        	
    }
  
}
