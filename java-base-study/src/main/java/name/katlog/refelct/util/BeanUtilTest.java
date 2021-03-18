package name.katlog.refelct.util;

import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Test;
import name.katlog.util.collections.MapUtil;

/**
 * @moudle: BeanUtilTest 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月11日 上午10:05:10
 *
 */
public class BeanUtilTest {
	/**
	 * Test _03method for {@link BeanUtil#getProVal(Object, String[])}.
	 */
	@Test
	public void testGetProVal() {
		fail("Not yet implemented");
	}
	/**
	 * Test _03method for {@link BeanUtil#comparePro(Object, Object, boolean, String[])}.
	 */
	@Test
	public void testCompareProTTBooleanStringArray() {
		fail("Not yet implemented");
	}
	/**
	 * Test _03method for {@link BeanUtil#comparePro(Object, Object)}.
	 */
	@Test
	public void testCompareProTT() {
		fail("Not yet implemented");
	}
	
	
	
	interface TESTINTERFACE{
		int A =1;
	}
	class TESTMEBERCLASS{
		public static final int A =2;
		public TESTMEBERCLASS(Object o) {
		}
	}
	
	/**
	 * Test _03method for {@link BeanUtil#getPublicFinalStatictPro(Object)}.
	 */
	@Test
	public void testGetPublicFinalStatictProT() {
		fail("Not yet implemented");
	}
	/**
	 * Test _03method for {@link BeanUtil#getPublicFinalStatictPro(Class)}.
	 */
	@Test
	public void testGetPublicFinalStatictProClassOfT() {
		
		Map<String, Object> map1 = null;
		try {
			//测试内部类 为接口
			map1 = BeanUtil.getPublicFinalStatictPro(TESTINTERFACE.class);
			//测试public 接口
			map1 = BeanUtil.getPublicFinalStatictPro(Comparable.class);
			//测试内部类 没默认构造函数
			Map<String, Object> map2 = BeanUtil.getPublicFinalStatictPro(TESTMEBERCLASS.class);
			MapUtil.formatPrint(map2);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		MapUtil.formatPrint(map1);
	}



}
