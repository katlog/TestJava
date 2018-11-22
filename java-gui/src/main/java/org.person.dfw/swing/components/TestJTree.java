package org.person.dfw.swing.components;

//这段程序代码主要是为读者展示如何通过JTree()来创建树组件
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.junit.Test;

/**
 * @moudle: TestJTree 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月28日 下午8:45:59
 *
 */
public class TestJTree {
	
	/**一般显示*/
	@Test public void test1() throws InterruptedException {
		JFrame f = new JFrame("树组件测试");
		Container contentPane = f.getContentPane();
		
		JTree tree = new JTree();								// 创建一个系统默认的树组件
		JScrollPane scrollPane = new JScrollPane();				// 创建一个滚动条组件
		scrollPane.setViewportView(tree);						// 将树组件添加到滚动条组件中
		contentPane.add(scrollPane);							// 将滚动条组件添加到中间容器中
		
		f.pack();
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		Thread.sleep(10000);
	}
	
	/**多层级显示*/
	@Test public void test2() throws InterruptedException {
		
		JFrame f = new JFrame("树组件测试");
		Container contentPane = f.getContentPane();
	    
	    String[] s1={"青菜","大蒜","大葱"};						//创建三个字符串数组	
	    String[] s2={"苹果","梨子","香蕉"};
	    String[] s3={"馒头","包子","饺子","馄饨","面条"};
	    Hashtable hashtable1=new Hashtable();							//创建两个哈希表对象
	    Hashtable hashtable2=new Hashtable();
	    hashtable1.put("蔬菜",s1); 						//将s1添加到哈希表对象1中，并且给字符串数组命名为"蔬菜"，将s2添加到哈希表对象1中，并且给字符串数组命名为"水果"，将哈希表对象1添加到哈希表对象2中，并且给字符串数组命名为"点心"，将s3添加到哈希表对象2中，并且给字符串数组命名为"中点"
	    hashtable1.put("水果",s2);
	    hashtable1.put("点心",hashtable2);
	    
	    hashtable2.put("中点",s3);
	    
		Font font = new Font("Dialog", Font.PLAIN, 12);
		Enumeration keys = UIManager.getLookAndFeelDefaults().keys();
		
		while (keys.hasMoreElements()) {				//【用来干啥的？】
			Object key = keys.nextElement();
			if (UIManager.get(key) instanceof Font) {
				UIManager.put(key, font);
			}
		}
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// 设置界面的观感器
		} catch (Exception el) {
			System.exit(0);
		}
		
		JTree tree = new JTree(hashtable1);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tree);
		contentPane.add(scrollPane);
		f.pack();
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		
		Thread.sleep(20000);
	}
	
	/**DefaultMutableTreeNode的使用？*/
	@Test public void test3() throws InterruptedException {
		
	    JFrame f=new JFrame("今天要购买的清单");
	    Container contentPane=f.getContentPane();
	    
	    DefaultMutableTreeNode root=new DefaultMutableTreeNode("今天要购买的东西");//使用DefaultMutableTreeNode的构造器创建根节点
	    DefaultMutableTreeNode node1=new DefaultMutableTreeNode("蔬菜");//使用DefaultMutableTreeNode的构造器创建四个枝节点	
	    DefaultMutableTreeNode node2=new DefaultMutableTreeNode("水果");
	    DefaultMutableTreeNode node3=new DefaultMutableTreeNode("礼品");
	    root.add(node1); //将四个枝节点添加到根节点中
	    root.add(node2);
	    root.add(node3);
	    DefaultMutableTreeNode leafnode=new DefaultMutableTreeNode("白菜"); //利用DefaultMutableTreeNode的构造器构造器创建出叶节点，再将页节点分别添加到不同的枝节点上
	    node1.add(leafnode);
	    leafnode=new DefaultMutableTreeNode("大蒜");
	    node1.add(leafnode);
	    leafnode=new DefaultMutableTreeNode("土豆");
	    node1.add(leafnode);
	    leafnode=new DefaultMutableTreeNode("苹果");
	    node2.add(leafnode);
	    leafnode=new DefaultMutableTreeNode("香蕉");
	    node2.add(leafnode);
	    leafnode=new DefaultMutableTreeNode("西瓜");
	    node2.add(leafnode);
	    
	    leafnode=new DefaultMutableTreeNode("礼品");
	    node3.add(leafnode);
	    
	    leafnode=new DefaultMutableTreeNode("茅台酒");
	    node3.add(leafnode);
	    leafnode=new DefaultMutableTreeNode("营养麦片");
	    node3.add(leafnode);
	    leafnode=new DefaultMutableTreeNode("保健食品");
	    node3.add(leafnode);
	    
	    
	    JTree tree=new JTree(root);
	    JScrollPane scrollPane=new JScrollPane();
	    scrollPane.setViewportView(tree);
	    
	    contentPane.add(scrollPane);
	  	f.pack();
	  	f.setVisible(true);
	  	f.addWindowListener(new WindowAdapter(){
	  		 public void windowClosing(WindowEvent e){
	  		   System.exit(0);	
	  		 }
	  	});
		
		
		Thread.sleep(20000);
	}
	
	public static class test4 implements TreeModelListener {
		JLabel label;
		String nodeName = null; // 原有节点名称

		public test4() {
			JFrame f = new JFrame("今天要购买的清单");
			Container contentPane = f.getContentPane();
	    
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("今天要购买的东西");
			DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("蔬菜");
			DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("水果");
			DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("礼品");
			DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("家用小物件");
			root.add(node1);
			root.add(node2);
			root.add(node3);
			root.add(node4);
	    
			DefaultMutableTreeNode leafnode = new DefaultMutableTreeNode("白菜");
			node1.add(leafnode);
			leafnode = new DefaultMutableTreeNode("大蒜");
			node1.add(leafnode);
			leafnode = new DefaultMutableTreeNode("土豆");
			node1.add(leafnode);
			leafnode = new DefaultMutableTreeNode("苹果");
			node2.add(leafnode);
			leafnode = new DefaultMutableTreeNode("香蕉");
			node2.add(leafnode);
			leafnode = new DefaultMutableTreeNode("西瓜");
			node2.add(leafnode);
	    
			leafnode = new DefaultMutableTreeNode("礼品");
			node3.add(leafnode);
	    
			leafnode = new DefaultMutableTreeNode("茅台酒");
			node3.add(leafnode);
			leafnode = new DefaultMutableTreeNode("营养麦片");
			node3.add(leafnode);
			leafnode = new DefaultMutableTreeNode("保健食品");
			node3.add(leafnode);
	    
			leafnode = new DefaultMutableTreeNode("味精");
			node4.add(leafnode);
	    
			leafnode = new DefaultMutableTreeNode("酱油");
			node4.add(leafnode);
			leafnode = new DefaultMutableTreeNode("洗洁精");
			node4.add(leafnode);
			leafnode = new DefaultMutableTreeNode("保险袋");
			node4.add(leafnode);
	    
			JTree tree = new JTree(root);
			tree.setEditable(true);// 设置JTree为可编辑的
			tree.addMouseListener(new MouseHandle());// 在Tree加入检测Mouse事件，以便取得节点名称
	    
			DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();// 下面两行取得DefaultTreeModel,并检测是否有TreeModelEvent事件.
			treeModel.addTreeModelListener(this);
	    
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportView(tree);
	    
			label = new JLabel("更改数据为: ");
			contentPane.add(scrollPane, BorderLayout.CENTER);
			contentPane.add(label, BorderLayout.SOUTH);
			f.pack();
			f.setVisible(true);
			
			f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		}
		public void treeNodesChanged(TreeModelEvent e) {
			// 本方法实作TreeModelListener接口，本接口共定义四个方法，分别是TreeNodesChanged()
			// *treeNodesInserted()、treeNodesRemoved()、treeNodesRemoved()、treeStructureChanged().在此范例中只针对更改节点值的部份，因此只要实现treeNodesChanged()方法.
			TreePath treePath = e.getTreePath();// 获取目前所选取节点的树路径
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)treePath.getLastPathComponent();//获得所选取节点
			try {// 获取其子节点的节点序号，针对此序号获得其子节点
				int[] index = e.getChildIndices();
				node = (DefaultMutableTreeNode) node.getChildAt(index[0]);
			} catch (NullPointerException exc) {
			}
			label.setText(nodeName + "更改数据为: " + (String) node.getUserObject());// 将节点内容显示在标签中
		}
		public void treeNodesInserted(TreeModelEvent e) {
		}
		public void treeNodesRemoved(TreeModelEvent e) {
		}
		public void treeStructureChanged(TreeModelEvent e) {
		}


		class MouseHandle extends MouseAdapter {// 处理Mouse点选事件
			public void mousePressed(MouseEvent e) {
				try {
					JTree tree = (JTree) e.getSource();// 当单击树中的节点时，会获取节点的内容
					int rowLocation = tree.getRowForLocation(e.getX(), e.getY());
					TreePath treepath = tree.getPathForRow(rowLocation);
					TreeNode treenode = (TreeNode) treepath.getLastPathComponent();
					nodeName = treenode.toString();
				} catch (NullPointerException ne) {
				}
			}
		}
		
		@Test public void test() throws InterruptedException{
			new test4();
			Thread.sleep(20000);
		}
	}
	
	
	public static class test5 implements TreeModelListener {
		JLabel label;

		String nodeName = null; // 原有节点名称

		DefaultMutableTreeNode root;

		DefaultMutableTreeNode node1;

		DefaultMutableTreeNode node2;

		DefaultMutableTreeNode node3;

		DefaultMutableTreeNode node4;

		JTree tree;

		DefaultTreeModel treeModel;

		public test5() {
			JFrame f = new JFrame("今天要购买的清单");
			Container contentPane = f.getContentPane();
			Container con = new Container();
			root = new DefaultMutableTreeNode("今天要购买的东西");
			node1 = new DefaultMutableTreeNode("蔬菜");
			node2 = new DefaultMutableTreeNode("水果");
			node3 = new DefaultMutableTreeNode("礼品");
			node4 = new DefaultMutableTreeNode("家用小物件");
			root.add(node1);
			root.add(node2);
			root.add(node3);
			root.add(node4);
			DefaultMutableTreeNode leafnode = new DefaultMutableTreeNode("白菜");
			node1.add(leafnode);
			leafnode = new DefaultMutableTreeNode("大蒜");
			node1.add(leafnode);
			leafnode = new DefaultMutableTreeNode("土豆");
			node1.add(leafnode);
			leafnode = new DefaultMutableTreeNode("苹果");
			node2.add(leafnode);
			leafnode = new DefaultMutableTreeNode("香蕉");
			node2.add(leafnode);
			leafnode = new DefaultMutableTreeNode("西瓜");
			node2.add(leafnode);
			leafnode = new DefaultMutableTreeNode("礼品");
			node3.add(leafnode);
			leafnode = new DefaultMutableTreeNode("茅台酒");
			node3.add(leafnode);
			leafnode = new DefaultMutableTreeNode("营养麦片");
			node3.add(leafnode);
			leafnode = new DefaultMutableTreeNode("保健食品");
			node3.add(leafnode);
			leafnode = new DefaultMutableTreeNode("味精");
			node4.add(leafnode);
			leafnode = new DefaultMutableTreeNode("酱油");
			node4.add(leafnode);
			leafnode = new DefaultMutableTreeNode("洗洁精");
			node4.add(leafnode);
			leafnode = new DefaultMutableTreeNode("保险袋");
			node4.add(leafnode);
			tree = new JTree(root);
			tree.setEditable(true);// 设置JTree为可编辑的
			tree.addMouseListener(new MouseHandle());// 使Tree加入检测Mouse事件，以便取得节点名称
			final DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();// 下面两行取得DefaultTreeModel,并检测是否有TreeModelEvent事件
			treeModel.addTreeModelListener(this);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportView(tree);
			label = new JLabel("更改数据为: ");
			JButton b1 = new JButton("增加节点");
			JButton b2 = new JButton("删除节点");
			con.setLayout(new FlowLayout());
			con.add(b1);
			con.add(b2);
			con.add(label);
			contentPane.add(scrollPane, BorderLayout.CENTER);
			contentPane.add(con, BorderLayout.SOUTH);
			f.pack();
			f.setVisible(true);
			f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			b1.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent Event) {
					DefaultMutableTreeNode parentNode = null;
					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("新节点");
					newNode.setAllowsChildren(true);
					TreePath parentPath = tree.getSelectionPath();
					parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());// 取得新节点的父节点
					treeModel.insertNodeInto(newNode, parentNode, parentNode.getChildCount());// 由DefaultTreeModel的insertNodeInto（）方法增加新节点
					tree.scrollPathToVisible(new TreePath(newNode.getPath())); // tree的scrollPathToVisible()方法在使Tree会自动展开文件夹以便显示所加入的新节点。若没加这行则加入的新节点,会被
																				// 包在文件夹中，必须自行展开文件夹才看得到。
					label.setText("新增节点成功");
				}
			});
			b2.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent Event) {
					TreePath treepath = tree.getSelectionPath();
					if (treepath != null) {
						DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode) treepath.getLastPathComponent();// 下面两行取得选取节点的父节点.
						TreeNode parent = (TreeNode) selectionNode.getParent();
						if (parent != null) {
							treeModel.removeNodeFromParent(selectionNode); // 由DefaultTreeModel的removeNodeFromParent()方法删除节点，包含它的子节点。
							label.setText("删除节点成功");
						}
					}
				}
			});
		}
		/*
		 * 本方法实作TreeModelListener接口，本接口共定义四个方法，分别是TreeNodesChanged()
		 * treeNodesInserted()、treeNodesRemoved()、treeNodesRemoved()、treeStructureChanged().在此范例中我们只针对更改节点值的部份，因此只实作
		 * treeNodesChanged()方法.
		 */
		public void treeNodesChanged(TreeModelEvent e) {
			TreePath treePath = e.getTreePath();
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
			try {
				int[] index = e.getChildIndices();
				node = (DefaultMutableTreeNode) node.getChildAt(index[0]);
			} catch (NullPointerException exc) {
			}
			label.setText(nodeName + "更改数据为: " + (String) node.getUserObject());
		}
		public void treeNodesInserted(TreeModelEvent e) {
		}
		public void treeNodesRemoved(TreeModelEvent e) {
		}
		public void treeStructureChanged(TreeModelEvent e) {
		}
		public static void main(String args[]) {
			new test5();
		}

		// 上面处理了节点变化事件，下面将给出有关鼠标双击后的事件处理代码。
		class MouseHandle extends MouseAdapter {// 处理Mouse点选事件
			public void mousePressed(MouseEvent e) {
				try {
					JTree tree = (JTree) e.getSource();
					int rowLocation = tree.getRowForLocation(e.getX(), e.getY());
					TreePath treepath = tree.getPathForRow(rowLocation);
					TreeNode treenode = (TreeNode) treepath.getLastPathComponent();
					nodeName = treenode.toString();
				} catch (NullPointerException ne) {
				}
			}
		}

		@Test  public void test() throws InterruptedException {
			new test5();
			Thread.sleep(20000);
		}
	}

	
	public static class test6 implements TreeModelListener, TreeSelectionListener {
		JLabel label;

		String nodeName = null; // 原有节点名称

		DefaultMutableTreeNode root;
		DefaultMutableTreeNode node1;
		DefaultMutableTreeNode node2;
		DefaultMutableTreeNode node3;
		DefaultMutableTreeNode node4;

		JTree tree;

		DefaultTreeModel treeModel;

		JPanel p1;
		JPanel p2;
		JPanel p3;

		static final JTextArea ta1 = new JTextArea(30, 30);
		static final JTextArea ta2 = new JTextArea(40, 40);
		static final JTextArea ta3 = new JTextArea(40, 40);
		static final JTextArea ta4 = new JTextArea(40, 40);
		static final JTextArea ta5 = new JTextArea(40, 40);

		JPanel panel1;
		JPanel panel2;
		JPanel panel3;
		JPanel panel4;
		JPanel panel5;

		public test6() {
			p1 = new JPanel();
			p2 = new JPanel();
			p3 = new JPanel();
			label = new JLabel("更改数据为: ");

			JTabbedPane tp = new JTabbedPane();
			panel1 = new JPanel();
			panel2 = new JPanel();
			panel3 = new JPanel();
			panel4 = new JPanel();
			panel5 = new JPanel();
			tp.addTab("panel1", panel1);
			tp.setEnabledAt(0, true);
			tp.setTitleAt(0, "简介");
			tp.addTab("panel2", panel2);
			tp.setEnabledAt(1, true);
			tp.setTitleAt(1, "用处");
			tp.addTab("panel3", panel3);
			tp.setEnabledAt(2, true);
			tp.setTitleAt(2, "各地比较");
			tp.addTab("panel4", panel4);
			tp.setEnabledAt(0, true);
			tp.setTitleAt(3, "一般进货来源");
			tp.addTab("panel5", panel5);
			tp.setEnabledAt(4, true);
			tp.setTitleAt(4, " 备注");
			tp.setPreferredSize(new Dimension(500, 200));
			tp.setTabPlacement(JTabbedPane.TOP);
			tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			panel1.setLayout(new FlowLayout());
			panel1.add(ta1);
			panel2.setLayout(new FlowLayout());
			panel2.add(ta2);
			panel3.setLayout(new FlowLayout());
			panel3.add(ta3);
			panel4.setLayout(new FlowLayout());
			panel4.add(ta4);
			panel5.setLayout(new FlowLayout());
			panel5.add(ta5);
			JSplitPane splitPane = new JSplitPane();
			splitPane.setOneTouchExpandable(true);
			splitPane.setContinuousLayout(true);
			splitPane.setPreferredSize(new Dimension(100, 200));
			splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			splitPane.setLeftComponent(p1);
			splitPane.setRightComponent(p2);
			splitPane.setDividerSize(3);
			splitPane.setDividerLocation(50);
			JFrame f = new JFrame("今天要购买的清单");
			Container contentPane = f.getContentPane();
			Container con = new Container();
			root = new DefaultMutableTreeNode("今天要购买的东西");
			node1 = new DefaultMutableTreeNode("蔬菜");
			node2 = new DefaultMutableTreeNode("水果");
			node3 = new DefaultMutableTreeNode("礼品");
			node4 = new DefaultMutableTreeNode("家用小物件");
			root.add(node1);
			root.add(node2);
			root.add(node3);
			root.add(node4);
			DefaultMutableTreeNode leafnode = new DefaultMutableTreeNode("白菜");
			node1.add(leafnode);
			leafnode = new DefaultMutableTreeNode("大蒜");
			node1.add(leafnode);
			leafnode = new DefaultMutableTreeNode("土豆");
			node1.add(leafnode);
			leafnode = new DefaultMutableTreeNode("苹果");
			node2.add(leafnode);
			leafnode = new DefaultMutableTreeNode("香蕉");
			node2.add(leafnode);
			leafnode = new DefaultMutableTreeNode("西瓜");
			node2.add(leafnode);
			leafnode = new DefaultMutableTreeNode("礼品");
			node3.add(leafnode);
			leafnode = new DefaultMutableTreeNode("茅台酒");
			node3.add(leafnode);
			leafnode = new DefaultMutableTreeNode("营养麦片");
			node3.add(leafnode);
			leafnode = new DefaultMutableTreeNode("保健食品");
			node3.add(leafnode);
			leafnode = new DefaultMutableTreeNode("味精");
			node4.add(leafnode);
			leafnode = new DefaultMutableTreeNode("酱油");
			node4.add(leafnode);
			leafnode = new DefaultMutableTreeNode("洗洁精");
			node4.add(leafnode);
			leafnode = new DefaultMutableTreeNode("保险袋");
			node4.add(leafnode);
			tree = new JTree(root);
			tree.setEditable(true);// 设置JTree为可编辑的
			tree.addMouseListener(new MouseHandle());// 使Tree加入检测Mouse事件，以便取得节点名称
			final DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();// 下面两行取得DefaultTreeModel,并检测是否有TreeModelEvent事件
			treeModel.addTreeModelListener(this);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportView(tree);
			JSplitPane splitPane1 = new JSplitPane();
			splitPane1.setOneTouchExpandable(true);
			splitPane1.setContinuousLayout(true);
			splitPane1.setPreferredSize(new Dimension(100, 200));
			splitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane1.setTopComponent(scrollPane);
			splitPane1.setBottomComponent(label);
			splitPane1.setDividerSize(1);
			splitPane1.setDividerLocation(80);
			p3.setLayout(new FlowLayout());
			JSplitPane splitPane2 = new JSplitPane();
			splitPane2.setOneTouchExpandable(true);
			splitPane2.setContinuousLayout(true);
			splitPane2.setPreferredSize(new Dimension(100, 200));
			splitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane2.setTopComponent(tp);
			splitPane2.setBottomComponent(p3);
			splitPane2.setDividerSize(1);
			splitPane2.setDividerLocation(80);
			p1.setLayout(new GridLayout(1, 1));
			p1.add(splitPane1);
			p2.setLayout(new GridLayout(1, 1));
			p2.add(splitPane2);
			f.setContentPane(splitPane);
			f.pack();
			f.setVisible(true);
			tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION); // 设置树的选择模式
			f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			/*
			 * 本方法实作TreeModelListener接口，本接口共定义四个方法，分别是TreeNodesChanged()
			 * treeNodesInserted()、treeNodesRemoved()、treeNodesRemoved()、treeStructureChanged().在此范例中我们只针对更改节点值的部份，因此只实作
			 * treeNodesChanged()方法.
			 */
			tree.addTreeSelectionListener(new TreeSelectionListener() {// 下面主要是相应树节点的选择事件
				public void valueChanged(TreeSelectionEvent e) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();// 将所选的节点模型化
					ta1.setText("You selected " + node);
				}
			});
		}
		public void treeNodesChanged(TreeModelEvent e) {// 下面的方法主要是当节点发生改变时，相应的会在标签中显示出修改前的数据后修改后的数据
			TreePath treePath = e.getTreePath();
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
			try {
				int[] index = e.getChildIndices();
				node = (DefaultMutableTreeNode) node.getChildAt(index[0]);
			} catch (NullPointerException exc) {
			}
			label.setText(nodeName + "更改数据为: " + (String) node.getUserObject());
		}
		public void treeNodesInserted(TreeModelEvent e) {
		}
		public void treeNodesRemoved(TreeModelEvent e) {
		}
		public void treeStructureChanged(TreeModelEvent e) {
		}
		public void valueChanged(TreeSelectionEvent e) {
			TreePath path = tree.getSelectionPath();
			if (path == null)
				return;
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
			ta1.setText(selectedNode.toString());
		}
		// 上面处理了节点变化事件，下面将给出有关鼠标双击后的事件处理代码。
		class MouseHandle extends MouseAdapter {// 处理Mouse点选事件
			public void mousePressed(MouseEvent e) {
				try {
					JTree tree = (JTree) e.getSource();
					int rowLocation = tree.getRowForLocation(e.getX(), e.getY());
					TreePath treepath = tree.getPathForRow(rowLocation);
					TreeNode treenode = (TreeNode) treepath.getLastPathComponent();
					nodeName = treenode.toString();
				} catch (NullPointerException ne) {
				}
			}
		}
		@Test public void test6() throws InterruptedException{
			new test6();
			Thread.sleep(20000);
		}
	}
	
/*	public static class test7  implements TreeModelListener
	{
		JLabel label;
		String nodeName = null; //原有节点名称
		DefaultMutableTreeNode root;
		DefaultMutableTreeNode node1;
		DefaultMutableTreeNode node2;
		DefaultMutableTreeNode node3;
		DefaultMutableTreeNode node4;
		JTree tree;
		DefaultTreeModel treeModel;
		JPanel p1;
		JPanel p2;
		JPanel p3;
		static JTextArea ta1;
		static JTextArea ta2;
		static JTextArea ta3;
		static JTextArea ta4;
		static JTextArea ta5;
		JPanel panel1;
		JPanel panel2;
		JPanel panel3;
		JPanel panel4;
		JPanel panel5;
	  public test7()
	  {
	    ………………………………….与上例中加黑部分内容相同
	    
	tree.addMouseListener(new MouseHandle());
	    }
	class MouseHandle extends MouseAdapter
	{//处理Mouse点选事件
	    public void mousePressed(MouseEvent e) 
	    {
	    	 String nodeName;
	        try{
	          JTree tree = (JTree)e.getSource(); //当单击鼠标，在右边会显示出相应的数据信息
	          int rowLocation = tree.getRowForLocation(e.getX(), e.getY());          
	          TreePath treepath = tree.getPathForRow(rowLocation);
	          TreeNode treenode = (TreeNode) treepath.getLastPathComponent(); 
	          nodeName = treenode.toString();
	          dd.ta1.setText("1中含有丰富的碳水化合物、维生素和微量元素。尤维生素A和胡萝卜素的含量较高。 ");//在右边文本区中所显示的数据信息
	          dd.ta2.setText("可以吃、治病。 ");
	          dd.ta3.setText("上海的比较便宜。 ");
	          dd.ta4.setText("一般进货从广东惠州。 ");
	          dd.ta5.setText("营养价值高，需要多买点。 ");
	          
	        }catch(NullPointerException ne){}
	    }
	    
		// 上面处理了节点变化事件，下面将给出有关鼠标双击后的事件处理代码。
		class MouseHandle extends MouseAdapter {// 处理Mouse点选事件
			public void mousePressed(MouseEvent e) {
				try {
					JTree tree = (JTree) e.getSource();
					int rowLocation = tree.getRowForLocation(e.getX(), e.getY());
					TreePath treepath = tree.getPathForRow(rowLocation);
					TreeNode treenode = (TreeNode) treepath.getLastPathComponent();
					nodeName = treenode.toString();
				} catch (NullPointerException ne) {
				}
			}
		}
	}*/

	static final int WIDTH=300;
	static final int HEIGHT=800;
	/**DefaultMutableTreeNode的使用？*/
	@Test public void test8() throws InterruptedException {
		JFrame frame=new JFrame();
		frame.setTitle("Ê÷×é¼þ²âÊÔ´°¿Ú");
		frame.setSize(WIDTH,HEIGHT);
		frame.setVisible(true);
		
		String[] s1={"µçÊÓ»ú","Ï´ÒÂ»ú","Ó°µú»ú","¿Õµ÷","±ùÏä","Î¢²¨Â¯"};	
	    String[] s2={"´óÒÂ¹ñ","·¹×À","´²","Êé×À","Êé¹ñ"};
	    String[] s3={"¹ø","ÌÀÉ×","½Á°è»ú","¹ø²ù","²Ëµ¶"};
	    String[] s4={"µçÄÔÊé","Ð¡Ëµ","¿Î±¾","×Öµä"};
	    Hashtable hashtable=new Hashtable();
	    hashtable.put("¼ÒÓÃµçÆ÷", s1);
	    hashtable.put("¼Ò¾ß", s2);
	    hashtable.put("³ø¾ß", s3);
	    hashtable.put("Êé", s4);
	    JTree tree=new JTree(hashtable);
	    
	    JScrollPane scrollPane=new JScrollPane();
	    scrollPane.setViewportView(tree);
	    frame.getContentPane().add(scrollPane);
	    frame.pack();
		
		Thread.sleep(20000);
	}
	
	/**DefaultMutableTreeNode的使用？*/
	@Test public void test9() throws InterruptedException {
		
		
		Thread.sleep(20000);
	}
	
	/**DefaultMutableTreeNode的使用？*/
	@Test public void test10() throws InterruptedException {
		
		
		Thread.sleep(20000);
	}
	
	/**DefaultMutableTreeNode的使用？*/
	@Test public void test11() throws InterruptedException {
		
		
		Thread.sleep(20000);
	}
}
