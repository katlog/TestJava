/**  
 * @Title: TestUnit1.java
 * @Package: org.person.dfw.junit.Suit
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月7日 上午10:01:00
 * @version: V1.0  
 */
package name.katlog.junit.Suit;

import name.katlog.junit.MessageUtil;
import org.junit.Test;

public class TestJunit1 {
	String message = "Robert";
	MessageUtil messageUtil = new MessageUtil(message);

	@Test
	public void testPrintMessage() {
		System.out.println("Inside testPrintMessage()");
//		assertEquals(message, messageUtil.printMessage());
	}
}