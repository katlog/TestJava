package org.person.dfw.util.file.text;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.person.dfw.util.Print.Style;
import org.person.dfw.util.collections.MapUtil;
import org.person.dfw.util.file.FileUtil;

/**
 * @moudle: JavaTextUtil 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月1日 上午11:48:14
 *
 */
public final class JavaTextUtil extends TextUtil {
	
	/**单行注释*/
	private static final String REG_LINE_COMMENT = "^ *//.+";
	/**多行注释*/
	private static final String REG_LINES_COMMENT = "/\\*(?!\\*)(.|[^.])+?\\*/";
	/**块注释*/
	private static final String REG_BLOCK_COMMENT = "/\\*\\*(.|[^.])+?\\*/";
	
	/**类名*/
	private static final String REG_PUBLIC_CLASS_NAME = "\\s*public\\s*(?:final)?\\s*class\\s*(\\w+)";
	/**接口名*/
	private static final String REG_PUBLIC_INTERFACE_NAME = "\\s*public\\s*interface\\s*(\\w+)";
	/**类文件后缀*/
	private static final String REG_FILE_SUFFIX = ".+\\.java";
	
	public interface BB{}

	public static void main(String[] args) {
		
//		changePackage("C:\\Users\\lenovo\\Desktop\\opslabJutil-master\\opslabJutil-master\\src\\main\\java\\com", "com.opslab.util", "org.person.dfw.util");
		Map<File, String> classNames = getJavaFiles(System.getProperty("user.dir"));
		MapUtil.formatPrint(Style.Vertical, null, classNames);
	}
	
	public static void changePackage(String basePath,String sourePack,String descPack){
		
		try {
			changeFilesContent(basePath, REG_FILE_SUFFIX, sourePack, descPack);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Title: getClassNameByFName</br>
	 * author : 丰伟</br>
	 * date : 2017年8月3日 下午5:06:13</br>
	 * @param basePath
	 * @return map
	 */ 
	public static Map<File, String> getJavaFiles(String basePath) {
		Map<File, String> map = new HashMap<>();
		List<File> list = new ArrayList<File>();
		
		//获取所有的java文件
		FileUtil.listFileFilter(basePath, list, REG_FILE_SUFFIX);
		for (File file : list) {
			String className = file.getName().substring(0,file.getName().indexOf(".java"));
			map.put(file, className);
		}
		
		return map;
	}
	
	@Test public void test(){
		String string = "public interface FTPUtil {\r\n" + 
				"    //判断远程文件是否存在\r\n" + 
				"    public boolean isExists(String fileName);\r\n" + 
				"public class AAA\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"public final class BBB  ";
		Pattern pattern = Pattern.compile(REG_PUBLIC_CLASS_NAME);
		Matcher matcher = pattern.matcher(string);
		System.out.println(matcher.find());

		System.out.println(matcher.group(1));
	}
}
