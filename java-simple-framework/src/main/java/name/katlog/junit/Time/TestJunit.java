/**  
 * @Title: TestJunit.java
 * @Package: org.person.dfw.junit.Time
 * @date: 2017年3月7日 上午9:52:00
 * @version: V1.0  
 */
package name.katlog.junit.Time;

import name.katlog.junit.MessageUtil;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestJunit {
	String message = "Robert";
	MessageUtil messageUtil = new MessageUtil(message);

//	一个测试用例比起指定的毫秒数花费了更多的时间， Junit 将自动将
//	它标记为失败。timeout 参数和 @Test 注释一起使用。
	
	@Test(timeout = 1000)
	public void testPrintMessage() {
		System.out.println("Inside testPrintMessage()");
		messageUtil.printMessage();
	}

	@Test
	public void testSalutationMessage() {
		System.out.println("Inside testSalutationMessage()");
		message = "Hi!" + "Robert";
		assertEquals(message, messageUtil.salutationMessage());
	}
}