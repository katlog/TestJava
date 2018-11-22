package org.person.dfw.swing.components;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.junit.Test;

/**
 * @moudle: TestJFileChooser 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月30日 下午8:24:47
 *
 */
@SuppressWarnings("javadoc")
public class TestJFileChooser {
	
    JFrame f = null;
    JLabel label = null;
    JTextArea textarea = null;
    JFileChooser fileChooser = null;
    
	@Test public void test() throws InterruptedException {
		
        f = new JFrame("FileChooser Example");
        Container contentPane = f.getContentPane();
        
        //中间文字显示部分
        JScrollPane scrollPane = new JScrollPane(textarea);
        textarea = new JTextArea();
        scrollPane.setPreferredSize(new Dimension(350,300));
        
		//新建与存储部分
        JPanel panel = new JPanel();
        ActionListener listener = listener();
        JButton b1 = new JButton("新建文件");
        b1.addActionListener(listener);
        JButton b2 = new JButton("存储文件");
        b2.addActionListener(listener);
        panel.add(b1);
        panel.add(b2);
       
        //组合起来
        label = new JLabel(" ",JLabel.CENTER);
        contentPane.add(label,BorderLayout.NORTH);
        contentPane.add(scrollPane,BorderLayout.CENTER);
        contentPane.add(panel,BorderLayout.SOUTH);
        f.pack();
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
		Thread.sleep(50000);
	}

	private ActionListener listener() {
		ActionListener listener = new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				File file = null;
				int result;
				//建立一个FileChooser对象,并指定D:的目录为默认文件对话框路径.
				 fileChooser = new JFileChooser("D:\\");
				/*
				 * 当用户按下"打开文件"按钮时,JFileChooser的showOpenDialog()方法会输出文件对话框,并利用setApproveButtonText()方法取代按钮上"Open"文字;
				 * 以setDialogTitle()方法设置打开文件对话框Title名称，当使用选择完后,会将选择结果存到result变量中.
				 */
				if (e.getActionCommand().equals("新建文件")) {
					fileChooser.setApproveButtonText("确定");
					fileChooser.setDialogTitle("打开文件");
					result = fileChooser.showOpenDialog(f);
					textarea.setText("");
					/*
					 * 当用户按下打开文件对话框的"确定"钮后,我们就可以利用getSelectedFile()方法取得文件对象.若是用户按下打开文件对话框的"Cancel"钮,则将在label上显示"你没有选择任何文件"
					 * 字样.
					 */
					if (result == JFileChooser.APPROVE_OPTION) {
						file = fileChooser.getSelectedFile();
						label.setText("您选择打开的文件名称为：" + file.getName());
					} else if (result == JFileChooser.CANCEL_OPTION) {
						label.setText("您没有选择任何文件");
					}
					FileInputStream fileInStream = null;
					if (file != null) {
						try {
							// 利用FileInputStream将文件内容放入此数据流中以便读取.
							fileInStream = new FileInputStream(file);
						} catch (FileNotFoundException fe) {
							label.setText("File Not Found");
							return;
						}
						int readbyte;
						try {
							// 以read()方法读取FileInputStream对象内容,当返回值为-1时代表读完此数据流.将所读到的字符显示
							// 在textarea中.
							while ((readbyte = fileInStream.read()) != -1) {
								textarea.append(String.valueOf((char) readbyte));
							}
						} catch (IOException ioe) {
							label.setText("读取文件错误");
						} finally {// 回收FileInputStream对象,避免资源的浪费.
							try {
								if (fileInStream != null)
									fileInStream.close();
							} catch (IOException ioe2) {
							}
						}
					}
				}
				// 实现写入文件的功能.
				if (e.getActionCommand().equals("存储文件")) {
					result = fileChooser.showSaveDialog(f);
					file = null;
					String fileName;
					// 当用户没有选择文件,而是自己键入文件名称时,系统会自动以此文件名建立新文件.
					if (result == JFileChooser.APPROVE_OPTION) {
						file = fileChooser.getSelectedFile();
						label.setText("您选择存储的文件名称为：" + file.getName());
					} else if (result == JFileChooser.CANCEL_OPTION) {
						label.setText("您没有选择任何文件");
					}
					// 写入文件使用FileOutputStream,在这个范例中,写入文件的方式是将之前内容清除并重新写入.若想把新增的内容加在原有的文件内容后面，可以使用FileOutputStream(String
					// name,Boolean append)这个构造函数.
					FileOutputStream fileOutStream = null;
					if (file != null) {
						try {
							fileOutStream = new FileOutputStream(file);
						} catch (FileNotFoundException fe) {
							label.setText("File Not Found");
							return;
						}
						String content = textarea.getText();
						try {
							fileOutStream.write(content.getBytes());
						} catch (IOException ioe) {
							label.setText("写入文件错误");
						} finally {
							try {
								if (fileOutStream != null)
									fileOutStream.close();
							} catch (IOException ioe2) {
							}
						}
					}
				}
			}
		};
		return listener;
	}
}
