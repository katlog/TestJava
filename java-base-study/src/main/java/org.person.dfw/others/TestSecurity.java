package org.person.dfw.others;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Security;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Test;
import org.person.dfw.util.collections.MapUtil;

public class TestSecurity {
	@Test
	public void test() {
		// Security.getProperty(name)获取的是java.security文件中的属性和值
		System.out.println(Security.getProperty("package.access"));

		// 获取全部属性和值用其他方式
		Properties props = new Properties();

		String sep = File.separator;
		File propFile = new File(System.getProperty("java.home") + sep + "lib"
				+ sep + "security" + sep + "java.security");
		if (propFile.exists()) {
			InputStream is = null;
			try {
				FileInputStream fis = new FileInputStream(propFile);
				is = new BufferedInputStream(fis);
				props.load(is);
				MapUtil.formatPrint(props);
				for (Entry<Object, Object> entry : props.entrySet()) {
					System.out.printf("%-40s%s\n",entry.getKey(),entry.getValue());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException ioe) {
					}
				}
			}
		}
	}
}
