
/**  
 * @Title: MyWindowAdpater.java
 * @Package: org.person.dfw.swing.listener
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 下午12:43:54
 * @version: V1.0  
 */ 
package org.person.dfw.swing.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @moudle: MyWindowAdpater 
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 下午12:43:54
 *
 */
public class MyWindowAdpater extends WindowAdapter{
	public void windowCloseing(WindowEvent e) {
		System.out.println("window closing");
//		System.exit(1);
	}
}
