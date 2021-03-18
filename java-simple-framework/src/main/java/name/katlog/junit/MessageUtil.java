/**  
 * @Title: MessageUtil.java
 * @Package: org.person.dfw.junit.Time
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月7日 上午9:51:03
 * @version: V1.0  
 */
package name.katlog.junit;

/**
 * @moudle: MessageUtil
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月7日 上午9:51:03
 *
 */
public class MessageUtil {
	
	private String message;

	// Constructor
	// @param message to be printed
	public MessageUtil(String message) {
		this.message = message;
	}

	// prints the message
	public void printMessage() {
		System.out.println(message);
		while (true);
	}

	// add "Hi!" to the message
	public String salutationMessage() {
		message = "Hi!" + message;
		System.out.println(message);
		return message;
	}
}
