package org.person.dfw.swing;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.junit.Test;

/**
 * @moudle: TestSwingBasic 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月22日 下午8:43:02
 *
 */
public class TestSwingBasic {
	
	
	/**更改显示模式*/
	@Test public void _test1(){
		
	    JFrame frame = new JFrame();
	    frame.setSize(800, 600);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		device.setFullScreenWindow(frame);
		device.setDisplayMode(new DisplayMode(800, 600, 32, 60));
		frame.setVisible(true);
		JButton btn = new JButton();
	    btn.setText("Button");
	    JPanel panel = new JPanel();

	    panel.add(btn);
	    frame.add(panel);

		btn.addActionListener(e -> {
			JOptionPane.showMessageDialog(frame.getContentPane(), "JOptionPane");
		});
	}
	
	
	@Test public void _test2(){
		
	    JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JPanel gui = new JPanel(new BorderLayout(5, 5));

		JPanel plafComponents = new JPanel(new FlowLayout(FlowLayout.RIGHT, 3, 3));
		plafComponents.setBorder(new TitledBorder("FlowLayout(FlowLayout.RIGHT, 3,3)"));

		UIManager.LookAndFeelInfo[] plafInfos = UIManager.getInstalledLookAndFeels();
		String[] plafNames = new String[plafInfos.length];
		for (int ii = 0; ii < plafInfos.length; ii++) {
			plafNames[ii] = plafInfos[ii].getName();
	    }
	    JComboBox plafChooser = new JComboBox(plafNames);
	    plafComponents.add(plafChooser);

	    JCheckBox pack = new JCheckBox("Pack on PLAF change", true);
	    plafComponents.add(pack);

		plafChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int index = plafChooser.getSelectedIndex();
				try {
					UIManager.setLookAndFeel(plafInfos[index].getClassName());
					SwingUtilities.updateComponentTreeUI(frame);
					if (pack.isSelected()) {
						frame.pack();
						frame.setMinimumSize(frame.getSize());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	    gui.add(plafComponents, BorderLayout.NORTH);

	    JPanel dynamicLabels = new JPanel(new BorderLayout(4, 4));
	    dynamicLabels.setBorder(new TitledBorder("BorderLayout(4,4)"));
	    gui.add(dynamicLabels, BorderLayout.WEST);

	    final JPanel labels = new JPanel(new GridLayout(0, 2, 3, 3));
	    labels.setBorder(new TitledBorder("GridLayout(0,2,3,3)"));

	    JButton addNew = new JButton("Add Another Label");
	    dynamicLabels.add(addNew, BorderLayout.NORTH);
	    addNew.addActionListener(new ActionListener() {

	      private int labelCount = 0;

	      public void actionPerformed(ActionEvent ae) {
	        labels.add(new JLabel("Label " + ++labelCount));
	        frame.validate();
	      }
	    });

	    dynamicLabels.add(new JScrollPane(labels), BorderLayout.CENTER);

	    String[] header = { "Name", "Value" };
	    String[] a = new String[0];
	    String[] names = System.getProperties().stringPropertyNames().toArray(a);
	    String[][] data = new String[names.length][2];
	    for (int ii = 0; ii < names.length; ii++) {
	      data[ii][0] = names[ii];
	      data[ii][1] = System.getProperty(names[ii]);
	    }
	    DefaultTableModel model = new DefaultTableModel(data, header);
	    JTable table = new JTable(model);
	        
	    JScrollPane tableScroll = new JScrollPane(table);
	    Dimension tablePreferred = tableScroll.getPreferredSize();
	    tableScroll.setPreferredSize(new Dimension(tablePreferred.width,
	        tablePreferred.height / 3));

	    JPanel imagePanel = new JPanel(new GridBagLayout());
	    JLabel imageLabel = new JLabel("test");
	    imagePanel.add(imageLabel, null);

	    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
	        tableScroll, new JScrollPane(imagePanel));
	    gui.add(splitPane, BorderLayout.CENTER);

	    frame.setContentPane(gui);
	    frame.pack();
	    frame.setVisible(true);
	}
	
	@Test public void _test3(){
		
	}
	
	@Test public void _test4(){
		
	}
	
	@Test public void _test5(){
		
	}
	
	@Test public void _test6(){
		
	}
	
	@Test public void _test7(){
		
	}
	
	@Test public void _test8(){
		
	}
	
	@Test public void _test9(){
		
	}
	
	@Test public void _test10(){
		
	}
	
	@Test public void _test11(){
		
	}
	
	@Test public void _test12(){
		
	}
	
	@Test public void _test13(){
		
	}
	
	@Test public void _test14(){
		
	}
	
	@Test public void _test15(){
		
	}
	
	@Test public void _test16(){
		
	}
	
	@Test public void _test17(){
		
	}
	
	@Test public void _test18(){
		
	}
	
	@Test public void _test19(){
		
	}
	
	@Test public void _test20(){
		
	}
	
}
