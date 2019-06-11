
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

import static org.junit.Assert.*;

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
    /**如果lambda表达式的值不为空，不论key是否已经存在，建立一种映射关系key=newValue;
	 * 否则，不建立映射并返回null*/
	public void compute() {
		Map<Integer,String> map = new HashMap<>(1);
		map.put(1, "a");

		String s = map.compute(1, (k, v) -> v + "a");
		String s1 = map.compute(2, (k, v) -> v + "b");

		assertEquals("aa", s);
		assertEquals("aa", map.get(1));
		assertEquals("nullb",map.get(2));
		assertEquals("nullb", s1);

		String s3 = map.compute(3, (integer, s2) -> null);
		assertNull(s3);
		assertEquals(2, map.size());
	}

	@Test
	/**
	 * key存在并且不为空，计算remappingFunction的值value；
	 *
	 * 如果value不为空，保存指定key和value的映射关系；
	 * 如果value为null，remove（key）；
	 * 如果计算value的过程抛出了异常，computeIfPresent方法中会再次抛出，key和其对应的值不会改变
	 * */
	public void computeIfPresent(){
		Map<Integer,String> map = new HashMap<>(1);
		map.put(1, "a");

		String s = map.computeIfPresent(1, (k, v) -> v + "a");
		String s1 = map.computeIfPresent(2, (k, v) -> v + "b");
		assertEquals("aa", s);
		assertEquals("aa", map.get(1));
		assertNull(map.get(2));
		assertNull(s1);

	}

	@Test
	/**
	 * 如果给定的key不存在（或者key对应的value为null），就去计算mappingFunction的值；
	 *
	 * 如果计算mappingFunction的值的过程出现异常，再次抛出异常，不记录映射关系，返回null；
	 * */
	public void computeIfAbsent(){
		Map<Integer,String> map = new HashMap<>(1);
		map.put(0, "0");

		map.computeIfAbsent(0, integer -> {
			System.out.println("integer = " + integer);
			return "0-0";
		});
		assertEquals("0", map.get(0));

		/** 如果mappingFunction的值不为null，就把key=value放进去； */
		map.computeIfAbsent(1, integer -> integer + "");
		assertEquals("1",map.get(1));
		assertEquals(2, map.size());

		/** 如果mappingFunction的值为null，就不会记录该映射关系，返回值为null； */
		map.computeIfAbsent(2, integer -> null);
		assertEquals(2, map.size());
	}


	@Test
	public void merge(){
		Map<Integer,String> map = new HashMap<>(10);
		map.put(1, "a");

		map.merge(1, "a1", (s, s2) -> s +"-"+ s2);
		assertEquals("a-a1", map.get(1));

		map.merge(2, "b1", (s, s2) -> s + "-" + s2);
		assertEquals("b1", map.get(2));

	}


	@Test
	/** 如果没有key则添加 */
	public void putIfAbsent(){
		Map<Integer,String> map = new HashMap<>(10);
		map.put(1, "a");

		map.putIfAbsent(1, "1");
		map.putIfAbsent(2, "2");
		assertEquals("a", map.get(1));
		assertEquals("2", map.get(2));

	}
}	
