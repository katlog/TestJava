
/**  
 * @Title: TestMap.java
 * @Package: org.person.dfw.collection
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月20日 下午6:01:10
 * @version: V1.0  
 */ 
package org.person.dfw.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * @moudle: TestMap 
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月20日 下午6:01:10
 *
 */
public class TestMap {
	
	
	@Test public void _traversal (){
		Map<String, String> map = new HashMap<>();
		map.put("A", "1");
		map.put("B", "2");
		map.put("C", "3");
		map.put("D", "4");
		
		for (int i = 0; i < 300; i++) {
			
		}
		
		//entrySet遍历
		Set<Entry<String, String>> entrySet = map.entrySet();
		for (Entry<String, String> entry : entrySet) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
		//keySet遍历：效率低，【好像搜索了两边】
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			System.out.println(key+":"+map.get(key));
		}
	}

	@Test
    /**compute()是java8在Map中新增的一个方法，相对而言较为陌生。其作用是把remappingFunction的计算结果关联到key上
     * （即remappingFunction返回值作为新value）。写一段它的简单应用的代码，并与“同级生”merge()类比加深理解*/
	public void compute() {
		HashMap map = new HashMap();
		map.put("a", "c");
		map.put("b", "h");
		map.put("c", "e");

		map.compute("a", (k, v) -> "C") ;
		map.merge("b", "h", (k, v) -> "H") ;
		map.compute("d", (k, v) -> "D") ;
		map.merge("c", "e", (k, v) -> null) ;
		System.out.println(map.toString());
		// 输出结果为：{a=C, b=H, d=D}
	}
}	
