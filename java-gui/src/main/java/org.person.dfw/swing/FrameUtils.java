package org.person.dfw.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

/**
 * @moudle: FrameUtils 
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 下午2:04:39
 *
 */
public class FrameUtils {
	
	/**
	 * <p>Title: initFrame</p>
	 * <p>author : fw</p>
	 * <p>date : 2017年1月24日 下午2:11:34</p>
	 * @param frame
	 */ 
	public static void initFrame(Frame frame){
		frame.setTitle("fw");
		frame.setVisible(true);
		frame.setBounds(0, 0, 300, 120);
	}
	
	/**
	 * 设置成屏幕中心
	 * Title: setCenter</br>
	 * author : 丰伟</br>
	 * date : 2017年8月28日 下午8:07:27</br>
	 * @param frame
	 */
	public static void setCenter(Frame frame){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		double height = dimension.getHeight();						//屏幕高
		double width = dimension.getWidth();						//屏幕宽
		Dimension dimension2 = frame.getSize();
		double height2 = dimension2.getHeight();					//窗口高
		double width2 = dimension2.getWidth();						//窗口宽
		frame.setLocation((int)(width-width2)/2, (int)(height-height2)/2);
	}
	
	/**
	 * 获取系统中可用的字体
	 * <p>Title: getAvaliableFonts</p>
	 * <p>author : fw</p>
	 * <p>date : 2017年1月24日 下午2:15:59</p>
	 * @return
	 */ 
	public static java.util.List<Font> getAvaliableFonts(){
		java.util.List<Font> fonts = new ArrayList<>();
		GraphicsEnvironment ge =
				GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] allFonts = ge.getAllFonts();
		fonts = Arrays.asList(allFonts);
		
		return fonts;
	}
	
	
	/**
	 * 竖向设置面板	
	 * <p>Title: vertical1ColPan</p>
	 * <p>author : fw</p>
	 * <p>date : 2017年1月24日 下午2:51:04</p>
	 * @param frame
	 * @param components
	 */ 
	public static void vertical1ColPan(Frame frame,Component...components){
		FrameUtils.initFrame(frame);
		frame.setLayout(new GridLayout(components.length, 1));
		for (Component component : components) {
			frame.add(component);
		}
		frame.pack();
	}
	
	/**
	 * 设置横向面板
	 * <p>Title: herizontal1RowPan</p>
	 * <p>author : fw</p>
	 * <p>date : 2017年2月4日 上午9:36:22</p>
	 * @param frame
	 * @param components
	 */ 
	public static void herizontal1RowPan(Frame frame,Component... components){
		initFrame(frame);
		frame.setLayout(new GridLayout(1, components.length));
		for (Component component : components) {
			frame.add(component);
		}
	}
	
	/**
	 * 设置组
	 * <p>Title: setGroup</p>
	 * <p>author : dfw</p>
	 * <p>date : 2017年2月4日 上午10:03:48</p>
	 * @param components
	 */ 
	public static void setGroup(AbstractButton...components ){
		ButtonGroup buttonGroup = new ButtonGroup();
		for (AbstractButton component : components) {
			buttonGroup.add(component);
		}
		
	}
}

