package org.person.dfw.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.junit.Test;

/**
 * @moudle: TestJComponent 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月24日 上午9:33:06
 *
 */
public class TestJComponent {
	
	/**特性1：通过在setToolTipText方法为组件提供帮助，悬停显示 */
	@Test public void _1_tooltip() throws InterruptedException{
        JFrame jf=new JFrame("添加内容面板测试程序");
        jf.setSize(300,200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        JPanel contentPane=new JPanel( );
        jf.setContentPane(contentPane);
        JButton b1=new JButton("确定");
        JButton b2=new JButton("取消");
        contentPane.add(b1);
        contentPane.add(b2);
        b1.setToolTipText("这个按钮是一个确定按钮");				//设置按钮组件的工具提示功能
        b2.setToolTipText("这个按钮是一个取消按钮");
        
        Thread.sleep(10000);
	}
	
	/**特性2：绘画和边框功能 */
	@Test public void _2_paintBorder() throws InterruptedException{
		
        JFrame jf=new JFrame("添加内容面板测试程序");
        jf.setSize(300,200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        JPanel contentPane=new JPanel( );
        jf.setContentPane(contentPane);
        JButton b1=new JButton("确定");
        JButton b2=new JButton("取消");
        contentPane.add(b1);
        contentPane.add(b2);
        b1.setBorder(BorderFactory.createLineBorder(Color.red));//用来设置按钮组件的边框
		
		Thread.sleep(10000);
	}
	
	/**特性3：可插入的观感器 */
	@Test public void _3_lookAndFeel() throws InterruptedException{
		
		JFrame jf=new JFrame("添加内容面板测试程序");
		jf.setSize(300,200);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		JPanel contentPane=new JPanel( );
		jf.setContentPane(contentPane);
		JButton b1=new JButton("确定");
		JButton b2=new JButton("取消");
		contentPane.add(b1);
		contentPane.add(b2);
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//设置皮肤
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
		}
		
		Thread.sleep(10000);
	}
	
	/**特性7：拖拽 */
	@Test public void _7_drag() throws InterruptedException{
		
		JFrame jFrame = new JFrame();
		jFrame.setSize(500, 200);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocation(400, 200);
		jFrame.setTitle("最简单的拖拽示例：拖拽文件到下面（20130124）");
		jFrame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		jFrame.getContentPane().add(panel, BorderLayout.CENTER);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			// panel表示要接受拖拽的控件
			new DropTarget(panel, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter() {
				@Override
				public void drop(DropTargetDropEvent dtde){									// 重写适配器的drop方法
					try {
						if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)){		// 如果拖入的文件格式受支持
							dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);				// 接收拖拽来的数据
							List<File> list = (List<File>) (dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
							String temp = "";
							for (File file : list)
								temp += file.getAbsolutePath() + ";\n";
							JOptionPane.showMessageDialog(null, temp);
							dtde.dropComplete(true);										// 指示拖拽操作已完成
						} else {
							dtde.rejectDrop();												// 否则拒绝拖拽来的数据
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
		}
		Thread.sleep(20000);
	}
	
	/**特性9：键绑定 */
	@Test public void _9_keyBind() throws InterruptedException{
		
		// 全局的位置变量，用于表示鼠标在窗口上的位置
		Point origin = new Point();
		JFrame myFrame = new JFrame();
		myFrame.setUndecorated(true);									//隐藏窗体的边框和标题栏
		myFrame.setSize(600, 400);
		myFrame.setVisible(true);
		myFrame.addMouseListener(new MouseAdapter() {
			// 按下(mousePressed 不是点击，而是鼠标被按下没有抬起)
			public void mousePressed(MouseEvent e) {
				// 当鼠标按下的时候获得窗口当前的位置
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		myFrame.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point p = myFrame.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				myFrame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			}
		});
		
		Thread.sleep(20000);
	}
	
	


}
