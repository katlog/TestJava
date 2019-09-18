
/**  
 * @Title: Temp.java
 * @Package: org.temp.pack
 * @Description: TODO
 * @author: chensl
 * @date: 2017年2月9日 下午3:56:37
 * @version: V1.0  
 */ 
package org.person.dfw.jdbc;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * @moudle: Temp 
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年2月9日 下午3:56:37
 *
 */
@SuppressWarnings("static-access")
public class TestSQL {
	
	private static Properties pro;

	private static String url;
	private static String username;
	private static String password;
	
	private  Connection connection;
	private  Statement statement;
	private  ResultSet resultSet;
	private  PreparedStatement preparedStatement;
	static{
		InputStream in = null;
		try {
			pro = new Properties();
			in = ClassLoader.getSystemResourceAsStream("db.properties");
			pro.load(in);

			url = pro.getProperty("url");
			username = pro.getProperty("username");
			password = pro.getProperty("password");

			// oracle
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection oracleCon = DriverManager.getConnection(url, username, password);

			// mysql
			Class.forName("com.mysql.jdbc.Driver");
			Connection mysqlCon = DriverManager.getConnection(url, username, password);

			// sqlite
			Class.forName("org.sqlite.JDBC");
			Connection sqliteCon = DriverManager.getConnection("jdbc:sqlite:./config/config.db");


		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Connection getConnection(){
		try {
			if (connection==null||connection.isClosed()) {
				connection = DriverManager.getConnection(url, username, password);
				return connection;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return connection;
	}
	
	
	public ResultSet query(String sql,List<Object> params){
		try {
			getConnection();
			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement(sql);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return resultSet;
	}
	
	
	@Test
	public void testSql(){
		
		try {
			getConnection();
			DatabaseMetaData metaData = connection.getMetaData();
			Statement statement = connection.createStatement();
			
//			String sql = getSqlStr(1, 2, "2016-11-11", "2016-12-12");
			String sql = "select * from wiwj_jr_user";
			
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				System.out.println(resultSet.getObject(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			
		}
	}

	// 自增主键
	public void JDBC30() throws SQLException{
		Statement statement2 = connection.createStatement();
		statement2.executeUpdate("sql", Statement.RETURN_GENERATED_KEYS);
		ResultSet keys = statement2.getGeneratedKeys();
		if (keys.next()) {
			int key = keys.getInt(0);
		}
	}
}
