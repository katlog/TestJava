package name.katlog.objectAndclass;

import com.alibaba.fastjson.JSON;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import name.katlog.util.JsonUtil;
import org.junit.Assert;
import org.junit.Test;


/**
 * @moudle: TestObject 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月1日 上午9:21:51
 *
 */
public class TestObject {
	
	
	@Test
	public void _hashCode(){
		String s = "OK";
		StringBuffer sb = new StringBuffer(s);
		System.out.println(s.hashCode()+"---"+sb.hashCode());
		
		String t = new String("OK");
		StringBuffer tb = new StringBuffer(t);
		System.out.println(t.hashCode()+"---"+tb.hashCode());

	}

	@Test
	public void 	test() {
		int a = 1;
		System.out.println("a = " + a);
		int b = a;
		b = 2;
		System.out.println("a = " + a);

		int[] a1 = { 1, 2, 3, 4, 5 };
		int[] a2;
		a2 = a1;
		for(int i = 0; i < a2.length; i++)
			a2[i] = a2[i] + 1;
		for(int i = 0; i < a1.length; i++)
			System.out.println("a1[" + i + "] = " + a1[i]);
	}


	@Setter(AccessLevel.PRIVATE)
	@Getter
	@AllArgsConstructor
	static class People implements Cloneable{
		private String name;
		private int age;

		@Override
		protected People clone()  {
			try {
				// 浅拷贝
				return (People) super.clone();
			} catch (CloneNotSupportedException e) {
				throw new AssertionError();
			}
		}

	}
	@Test
	public void _clone(){
		People p = new People("kat", 12);

		People p2 = p.clone();

		System.out.println("p2 = " + JSON.toJSONString(p2));
		// Assert.assertEquals(p, p2);
	}
}
