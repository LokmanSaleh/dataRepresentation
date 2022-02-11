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
    int x1 ;//= one.getX() ;//+ one.getWidth() / 2;
    int y1;// = one.getY();// + one.getHeight() / 2;
    
    int x2 ;//= two.getX() ;//+ two.getWidth() / 2;
    int y2 ;//= two.getY() ;//+ two.getHeight() / 2;
    
    CubicCurve2D cubcurve;
    
    public JDPTest2(int x1, int y1, int x2, int y2) {
    	super();
    	this.setPreferredSize(new Dimension(640, 480));
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
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
        int x1 = this.getAllFrames()[0].getX();
        int y1 = this.getAllFrames()[0].getY();
        int x2 = this.getAllFrames()[1].getX() ;
        int y2 = this.getAllFrames()[1].getY();
        cubcurve = new CubicCurve2D.Float(x1,y1,x1+200,y1-115,x2-200,y2+115,x2,y2);
        g2d.draw(cubcurve);
 
       // g2d.drawLine(x1, y1, x2, y2);
    }



    private void display( ) {

         
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
            	JDPTest2 jDPTest2 = new JDPTest2(380, 100, 200, 280);
            	
                MyFrame one = new MyFrame("One", 380, 100, jDPTest2);
                MyFrame two = new MyFrame("Two", 200, 280, jDPTest2);
                jDPTest2.add(one);
                jDPTest2.add(two);

                jDPTest2.display();
                 
            }
        });
    }
}

class MyFrame extends JInternalFrame {

    MyFrame(String name, int x, int y, JDesktopPane jDesktopPane) {
        super(name);
        this.setSize(160, 40);
        this.setLocation(x, y);
        this.setVisible(true);
        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentMoved(ComponentEvent e) {
            	jDesktopPane.repaint();
            }
        });
    }
}