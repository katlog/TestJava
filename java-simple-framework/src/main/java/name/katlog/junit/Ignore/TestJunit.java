/**  
 * @Title: TestJunit.java
 * @Package: org.person.dfw.junit.Time
 * @date: 2017年3月7日 上午9:52:00
 * @version: V1.0  
 */
package name.katlog.junit.Ignore;

import name.katlog.junit.MessageUtil;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestJunit {
	String message = "Robert";
	MessageUtil messageUtil = new MessageUtil(message);
	
//	有时可能会发生我们的代码还没有准备好的情况，这时测试用例去测试这个方法或代码的时候会造成失败。@Ign
//	ore 注释会在这种情况时帮助我们。
//	• 一个含有 @Ignore 注释的测试方法将不会被执行。
//	• 如果一个测试类有 @Ignore 注释，则它的测试方法将不会执行

	@Ignore
	@Test
	public void testPrintMessage() {
		System.out.println("Inside testPrintMessage()");
		message = "Robert";
		//assertEquals(message, messageUtil.printMessage());
	}

	@Test
	public void testSalutationMessage() {
		System.out.println("Inside testSalutationMessage()");
		message = "Hi!" + "Robert";
		assertEquals(message, messageUtil.salutationMessage());
	}
}