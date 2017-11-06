package com.st.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

public class JDBCUTilSingle {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/student";
	private static String username = "root";
	private static String password = "root";
	private static Connection conn = null;
	private static JDBCUTilSingle jdbcuTilSingle = null;

	private JDBCUTilSingle() {

	}

	public static JDBCUTilSingle getJdbcuTilSingle() {
		if (jdbcuTilSingle == null) {
			synchronized (JDBCUTilSingle.class) { // 家同步锁，阻止线程并发
				jdbcuTilSingle = new JDBCUTilSingle(); // 懒汉式加载
			}
		}
		return jdbcuTilSingle;

	}
	//获取连接
	public Connection getConnection(){
		try {
			conn = DriverManager.getConnection(url, username, password);
//			if (conn!=null) {
//				System.out.println("连接成功！");
//				
//			}
			
		} catch (Exception e) {
			
		}
		return conn;
		
	}
	public void closeConnection(ResultSet rs,Statement st,Connection conn){
		try {
			if (rs != null) {
				rs.close();				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (st!=null) {
					st.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}finally{
				try {
					if (conn!=null) {
						conn.close();
						
					}
					
				} catch (Exception e3) {
					e3.printStackTrace();
				}					
			}
		}						
	}
	public static void main(String[] args) {
		JDBCUTilSingle.getJdbcuTilSingle().getConnection();
	}
}
