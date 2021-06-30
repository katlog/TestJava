
/**  
 * @Title: CheckBoxForEnum.java
 * @Package: org.hpin.commons.ui.taglib
 * @Description: 
 * @author: chensl
 * @date: 2017年2月28日 下午2:49:02
 * @version: V1.0  
 */ 
package name.katlog.reflect.util;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @moudle: CheckBoxForEnum 
 * @version:v1.0
 * @Description: 为枚举类生成CheckBox的标签
 * @author: fw
 * @date: 2017年2月28日 下午2:49:02
 *
 */
public class CheckBoxForEnum  {
	/**枚举类名*/
	protected String enumName;
	/**option中的name*/
	protected String name;
	/**选中的option*/
	protected String selectedCode;
	/**checkBox中的id*/
	protected String id;
	
	private Class getEnumClass(String enumName){
//		this.getClass().getClassLoader().
		return null;
	}
	
	public static <T> void main(String[] args) {
		URL url = new CheckBoxForEnum().getClass().getResource("/");
		String path = url.getPath();
		File file = new File(path);
		Map<String, String> classNameMap = new HashMap<String, String>();
		//采用递归调用获取全部的calss文件
		getFilesNames(classNameMap, file,"EnumActivityId");
		String classPath = classNameMap.get("EnumActivityId");
		String classTempName = classPath.substring(file.getPath().length(), classPath.length());
		String classTempName1 = classTempName.replace("\\", ".").substring(1);
		String newClassName = classTempName1.substring(0, classTempName1.length()-6);
		try {
			Class<?> enumClazz = new CheckBoxForEnum().getClass().getClassLoader().loadClass(newClassName);
			
			if (enumClazz.isEnum()) {
				//获取同类型的所有枚举
				Field[] fields = enumClazz.getFields();
				for (Field field : fields) {
					System.out.println(field);
					System.out.println(field.getName());
					System.out.println(field.get(new Object()));
				}
				for (Object constant  : enumClazz.getEnumConstants()) {
					System.out.println(constant);
					//问题是怎样将其向下强制转换
					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取类的全限定名
	 * <p>Title: getFilesNames</p>
	 * <p>author : chensl</p>
	 * <p>date : 2017年2月28日 下午8:59:06</p>
	 * @param map
	 * @param file
	 * @param enumName
	 * @return
	 */
	private static Map<String, String> getFilesNames(Map<String, String> map,File file,String enumName){
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File file2 : files) {
				getFilesNames(map, file2,enumName);
			}
		}else{
			int prfix = file.getName().indexOf(".");
			if (file.getName().substring(0,prfix).equals(enumName)) {
				map.put(file.getName().substring(0,prfix),file.getPath());
			}
		}
		return map;
	}
	/**
	 * 取得 枚举类名
	 * @return 枚举类名
	 */
	public String getEnumName() {
	    return enumName;
	}

	/**
	 * 設定 枚举类名
	 * @param enumName 枚举类名
	 */
	public void setEnumName(String enumName) {
	    this.enumName = enumName;
	}

	/**
	 * 取得 option中的name
	 * @return option中的name
	 */
	public String getName() {
	    return name;
	}

	/**
	 * 設定 option中的name
	 * @param name option中的name
	 */
	public void setName(String name) {
	    this.name = name;
	}

	/**
	 * 取得 选中的option
	 * @return 选中的option
	 */
	public String getSelectedCode() {
	    return selectedCode;
	}

	/**
	 * 設定 选中的option
	 * @param selectedCode 选中的option
	 */
	public void setSelectedCode(String selectedCode) {
	    this.selectedCode = selectedCode;
	}

	/**
	 * 取得 checkBox中的id
	 * @return checkBox中的id
	 */
	public String getId() {
	    return id;
	}

	/**
	 * 設定 checkBox中的id
	 * @param id checkBox中的id
	 */
	public void setId(String id) {
	    this.id = id;
	}
	
}
