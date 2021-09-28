
/**  
 * @Title: TestList.java
 * @Package: org.person.dfw.collection
 * @author: katlog
 * @date: 2017年5月16日 下午8:49:42
 * @version: V1.0  
 */ 
package name.katlog.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @moudle: TestList 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年5月16日 下午8:49:42
 *
 */
public class TestList {

	/**
	 * 两种添加：带坐标、不带坐标
	 *  */
	@Test
	public void add(){
		List<Integer>  list = new ArrayList<>();
		list.add(0, 0);

		for (int i = 0; i < 10; i++) {
			list.add(i, i);
		}
		assertEquals(10, list.size());
	}




	@Test
	public void size() {
		List list = new ArrayList(100);
		Assert.assertEquals(0,list.size());

		List list1 = new CopyOnWriteArrayList(new ArrayList(200));
		Assert.assertEquals(0,list1.size());

	}


	/**查看ArrayList添加顺序:顺序添加的*/
	@Test public void listsort(){
		List list = new ArrayList();
		for( int i = 0; i < 1000; i++ ) {
			list.add(i);
		}
		System.out.println(list.toString());
		for (Object o : list) {
			System.out.print(o+",");
		}
		System.out.println();
		Collections.sort(list);
		System.out.println(list.toString());
	}
	
	/**不要在 foreach 循环里进行元素的 remove/add 操作。 remove 元素请使用 Iterator方式，如果并发操作，需要对 Iterator 对象加锁*/
	@Test public void foreach_remove1(){						//【报错】
		List<String> a = new ArrayList<String>();
		a.add("1");
		a.add("2");
		for (String temp : a) {
			if ("1".equals(temp)) {
				a.remove(temp);
			}
		}
	}
	
	@Test public void foreach_remove2(){						//【不报错】
		List<String> a = new ArrayList<String>();
		a.add("1");
		a.add("2");
		Iterator<String> it = a.iterator();
		while (it.hasNext()) {
			String temp = it.next();
			if ("2".equals(temp)) {
				it.remove();
			}
		}
	}
	
	/**初始化话大小和size不同*/
	@Test
	public void sizeAndInitalizeSize(){
		List list = new ArrayList<>(1000);

		assertEquals(0, list.size());
	}

	@Test
	public void subList() {
		List<Integer> a = Arrays.asList(1, 2, 3, 4);
		// [fromIndex toIndex)
		List<Integer> subList = a.subList(0, 1);

		assertArrayEquals(subList.toArray(),new Integer[]{1});
	}

	@Test
	public void collect1() {
		List<Integer> a = Arrays.asList(1, 2, 3, 4);

		// 不会返回null
		List<Integer> b = a.stream().filter(i -> i == 7).collect(Collectors.toList());
		assertEquals(0,b.size());
		List<Integer> c = b.stream().filter(i -> i == 0).collect(Collectors.toList());
		assertEquals(0, c.size());
	}

	@Test
	public void _linkList(){
		List<Integer> a = Arrays.asList(1, 2, 3, 4);
		List<Integer> b = new LinkedList<>(a);

		b.add(1, 10);
		System.out.println("b = " + b);


		b.add(5, 11);
		System.out.println("b = " + b);
	}

	/** 测试遍历的时候能否改: */
	@Test
	public void practice_iterUpdate(){
		List<Up> list = new ArrayList<>();
		list.add(new Up("origin"));

		for (int i = 0; i < list.size(); i++) {
			// 相当于一个新指针指向了list
			Up up = list.get(i);
			// 新指针指向新对象，所以什么也没改
			up = new Up("up");
			System.out.println("up = " + up);
		}

		System.out.println(Arrays.toString(list.toArray()));
	}
	@Data
	@AllArgsConstructor
	private static class Up{
		String u;
	}
}
