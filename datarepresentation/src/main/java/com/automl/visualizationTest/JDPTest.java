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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
//https://stackoverflow.com/questions/3951383/cubiccurve2d-connecting-two-jinternalframe-instances/3952082#3952082
/** @see http://stackoverflow.com/questions/3951383 */
public class JDPTest extends JDesktopPane {

    private static final Stroke s = new BasicStroke(4.0f);
    private MyFrame one = new MyFrame("One", 380, 100);
    private MyFrame two = new MyFrame("Two", 200, 280);
    
    CubicCurve2D cubcurve;
    public JDPTest() {
        this.setPreferredSize(new Dimension(640, 480));
        
        JPanel sv = new JPanel();
        sv.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        sv.setBackground(Color.GREEN);
        Dimension size = new Dimension(100, 80);
        sv.setPreferredSize(size);
        sv.add(new JCheckBox("Button1"));
        sv.add(new JButton("Hello"));
        
        one.add(sv);
        this.add(one);
        this.add(two);
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
        int x1 = one.getX() + one.getWidth() / 2;
        int y1 = one.getY() + one.getHeight() / 2;
        int x2 = two.getX() + two.getWidth() / 2;
        int y2 = two.getY() + two.getHeight() / 2;

        cubcurve = new CubicCurve2D.Float(x1,y1,x1+200,y1-115,x2-200,y2+115,x2,y2);
        g2d.draw(cubcurve);

       // g2d.drawLine(x1, y1, x2, y2);
    }

    private final class MyFrame extends JInternalFrame {

        MyFrame(String name, int x, int y) {
            super(name);
            this.setSize(160, 100);
            this.setLocation(x, y);
            this.setVisible(true);
            this.addComponentListener(new ComponentAdapter() {

                @Override
                public void componentMoved(ComponentEvent e) {
                    JDPTest.this.repaint();
                }
            });
        }
    }

    private void display() {
        JFrame f = new JFrame("JDPTest");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {


            public void run() {
                new JDPTest().display();
            }
        });
    }
}