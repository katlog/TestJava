package org.person.dfw.awt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;
import org.person.dfw.swing.FrameUtils;

/**
 * @moudle: TestGraphics 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月30日 上午10:17:40
 *
 */
@SuppressWarnings({ "javadoc", "serial"})
public class TestGraphics {
	
	private static final int HEIGHT = 200;
	private static final int WIDTH = 300;
	private JFrame jFrame = new JFrame("测试graphics");
	private Container cp = jFrame.getContentPane();
	
	
	
	@Test public void _draw_line() throws InterruptedException {
		
		jFrame.setSize(WIDTH, HEIGHT);
		FrameUtils.setCenter(jFrame);
		jFrame.setVisible(true);
		
		cp.add(new JPanel(){
			@Override  public void paintComponent(Graphics g) {
//				super.paintComponent(g);     // paint parent's background
		        g.drawString("用Graphics写字和画图的基本方法", 20,40);  //写字
		        g.drawOval(100, 100, 30, 30);                       //画椭圆
		        g.drawOval(200, 100, 40, 25);
		        g.drawLine(20, 140, 200,140);                       //画直线
		        g.drawRect(20, 160, 50, 80);                        //画ru
		        g.drawRoundRect(110, 160, 100, 100, 25, 18);
			}
		});
		
		Thread.sleep(10000);
	}
	
	@Test public void _draw_font() throws InterruptedException {
		
		jFrame.setSize(WIDTH, HEIGHT);
		FrameUtils.setCenter(jFrame);
		jFrame.setVisible(true);
		
		cp.add(new JPanel(){
			@Override  public void paintComponent(Graphics g) {
				String s1 = "This Font is ";
				Font font = new Font("Arial", Font.BOLD, 18);
				g.setColor(Color.red);
				setBackground(new Color(0, 255, 0));
				g.setFont(font);
				g.drawString(s1 + font.getName(), 20, 60);
				g.setFont(new Font("隶书", Font.BOLD, 28));
				g.drawString("现在是隶书", 20, 120);
				g.setColor(new Color(0, 0, 0));
			}
		});
		
		Thread.sleep(10000);
	}
	
	/**drawString()实现精确定位?*/
	@Test public void _draw_String() throws InterruptedException {
		
		jFrame.setSize(WIDTH, HEIGHT);
		FrameUtils.setCenter(jFrame);
		jFrame.setVisible(true);
		
		cp.add(new JPanel(){
			@Override  public void paintComponent(Graphics g) {
				String s1 = "Hello, Java World!";
				g.setColor(Color.red); 							// 前景色
				setBackground(new Color(0, 255, 0)); 			// 背景色
				Font font = new Font("Arial", Font.BOLD, 18); 	// 字体
				g.setFont(font); 								// 设置字体

				FontMetrics fm = g.getFontMetrics(font); 		// 类对象,可以获得某个字体的高度,以及字符串的宽度
				int height = fm.getHeight();
				int width = fm.stringWidth(s1);

				int posx =50; int posy = 50;
				g.drawString(s1, posx, posy); 					// posx,posy位置显示s1
				g.drawString("I will come in.", posx + width, posy + height);// 偏移后的字符串显示
			}
		});
		
		Thread.sleep(10000);
	}
	
	/**drawString()实现精确定位?*/
	@Test public void _draw_arc_polygon() throws InterruptedException {
		
		jFrame.setSize(WIDTH, HEIGHT);
		FrameUtils.setCenter(jFrame);
		jFrame.setVisible(true);
		
		cp.add(new JPanel(){
			@Override  public void paintComponent(Graphics g) {
				Toolkit tk = Toolkit.getDefaultToolkit();
				Dimension d = tk.getScreenSize();
				int cx = d.width/2 ; int cy = d.height/2;
				// 定义待画组合图形的中心
				Point p = new Point(cx / 2, cy / 2);
				// 定义待画矩形的轮廓
				Rectangle rect = new Rectangle((p.x - 40), (p.y - 40), 80, 40); // 定义多边形的顶点
				int[] xP = { (p.x - 40), (p.x + 90), p.x + 200, (p.x - 40)};
				int[] yP = { (p.y - 40), (p.y + 140), (p.y + 60), (p.y - 40)};
				g.drawArc(rect.x, rect.y, rect.width, rect.height * 2, 270, 90);
				g.drawPolygon(xP, yP, 3);
				g.setColor(Color.red);
			}
		});
		
		Thread.sleep(10000);
	}
	
	@Test public void _draw_image() throws InterruptedException {
		
		jFrame.setSize(WIDTH, HEIGHT);
		FrameUtils.setCenter(jFrame);
		jFrame.setVisible(true);
		
		cp.add(new JPanel(){
			
			private static final int DEFAULT_WIDTH = 300;
			private static final int DEFAULT_HEIGHT = 200;
			private Image image;
			public void paintComponent(Graphics g) {
				image = new ImageIcon("gui/org/person/dfw/awt/blue-ball.gif").getImage();
				if (image == null)
					return;
				int imageWidth = image.getWidth(this);
				int imageHeight = image.getHeight(this);
				
				// draw the image in the upper-left corner
				g.drawImage(image, 0, 0, null);
				
				// tile the image across the component
				for (int i = 0; i * imageWidth <= getWidth(); i++)
					for (int j = 0; j * imageHeight <= getHeight(); j++)
						if (i + j > 0)
							g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j * imageHeight);
			}
			public Dimension getPreferredSize() {
				return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
			}
		});
		
		Thread.sleep(10000);
	}
	
	/**drawString()实现精确定位?*/
	@Test public void _draw_3d() throws InterruptedException {
		
		jFrame.setSize(WIDTH, HEIGHT);
		FrameUtils.setCenter(jFrame);
		jFrame.setVisible(true);
		
		cp.add(new JPanel(){
			@Override  public void paintComponent(Graphics g) {
				g.setColor(Color.green);
				g.fillArc(10, 10, 60, 40, 30, 60);
				g.fillOval(10, 60, 60, 40);
				g.fillRect(80, 10, 60, 40);
				Polygon p = new Polygon();
				p.addPoint(150, 10);
				p.addPoint(210, 10);
				p.addPoint(210, 50);
				p.addPoint(150, 50);
				g.fillPolygon(p); // 绘制高度为5个像素，视角为45度正方向的3维矩形
				g.fill3DRect(150, 10, 60, 40, true);
				for (int i = 1; i <= 5; i++)
					g.draw3DRect(150 + i, 10 - i, 60, 40, true);
				// 绘制高度为5个像素，视角为135度正方向的3维矩形
				g.fill3DRect(150, 60, 60, 40, true);
				for (int i = 1; i <= 5; i++)
					g.draw3DRect(150 - i, 60 + i, 60, 40, true);
			}
		});
		
		Thread.sleep(10000);
	}
	
	/**drawString()实现精确定位?*/
	@Test public void _draw_drawing_board() throws InterruptedException {
		
		jFrame.setSize(WIDTH, HEIGHT);
		FrameUtils.setCenter(jFrame);
		jFrame.setVisible(true);
		
		cp.add(new JPanel(){
			Color bgColor = Color.LIGHT_GRAY;
			
			@Override  public void paintComponent(Graphics g) {
			   JPanel   panel = new JPanel(new BorderLayout());
			      
				JButton btnColor = new JButton("Change Color");
				panel.add(btnColor, BorderLayout.SOUTH);
				btnColor.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						Color color = JColorChooser.showDialog(jFrame, "Choose a color", bgColor);
						if (color != null) { // new color selected
							bgColor = color;
						}
						panel.setBackground(bgColor); // change panel's background color
					}
				});
			 
				jFrame.setContentPane(panel);
				jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jFrame.setTitle("JColorChooser ExtractMethod");
				jFrame.setSize(300, 200);
				jFrame.setLocationRelativeTo(null); // center the application window
				jFrame.setVisible(true); // show it
			}
		});
		
		Thread.sleep(20000);
	}
	
	
}
