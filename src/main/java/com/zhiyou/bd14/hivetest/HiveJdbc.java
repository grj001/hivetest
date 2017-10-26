package com.zhiyou.bd14.hivetest;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//用jdbc的方式连接的server, 发送sql到hive上执行
public class HiveJdbc {

	public static final String DRIVE_CLASS = 
			"org.apache.hive.jdbc.HiveDriver";
	
	public static final String URL = 
			"jdbc:hive2://192.168.58.128:10000";
	
	public static final String USERNAME = 
			"root";
	
	public static final String PASSWORD = 
			"root";
	
	public static void main(String[] args) throws Exception{
		Class.forName(DRIVE_CLASS);
		Connection connection = 
				DriverManager
				.getConnection(URL,USERNAME,PASSWORD);
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		
		statement.execute("use bd14");
		statement.execute("drop table table_from_java");
		stringBuilder.append("create table if not exists table_from_java (");
		stringBuilder.append("test1 string");
		stringBuilder.append(", test2 int");
		stringBuilder.append(", test3 string");
		stringBuilder.append(") stored as textfile");
		System.out.println(stringBuilder);
		boolean execute = 
				statement.execute(stringBuilder.toString());
		
		ResultSet resultSet = statement.executeQuery("show tables");
		
		while(resultSet.next()){
			System.out.println(resultSet.getString(1));
		}
		statement.close();
		connection.close();
		
	}
	
}























