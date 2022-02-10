package com.automl.visualizationTest;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.CubicCurve2D;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

import com.automl.datarepresentation.bean.Column;
//https://stackoverflow.com/questions/3951383/cubiccurve2d-connecting-two-jinternalframe-instances/3952082#3952082
/** @see http://stackoverflow.com/questions/3951383 */
public class JDPTest2 extends JDesktopPane {

    private static final Stroke s = new BasicStroke(4.0f);
    private MyFrame one = new MyFrame("One", 380, 100);
    private MyFrame two = new MyFrame("Two", 200, 280);
    private MyFrame three = new MyFrame("Three", 300, 300);
    
    CubicCurve2D cubcurve;
    public JDPTest2() {
        this.setPreferredSize(new Dimension(640, 480));
        
        TreeMap<String, Column> columns = new TreeMap<String, Column> () ;
        columns.put("column1", new Column("table", "column1", false, null, null));
        columns.put("column2", new Column("table2", "column2", false, null, null));
        columns.put("column3", new Column("table3", "column3", false, null, null));
        JPanel panel = new JPanel();
        
        JLabel classTitle = new javax.swing.JLabel();
        
       // classTitle.setText("hello title");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(panel);
        
        panel.setLayout(jPanelLayout);
       
        ParallelGroup parallGr = jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        SequentialGroup sequeGr = jPanelLayout.createSequentialGroup()
										        .addComponent(classTitle)
										        .addGap(9, 9, 9);
        
        // add the checkBox to the panel
    	for (Map.Entry<String, Column> column : columns.entrySet()) {
	    		Column currentColumn = column.getValue();
	    		parallGr.addComponent(currentColumn.getCheckBox());
	    		sequeGr.addComponent(currentColumn.getCheckBox())
	    		 	   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    	}
    	
        jPanelLayout.setHorizontalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                    .addGroup( parallGr )
                    .addGap(0, 21, Short.MAX_VALUE))
                .addGroup(jPanelLayout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(classTitle)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        
        jPanelLayout.setVerticalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(sequeGr )
            );

       // setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel.setToolTipText("");
        
        
        
        
        
         one.add(panel);
        this.add(one);
        	one.setSize(160, 40*columns.size());
        	
        	
        this.add(two);
        this.add(three);

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.lightGray);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.blue);
        g2d.setStroke(s);
        int x1 = one.getX() ;//+ one.getWidth() / 2;
        int y1 = one.getY();// + one.getHeight() / 2;
        
        int x2 = two.getX() ;//+ two.getWidth() / 2;
        int y2 = two.getY() ;//+ two.getHeight() / 2;

        int x3 = three.getX() ;//+ three.getWidth() / 2;
        int y3 = three.getY() ;//+ three.getHeight() / 2;
        
        cubcurve = new CubicCurve2D.Float(x1,y1,x1+200,y1-115,x2-200,y2+115,x2,y2);
        g2d.draw(cubcurve);

        cubcurve = new CubicCurve2D.Float(x1,y1,x1+200,y1-115,x3-200,y3+115,x3,y3);
        g2d.draw(cubcurve);
        
       // g2d.drawLine(x1, y1, x2, y2);
    }

    private final class MyFrame extends JInternalFrame {

        MyFrame(String name, int x, int y) {
            super(name);
            this.setSize(160, 40);
            this.setLocation(x, y);
            this.setVisible(true);
            this.addComponentListener(new ComponentAdapter() {

                @Override
                public void componentMoved(ComponentEvent e) {
                    JDPTest2.this.repaint();
                }
            });
        }
    }

    private void display() {
        JFrame f = new JFrame("JDPTest");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
       
        panel.add(this);
        
        JButton buton = new JButton("SQL request");
        panel.add(buton);
        
        f.add(panel);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {


            public void run() {
                new JDPTest2().display();
            }
        });
    }
}