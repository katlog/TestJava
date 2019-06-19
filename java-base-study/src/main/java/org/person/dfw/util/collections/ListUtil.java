package org.person.dfw.util.collections;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.person.dfw.util.Print;
import org.person.dfw.util.StringUtil;


/**
 * @moudle: List 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月2日 下午2:33:21
 *
 */
public class ListUtil implements Print {

	/**
	 * 格式打印
	 * author : katlog</br>
	 * date : 2017年8月2日 下午2:36:07</br>
	 * @param style 打印的样式
	 * @param filterReg 过滤的内容
	 * @param list
	 */ 
	public static void format(Style style, String filterReg, List list) {
		
		Pattern pattern = null;
		if (!StringUtil.isEmpty(filterReg)) {
			pattern = Pattern.compile(filterReg);
		}

		System.out.println("-------------------------");
		switch (style) {
		case Horizontal:
			if (pattern!=null) {
				for (Object object : list) {
					Matcher matcher = pattern.matcher(object.toString());
					while (matcher.find()) {
						System.out.printf("%s\t", matcher.group());
					}
					System.out.print("|");
				}
			} else {
				for (Object object : list) {
					System.out.print(object+"\t|");
				}
			}
			break;
		case Vertical:
			if (pattern!=null) {
				for (Object object : list) {
					Matcher matcher = pattern.matcher(object.toString());
					byte i = 0;
					while (matcher.find()) {
						i++;
						System.out.printf("%s\t", matcher.group());
					}
					//没有批评到的不打印换行
					if (i>=1) {
						System.out.println();
					}
				}
			} else {
				for (Object object : list) {
					System.out.println(object);
				}
			}
			break;
		default:
			break;
		}
		System.out.println("-------------------------");
	}
	
	public static <T> void format(Style style, String filterReg, T... list) {
		format(style, filterReg, Arrays.asList(list));
	}
	
	public static void format(Style style, List list){
		format(style, null, list);
	}
	
	
	public static void format( List list){
		format(Style.Vertical, null, list);
	}
	
	
	public static <T> void format(Style style, T... list) {
		format(style, null, Arrays.asList(list));
	}
	
	public static <T> void format(T... list) {
		format(Style.Vertical, null, Arrays.asList(list));
	}


}
