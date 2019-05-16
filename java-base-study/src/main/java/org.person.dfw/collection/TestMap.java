
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
import java.util.function.BiFunction;

import org.junit.Assert;
import org.junit.Test;

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
		HashMap<String,String> map = new HashMap<>(3);
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

	@Test
	public void computeMerge(){

		Map<String, String> myMap = new HashMap<>();
		String keyA = "A";
		String keyB = "B";
		String keyC = "C";
		String keyD = "D";
		String keyE = "E";
		String keyF = "F";
		String keyG = "G";
		String keyH = "H";
		myMap.put(keyA, "str01A");
		myMap.put(keyB, "str01B");
		myMap.put(keyC, "str01C");

		System.out.println("myMap initial content:"+ myMap);

		myMap.merge(keyA, "merge01", String::concat);
		myMap.merge(keyD, "merge01", String::concat);
		System.out.println("Map merge demo content:"+ myMap);

		BiFunction<String, String, String> biFunc = (t, u) -> t == null ? u : t + "." + u;

		myMap.merge(keyA, "BiFuncMerge01", biFunc);
		myMap.merge(keyE, "BiFuncMerge01", biFunc);
		System.out.println("Map customized BiFunction merge demo content:"+ myMap);

		String msg = "msgCompute";
		myMap.compute(keyB, (k, v) -> (v == null) ? msg : v.concat(msg));
		myMap.compute(keyF, (k, v) -> (v == null) ? msg : v.concat(msg));
		System.out.println("Map customized BiFunction compute demo content:"+ myMap);

		myMap.computeIfAbsent(keyC, TestMap::genValue);
		myMap.computeIfAbsent(keyG, TestMap::genValue);
		System.out.println("Map customized Function computeIfAbsent demo content:"+ myMap);

		myMap.computeIfPresent(keyC, biFunc);
		myMap.computeIfPresent(keyH, biFunc);
		System.out.println("Map customized biFunc computeIfPresent demo content:"+ myMap);
	}

	static String genValue(String str) {
		System.out.println("===");
		return str + "2";
	}



	@Test
	/** 如果存在key就计算 */
	public void compute1(){
		Map<Integer,String> map = new HashMap<>(1);
		map.put(1, "a");

		String s = map.compute(1, (k, v) -> v + "a");
		String s1 = map.compute(2, (k, v) -> v + "b");

		Assert.assertEquals("aa", s);
		Assert.assertEquals("aa", map.get(1));
		Assert.assertEquals("nullb",map.get(2));
		Assert.assertEquals("nullb", s1);

	}

	@Test
	/** 如果存在key就计算 computeIfAbsent则相反 */
	public void computeIfPresent(){
		Map<Integer,String> map = new HashMap<>(1);
		map.put(1, "a");

		String s = map.computeIfPresent(1, (k, v) -> v + "a");
		String s1 = map.computeIfPresent(2, (k, v) -> v + "b");
		Assert.assertEquals("aa", s);
		Assert.assertEquals("aa", map.get(1));
		Assert.assertNull(map.get(2));
		Assert.assertNull(s1);

	}

	@Test
	/** 如果没有key则添加 */
	public void putIfAbsent(){
		Map<Integer,String> map = new HashMap<>(1);
		map.put(1, "a");

		map.putIfAbsent(1, "1");
		map.putIfAbsent(2, "2");
		Assert.assertEquals("a", map.get(1));
		Assert.assertNull("2", map.get(2));

	}
}	
