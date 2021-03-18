/**  
 * @Title: TestRunner.java
 * @Package: org.person.dfw.junit.Time
 * @date: 2017年3月7日 上午9:52:46
 * @version: V1.0  
 */
package name.katlog.junit.Suit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(JunitTestSuite.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
}