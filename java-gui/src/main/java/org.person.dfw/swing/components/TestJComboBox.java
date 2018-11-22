package org.person.dfw.swing.components;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.Test;

/**
 * @moudle: TestJComboBox 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月28日 下午9:38:49
 *
 */
@SuppressWarnings({"javadoc","unchecked","rawtypes","serial"})
public class TestJComboBox {
	
	/**创建了两个下拉列表框，其中一个是使用数组创建的，另一个是使用Vector数据结构来创建的*/
	@Test public void test1() throws InterruptedException {
		
		JFrame f = new JFrame("JComboBox1");
		Container contentPane = f.getContentPane();
		contentPane.setLayout(new GridLayout(1, 2));
		String[] s = { "桃花", "梅花", "玫瑰", "牡丹", "月季", "茉莉", "菊花", "樱花"	};// 创建一个数组，用来构造下拉列表框
		Vector v = new Vector();// 创建一个Vector，用来构造下拉列表框
		// /往Vector中添加元素
		v.addElement("王鹏");
		v.addElement("谭妮");
		v.addElement("朱敏");
		v.addElement("宋兵");
		v.addElement("李丽");
		v.addElement("章儒");
	     
		JComboBox combo1 = new JComboBox(s); // 利用数组创建下拉列表框
		combo1.addItem("映山红");// 利用JComboBox类所提供的addItem()方法，加入一个项目到此JComboBox中。
		combo1.setBorder(BorderFactory.createTitledBorder("你最喜欢哪种花?"));
		JComboBox combo2 = new JComboBox(v); // 利用Vector创建下拉列表框
		combo2.setBorder(BorderFactory.createTitledBorder("你最好的朋友呢？"));
		contentPane.add(combo1);
		contentPane.add(combo2);
		
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Thread.sleep(20000);
	}
	
	/**用ComboxModel模型来创建下拉列表框*/
	@Test public void test2() throws InterruptedException {
		String[] s= {"王鹏","朱雪莲","王宸博","朱广兴","朱广莲","马力","欧海","黎明"};
		
		// 创建一个继承AbstractListModel 同时实现ComboBoxModel这个接口的类UserDefineComboBoxModel
		class UserDefineComboBoxModel extends AbstractListModel implements ComboBoxModel {
			String item = null;
			public Object getElementAt(int index){ // 由于继承AbstractListModel抽象类。因此我们分别在程序中实作了getElementAt()与getSize()方法。
				return s[index++];
			}
			public int getSize() {
				return s.length;
			}
			public void setSelectedItem(Object anItem){ // 由于我们实现了ComboBoxModel
														// interface.因此我们必须在程序中实作setSelectedItem()与getSelectedItem()方法.
				item = (String) anItem;
			}
			public Object getSelectedItem() {
				return item;
			}
		}
		
	    JFrame f=new JFrame("JComboBox");	
	    Container contentPane=f.getContentPane();
	    ComboBoxModel mode=new UserDefineComboBoxModel();//创建一个UserDefineComboBoxModel对象
	    JComboBox combo=new JComboBox(mode);//通过UserDefineComboBoxModel对象来创建一个下拉列表框
	    combo.setBorder(BorderFactory.createTitledBorder("你的好朋友是谁?"));
	    contentPane.add(combo);
	    f.pack();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setVisible(true);
		
		Thread.sleep(20000);
	}
	
	
	/**用DefaultComboBoxModel模型创建一个下拉列表框*/
	@Test public void test3() throws InterruptedException {
		String[] s = {"主板","硬盘","内存","电源","键盘","鼠标","显示器","光驱"};
		
		//利用继承DefaultComboBoxModel类创建一个用来创建下拉列表框对象的类
		class AModel extends DefaultComboBoxModel {
			AModel(String[] s) {
				for (int i = 0; i < s.length; i++)
					addElement(s[i]);
			}
		}
		JFrame f = new JFrame("JComboBox3");
		Container contentPane = f.getContentPane();
		
		ComboBoxModel mode = new AModel(s);// 创建一个ComboBoxModel对象
		JComboBox combo = new JComboBox(mode);// 使用上面的对象来创建一个下拉列表框
		combo.setBorder(BorderFactory.createTitledBorder("电脑配件"));
		contentPane.add(combo);
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		Thread.sleep(20000);
	}
	
	Font font=new Font("SansSerif",Font.PLAIN,12);
	/**创建一个下拉列表框，然后通过选择下拉列表框中的数字，来改变字体的大小*/
	@Test public void test4() throws InterruptedException {
		
	  	String[] fontsize={"12","14","16","18","20","22","24","26","28"};
	  	String defaultMessage="请选择或直接输入文字大小!";
	  	JComboBox combo=new JComboBox(fontsize);
	  	JLabel label=new JLabel("Swing",JLabel.CENTER);
		
		JFrame f = new JFrame("JComboBox");
		Container contentPane = f.getContentPane();
		contentPane.setLayout(new GridLayout(2, 1));
		label.setFont(font);
		combo.setBorder(BorderFactory.createTitledBorder("请选择你要的文字大小:"));
		combo.setEditable(true);
		ComboBoxEditor editor = combo.getEditor();
		combo.configureEditor(editor, defaultMessage);// 返回用于绘制和编辑 JComboBox 字段中所选项的编辑器
	  	  
		class Listener implements ItemListener, ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isaddItem = true;
				int fontsize = 0;
				String tmp = (String) combo.getSelectedItem();
				try {// 判断用户所输入的项目是否有重复，若有重复则不增加到JComboBox中。
					fontsize = Integer.parseInt(tmp);
					for (int i = 0; i < combo.getItemCount(); i++) {
						if (combo.getItemAt(i).equals(tmp)) {
							isaddItem = false;
							break;
						}
					}
					if (isaddItem) {
						combo.insertItemAt(tmp, 0);// 插入项目tmp到0索引位置(第一列中).
					}
					font = new Font("SansSerif", Font.PLAIN, fontsize);
					label.setFont(font);
				} catch (NumberFormatException ne) {
					combo.getEditor().setItem("你输入的值不是整数值，请重新输入!");
				}
			}
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {// 当用户的选择改变时，则在JLabel上会显示出Swing目前字形大小信息
					int fontsize = 0;
					try {
						fontsize = Integer.parseInt((String) e.getItem());
						label.setText("Swing 目前字形大小:" + fontsize);
					} catch (NumberFormatException ne) {// 若所输入的值不是整数，则不作任何的操作.
					}
				}
			}
		}
		Listener listener = new Listener();
		combo.addItemListener(listener);
		combo.addActionListener(listener);
	  	  
		contentPane.add(label);
		contentPane.add(combo);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		Thread.sleep(20000);
	}
}
