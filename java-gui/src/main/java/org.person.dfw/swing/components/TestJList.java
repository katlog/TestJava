package org.person.dfw.swing.components;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.junit.Test;
/**
 * @moudle: TestJList 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月28日 下午9:38:39
 *
 */
@SuppressWarnings({"javadoc","unchecked","rawtypes"})
public class TestJList {
	
	/**这段程序代码主要是为读者展示如何使用数组创建列表框*/
	@Test public void test1() throws InterruptedException {
		
		JFrame f = new JFrame("测试窗口");
		f.setSize(400, 300);
		f.setLocation(0, 0);
		f.setVisible(true);
		JPanel p = new JPanel();
		f.setContentPane(p);
		p.setLayout(new BorderLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		// 将数据存储到数组name中
		String[] name = { "王鹏", "王宸博", "朱雪莲", "王棋淋", "项西云", "文日珍", "宋丽", "田秀"	};
		JList l = new JList(name);// 通过JList(String text)构造器将数组中的数据直接列举在列表框中
		
		JTextField tf = new JTextField();
		p.add(l, "North");
		p.add(tf, "South");
		// 此方法的用途就是当选择列表框中的任意一项时，都会将选择项显示在文本框中
		while (true) {
			tf.setText((String) l.getSelectedValue());
		}
	}
	
	
	/**这段程序代码主要为读者展示使用Vector方式创建列表框，在程序中同时使用了两种方法创建了三个列表框，
	 * 让其相互对比，一个是使用数组创建，一个是使用Vector创建*/
	@Test public void test2() throws InterruptedException {
		
		JFrame f = new JFrame("JList");
		Container contentPane = f.getContentPane();
		contentPane.setLayout(new GridLayout(1, 3));// 设置面板为GridLayout布局方式
		
		// 创建两个数组，s1和s2，分别存储国家名和姓名
		String[] s1 = { "美国", "日本", "大陆", "英国", "法国", "意大利", "澳洲", "韩国"};
		String[] s2 = { "范志毅", "符兵", "周宁", "杨晨", "高锋", "南方", "其他"	};
		Vector v = new Vector();// 创建一个Vector数据结构
		// 将数据存储到Vector数据结构中
		v.addElement("Nokia 3310");
		v.addElement("Nokia 8850");
		v.addElement("Nokia 8250");
		v.addElement("Motorola V8088");
		v.addElement("Motorola V3688x");
		v.addElement("Panasonic GD92");
		v.addElement("Panasonic GD93");
		v.addElement("NEC DB2100");
		v.addElement("Alcatel OT500");
		v.addElement("Philips Xenium 9@9 ");
		v.addElement("Ericsson T29sc");
		v.addElement("其他");
        
		JList list1 = new JList(s1);//创建一个列表框
        list1.setBorder(BorderFactory.createTitledBorder("您最喜欢到哪个国家玩呢？"));//设置主题
        
        JList list2 = new JList(s2); //创建一个列表框
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 一次只能选择一个列表索引
        list2.setBorder(BorderFactory.createTitledBorder("您最喜欢哪个运动员呢？"));//设置主题
        
        JList list3 = new JList(v); //创建一个列表框
        list3.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);// 一次只能选择一个列表索引
        list3.setBorder(BorderFactory.createTitledBorder("您最喜欢哪一种手机？"));
        
        contentPane.add(new JScrollPane(list1));
        contentPane.add(new JScrollPane(list2));
        contentPane.add(new JScrollPane(list3));
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Thread.sleep(20000);
	}
	
	/**使用AbstractListModel抽象类创建列表框*/
	@Test public void test3() throws InterruptedException {
		
		JFrame f = new JFrame("JList");
		Container contentPane = f.getContentPane();
		
		ListModel mode = new AbstractListModel() {
			String[] s = { "主板", "显示器", "内存", "CPU", "硬盘", "电源", "键盘", "鼠标"};
			public Object getElementAt(int index) {
				return (index + 1) + "." + s[index++];
			}
			public int getSize() {
				return s.length;
			}
		};
		JList list = new JList(mode);// 利用ListModel建立一个JList.
		list.setVisibleRowCount(5);// 设置程序一打开时所能看到的数据项个数。
		list.setBorder(BorderFactory.createTitledBorder("配置一台电脑需要的组件"));
		
		contentPane.add(new JScrollPane(list));
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Thread.sleep(20000);
	}
	
	/**用DefaultListModel类的类来创建一个列表框*/
	@Test public void test4() throws InterruptedException {
		
        JFrame f = new JFrame("JList");
        Container contentPane = f.getContentPane();
        
        ListModel mode = new DefaultListModel(){
            String[] s = {"显示器","主板","硬盘","内存","键盘","鼠标","电源","光驱"};
            
			{//起到构造函数的作用，匿名类不能有构造函数吧？
				for (int i = 0; i < s.length; i++)
					addElement((i + 1) + "." + s[i]);
			}
        };
        JList list = new JList(mode);//通过创建一个DefaultListModel类的继承类来创建一个列表框对象
        list.setVisibleRowCount(5);
        list.setBorder(BorderFactory.createTitledBorder("电脑配件"));
        
        contentPane.add(new JScrollPane(list));
        f.pack();
        f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Thread.sleep(20000);
	}
	
	JList list = null;
	JLabel label = null;
	/**然后单击列表框中的某个选项，标签组件会显示所选择的选项*/
	@Test public void test5() throws InterruptedException {
		
	    String[] s = {"美国","日本","大陆","英国","法国","意大利","澳洲","韩国"};
	    
        JFrame f = new JFrame("JList");
        Container contentPane = f.getContentPane();
        contentPane.setLayout(new BorderLayout());
        label = new JLabel();
        
        contentPane.add(label,BorderLayout.NORTH);
        contentPane.add(new JScrollPane(list),BorderLayout.CENTER);
        
        list = new JList(s);//利用数据创建列表框
        list.setVisibleRowCount(5);
        list.setBorder(BorderFactory.createTitledBorder("您最喜欢到哪个国家玩呢？"));
        list.addListSelectionListener(new ListSelectionListener() {
			@Override///实现valueChanged()方法，通过这个方法将列表框中所选取的数据显示在标签中
			public void valueChanged(ListSelectionEvent e) {
		        int tmp = 0;
		        String stmp = "您目前选取：";
				int[] index = list.getSelectedIndices();// 所选数据的序列号
				for (int i = 0; i < index.length; i++) {
					tmp = index[i];
					stmp = stmp + s[tmp] + "  ";
				}
				label.setText(stmp);// 为标签赋值
			}
		});
		
		contentPane.add(new JScrollPane(list));
        f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Thread.sleep(20000);
	}
	
	
	JList list1 = null;
	JList list2 = null;
	DefaultListModel mode1 = null;
	DefaultListModel mode2 = null;
	@Test public void test6() throws InterruptedException {
		
		String[] s = { "Java入门书", "C语言书","Hibernate书","Visual Basic书","ASP书","Dephi书","电脑组装书","Photoshop书"};
		class DataModel extends DefaultListModel {
			DataModel(int flag) {
				if (flag == 1) {
					for (int i = 0; i < s.length; i++)
						addElement(s[i]);
				}
			}
		}
		
	  	JFrame f=new JFrame("JList");
	  	Container contentPane=f.getContentPane();
	  	contentPane.setLayout(new GridLayout(1,2));
	  	
		MouseListener listener = new MouseAdapter() {
			// 处理鼠标双击事件.
			public void mouseClicked(MouseEvent e) {
				int index;
				if (e.getSource() == list1) {
					if (e.getClickCount() == 2) {
						// /当双击左边列表框中选项，会在左边将此项去掉，在右边列表框中将此项添加
						index = list1.locationToIndex(e.getPoint());
						String tmp = (String) mode1.getElementAt(index);
						mode2.addElement(tmp);
						list2.setModel(mode2);
						mode1.removeElementAt(index);
						list1.setModel(mode1);
					}
				}
				if (e.getSource() == list2) {
					if (e.getClickCount() == 2) {
						// /当双击右边列表框中选项，会在右边将此项去掉，在左边列表框中将此项添加
						index = list2.locationToIndex(e.getPoint());
						String tmp = (String) mode2.getElementAt(index);
						mode1.addElement(tmp);
						list1.setModel(mode1);
						mode2.removeElementAt(index);
						list2.setModel(mode2);
					}
				}
			}
		};
	  	
	  	mode1=new DataModel(1);
	  	list1=new JList(mode1);
	  	list1.setBorder(BorderFactory.createTitledBorder("图书种类!"));
	  	list1.addMouseListener(listener);//一遇到鼠标事件立即执行.
	  	
	  	mode2=new DataModel(2);
	  	list2=new JList(mode2);
	  	list2.setBorder(BorderFactory.createTitledBorder("你所需要选择的书："));
	  	list2.addMouseListener(listener);//一遇到鼠标事件立即执行.
	  	
		contentPane.add(new JScrollPane(list1));
		contentPane.add(new JScrollPane(list2));
		
		contentPane.add(new JScrollPane(list));
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Thread.sleep(20000);
	}
	
	
}
