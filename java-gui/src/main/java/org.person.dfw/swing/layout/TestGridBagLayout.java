package org.person.dfw.swing.layout;
//这段代码主要是为读者展示如何使用GridBagLayout布局管理器布局方式来排列内容面板中的组件
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class testA extends JPanel{

	static final int WIDTH = 300;
	static final int HEIGHT = 150;

	

	public void add(Component c, GridBagConstraints constraints, int x, int y, int w, int h) {// 此方法用来添加控件到容器中
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		add(c, constraints);
	}
	
	testA() {
		JFrame loginframe = new JFrame("信息管理系统"); 				// 设置顶层容器
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 设置其顶层容器的关闭性
		
		GridBagLayout lay = new GridBagLayout();					// 创建网格组布局方式对象
		setLayout(lay);
		loginframe.add(this, BorderLayout.WEST);
		loginframe.setSize(WIDTH, HEIGHT);
		
		Toolkit kit = Toolkit.getDefaultToolkit();					// 设置顶层容器框架为居中
		Dimension screenSize = kit.getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		int x = (width - WIDTH) / 2;
		int y = (height - HEIGHT) / 2;
		loginframe.setLocation(x, y);
		
		JButton ok = new JButton("确认");
		JButton cancel = new JButton("放弃");
		JLabel title = new JLabel("布局管理器测试窗口");
		JLabel name = new JLabel("用户名");
		JLabel password = new JLabel("密 码");
		final JTextField nameinput = new JTextField(15);
		final JTextField passwordinput = new JTextField(15);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.NONE;			//在每一个方向都不填充，即保持原状
		constraints.anchor = GridBagConstraints.EAST;		//anchor参数则是当一个组件大于分配给它的单元格时发挥作用，该约束就是约定如何处理该组件
		constraints.weightx = 3;
		constraints.weighty = 4;
		
		// 使用网格组布局添加控件
		add(title, constraints, 0, 0, 4, 1);				// 	0列,0行	开始占	4列1行	
		add(name, constraints, 0, 1, 1, 1);					// 	0列,1行	开始占	1列1行	
		add(password, constraints, 0, 2, 1, 1);				// 	0列,2行	开始占	1列1行	
		add(nameinput, constraints, 2, 1, 1, 1);			// 	2列,1行	开始占	1列1行	
		add(passwordinput, constraints, 2, 2, 1, 1);		// 	2列,2行	开始占	1列1行	
		add(ok, constraints, 0, 3, 1, 1);					// 	0列,3行	开始占	1列1行	
		add(cancel, constraints, 2, 3, 1, 1);				// 	2列,3行	开始占	1列1行	
		
		loginframe.setResizable(false);
		loginframe.setVisible(true);
	}
}

public class TestGridBagLayout {
	public static void main(String[] args) {
		new testA();
	}
}
