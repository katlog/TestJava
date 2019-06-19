package dfw.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import java.io.File;


/**
 * @moudle: TestHibernate 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年12月21日 下午4:01:23
 *
 */
public class TestHibernate {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		System.out.println(TestHibernate.class.getResource("hibernate.cfg.xml").toString());
		try {
			File file = new File(TestHibernate.class.getResource("hibernate.cfg.xml").toString()); 
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure("/org/person/dfw/hibernate/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void main(String[] args) {
		
		
	}
	
	@Test public void schemaExport(){
		
		// 将配置文件hibernate.cfg.xml转换成对象
		Configuration cfg = new Configuration().configure("/org/person/dfw/hibernate/hibernate.cfg.xml");
		// 创建将SchemaExport对象，用于将对象转换成数据库表
		SchemaExport se = new SchemaExport(cfg);
		// 创建表：第一个参数表示是否将sql语句输出到控制台
		// 第二个参数表示是否将sql语句导入到数据库
		se.create(true, true);
	}
	
}