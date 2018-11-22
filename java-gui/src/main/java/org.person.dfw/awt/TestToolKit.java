package org.person.dfw.awt;

import java.awt.Toolkit;

/**
 * @moudle: TestToolKit
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月30日 上午9:56:07
 *
 */
public class TestToolKit {
	
	public static void main(String[] args) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		String[] fontList = tk.getFontList();
		for (int i = 0; i < fontList.length; i++) {
			System.out.println(fontList[i]);
		}
		
		System.out.println(tk.getColorModel());						//颜色模式
		System.out.println(tk.getScreenResolution()); 				//屏幕解析?
		System.out.println(tk.getScreenSize());						//屏幕大小
	}
}
