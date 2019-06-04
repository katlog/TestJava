
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
			Class.forName("oracle.jdbc.driver.OracleDriver");
			pro = new Properties();
			in = ClassLoader.getSystemResourceAsStream("db.properties");
			pro.load(in);
			
			url = pro.getProperty("url");
			username = pro.getProperty("username");
			password = pro.getProperty("password");
		} catch (IOException | ClassNotFoundException e) {
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
	
	private String getSqlStr(int count,int times,String startTime,String endTime){
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ( ");
		sql.append(" SELECT ROWNUM AS ID, T.USER_NAME,T.CARD_NUM,T.AREA_CODE,");
		sql.append(" CASE WHEN T.NEW_HETONG_NUM IS NULL THEN T.HETONG_NUM ELSE T.NEW_HETONG_NUM END AS CONTRACT_NUM,");
		sql.append(" T.ID as FANGDAI_ID,");
		sql.append("(select  userinfo.id from WIWJ_JR_USER userinfo where userinfo.user_name = t.user_name and userinfo.credentials_no = t.card_num and rownum = 1 ) as USER_ID,");
		sql.append(" T.START_RENT_DATE as GMT_REPAYMENT_DATE,");
		sql.append(" '2' AS DATA_TYPE, ");
		sql.append(" (ADD_MONTHS(T.START_RENT_DATE,T.ZUQI)-1) AS GMT_HETONG_END ");
		sql.append(" FROM WIWJ_FANGDAI T");
//		sql.append(" INNER JOIN WIWJ_JR_USER USERINFO");
//		sql.append(" ON USERINFO.USER_NAME = T.USER_NAME AND USERINFO.CREDENTIALS_NO = T.CARD_NUM");
		sql.append(" WHERE T.STATUS IN ('6','10') ");
//		sql.append(" AND NOT EXISTS (SELECT ATT.USER_ID FROM JR_ZUKE_ATTENTION ATT WHERE USERINFO.ID = ATT.USER_ID)");
		sql.append(" AND NOT EXISTS (SELECT DANGER.FANGDAI_ID FROM WIWJ_ZHIMA_DANGER_LIST_DETAIL DANGER WHERE T.ID = DANGER.FANGDAI_ID AND DANGER.ZHIMA_DANGER_STATUS='10007001200010002')");
		//sql.append(" AND NOT EXISTS (SELECT TUIZU.FANGDAI_ID from WIWJ_JR_ZHONGTUI TUIZU WHERE T.ID = TUIZU.FANGDAI_ID)");
		sql.append(" AND T.RESERVED_FILED2 IS NULL AND (T.RESERVED_FILED3 IS NULL OR T.RESERVED_FILED3 = 'Y') ");
		sql.append(" AND T.START_RENT_DATE >= to_timestamp('");
		sql.append(startTime);
		sql.append("','YYYY-MM-DD') ");
		sql.append(" AND T.START_RENT_DATE <= to_timestamp('");
		sql.append(endTime);
		sql.append("','YYYY-MM-DD') ");
		sql.append("  ORDER BY T.GMT_CREATE ) TEMP");
		sql.append(" WHERE TEMP.ID BETWEEN ");
		sql.append((1+(times*count)));
		sql.append(" AND ");
		sql.append((times+1)*count);
		
		return sql.toString();
	}
	
	
	public void JDBC30() throws SQLException{
		Statement statement2 = connection.createStatement();
		statement2.executeUpdate("sql", Statement.RETURN_GENERATED_KEYS);
		ResultSet keys = statement2.getGeneratedKeys();
		if (keys.next()) {
			int key = keys.getInt(0);
		}
	}
}
