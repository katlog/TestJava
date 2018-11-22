
/**  
 * @Title: TestThreadStackTrace.java
 * @Package: org.person.dfw.test.thread
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月6日 下午6:47:28
 * @version: V1.0  
 */ 
package name.fw.thread.thread;

import org.junit.Test;

/**
 * @moudle: TestThreadStackTrace 
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月6日 下午6:47:28
 *
 */
public class TestThreadStackTrace {
	//不能这样使用，因为此时是在此类初始化时调用的，所以栈信息是初始化加载过程中的的
	private String className = Thread.currentThread().getStackTrace()[1].getClassName();
	private String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
	
	private String getName(){
//		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//		for (StackTraceElement stackTraceElement : stackTrace) {
//			System.out.println(stackTraceElement.getClassName()+":"+stackTraceElement.getMethodName());
//		}
		
//		System.out.println(className);
//		System.out.println(methodName);
		//这种方式才是最好的哟
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		String[] bags = Thread.currentThread().getStackTrace()[1].getClassName().split("\\.");
		System.out.println(bags[bags.length-1]);
		
		return "";
	}
	@Test
	public void testStackTrace(){
		System.out.println("测试开始....");
		
		getName();
		
		System.out.println("测试结束....");
		
	}
}
