/**  
 * @Title: JunitTestSuit.java
 * @Package: org.person.dfw.junit.Suit
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月7日 上午10:02:42
 * @version: V1.0  
 */
package name.katlog.junit.Suit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestJunit1.class, TestJunit2.class })
public class JunitTestSuite {
}