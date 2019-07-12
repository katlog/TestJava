package org.person.dfw.util.collections;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.person.dfw.util.Print;
import org.person.dfw.util.StringUtil;


/**
 * @moudle: MapUtil 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月2日 下午2:33:21
 *
 */
public class MapUtil implements Print {

	/**
	 * 格式打印
	 * author : katlog</br>
	 * date : 2017年8月2日 下午2:36:07</br>
	 * @param <T>
	 * @param <V>
	 * @param style 打印的样式
	 * @param filterReg 过滤的内容
	 * @param map
	 */ 
	public static <T, V> void formatPrint(Style style, String filterReg, Map<T,V> map) {
		
		Pattern pattern = null;
		if (!StringUtil.isEmpty(filterReg)) {
			pattern = Pattern.compile(filterReg);
		}
		
		Set<Entry<T, V>> set = map.entrySet();
		int maxLen = 0;
		for (Entry<T, V> entry : set) {
			int length = entry.getKey().toString().length();
			maxLen = maxLen>length? maxLen:length;
		}

		Print.segment(maxLen);
		
		switch (style) {
		case Horizontal:
			if (pattern!=null) {
				
			} else {
			}
			break;
		case Vertical:
			if (pattern!=null) {
				Set<Entry<T, V>> entrySet = map.entrySet();
				Iterator<Entry<T, V>> iterator = entrySet.iterator();
				while (iterator.hasNext()) {
					Entry<T, V> entry = iterator.next();
					Matcher matcher = pattern.matcher(entry.getKey().toString());
					byte i = 0;
					while (matcher.find()) {
						i++;
						System.out.printf("%s\t", matcher.group());
					}
					System.out.printf("\t"+entry.getValue().toString());
					//没有匹配到的不打印换行
					if (i>=1) {
						System.out.println();
					}
				}
			} else {
				Set<Entry<T, V>> entrySet = map.entrySet();
				Iterator<Entry<T, V>> iterator = entrySet.iterator();
				while (iterator.hasNext()) {
					Entry<T, V> entry = iterator.next();
					System.out.printf("%-"+(maxLen+5)+"s%s\n",entry.getKey(),entry.getValue());
				}
			}
			break;
		default:
			break;
		}
		
		Print.segment(maxLen);
	}

	/**格式打印
	 * Title: formatPrint</br>
	 * author : katlog</br>
	 * date : 2017年8月14日 上午9:36:24</br>
	 * @param style 
	 * @param map
	 */ 
	public static <T, V> void formatPrint(Style style, Map<T,V> map) {
		formatPrint(style, null, map);
	}
	
	/**格式打印
	 * Title: formatPrint</br>
	 * author : katlog</br>
	 * date : 2017年8月14日 上午9:36:27</br>
	 * @param map
	 */ 
	public static <T, V> void formatPrint( Map<T,V> map) {
		formatPrint(Style.Vertical, null, map);
	}

}
