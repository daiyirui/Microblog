package com.microblog.dbutil;

import java.io.IOException;
import java.util.Properties;

public class Config {
	//step1:创建连接配置文件对象
   private static Properties prop=new Properties();
   static{
	   try {
		   //step2:加载dbconfig.properties配置文件
		prop.load(Config.class.getResourceAsStream("dbconfigs.properties"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   //step3:设置获取配置文件7个常量
   public static final String CLASS_NAME=prop.getProperty("CLASS_NAME");
   public static final String DATABASE_URL=prop.getProperty("DATABASE_URL");
   public static final String SERVER_IP=prop.getProperty("SERVER_IP");
   public static final String SERVER_PORT=prop.getProperty("SERVER_PORT");
   public static final String DATABASE_SID=prop.getProperty("DATABASE_SID");
   public static final String USERNAME=prop.getProperty("USERNAME");
   public static final String PASSWORD=prop.getProperty("PASSWORD");
   
}
