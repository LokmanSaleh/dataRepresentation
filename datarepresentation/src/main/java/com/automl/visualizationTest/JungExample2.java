package com.automl.visualizationTest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;

/**
 * Second JUNG example:
 * Three vertices and three edges.
 * Vertices and edges have labels.
 * The figure of the vertices depends on the label in the vertex.
 * The edges and the vertices can be picked.
 * 
 * @author Arieh 'Vainolo' Bibliowicz
 * 
 */
//https://github.com/vainolo/Vainosamples/blob/master/cleanJava/src/com/vainolo/examples/jung/JungExample2.java
public class JungExample2 extends javax.swing.JFrame {
	private static javax.swing.JCheckBox jCheckBox3 = new JCheckBox("hello");
	
  public static void main(String[] args) {
    DirectedSparseGraph<String, String> g = new DirectedSparseGraph<String, String>();
    g.addVertex("");
    g.addVertex("Rectangle");
    g.addVertex("Circle");
    g.addEdge("Edge1", "Square", "Rectangle");
    g.addEdge("Edge2", "Square", "Circle");
    g.addEdge("Edge3", "Circle", "Square");
    
    VisualizationViewer<String, String> vv =
        new VisualizationViewer<String, String>(new CircleLayout<String, String>(g), new Dimension(400, 400));

    vv.getRenderContext().setVertexLabelTransformer(new Transformer<String, String>() {
      @Override
      public String transform(String arg0) {
        return arg0;
      }
    });
    vv.getRenderContext().setEdgeLabelTransformer(new Transformer<String, String>() {
      @Override
      public String transform(String arg0) {
        return arg0;
      }
    });

    vv.getRenderer().setVertexRenderer(new MyRenderer());

    final DefaultModalGraphMouse<String, Number> graphMouse = new DefaultModalGraphMouse<String, Number>();
    graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
    vv.setGraphMouse(graphMouse);

    JFrame frame = new JFrame();
    frame.getContentPane().add(vv);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
  
  private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {                                           
      // TODO add your handling code here:
	  System.out.println("i listen");
  }   
  
  static class MyRenderer implements Renderer.Vertex<String, String> {
    @Override
    public void paintVertex(RenderContext<String, String> rc, Layout<String, String> layout, String vertex) {
      GraphicsDecorator graphicsContext = rc.getGraphicsContext();
      
      Point2D center = layout.transform(vertex);
      
      Shape shape = null;
      Color color = null;
      if(vertex.equals("")) {
    	  
//    	JPanel sv = new JPanel();
//        sv.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//        sv.setBackground(Color.GREEN);
        Dimension size = new Dimension(100, 300);
//        sv.setPreferredSize(size);
//        jCheckBox3.setSelected(true);
//        sv.add(jCheckBox3);		
//        
//        
//        
        

        javax.swing.JCheckBox jCheckBox10;
        javax.swing.JCheckBox jCheckBox11;
        javax.swing.JCheckBox jCheckBox3;
        javax.swing.JCheckBox jCheckBox4;
        javax.swing.JCheckBox jCheckBox5;
        javax.swing.JCheckBox jCheckBox6;
        javax.swing.JCheckBox jCheckBox8;
        javax.swing.JCheckBox jCheckBox9;
        javax.swing.JLabel jLabel1;
        javax.swing.JLabel jLabel2;
        javax.swing.JLabel jLabel3;
        javax.swing.JPanel jPanel1;
        javax.swing.JPanel jPanel3;
        
        jPanel1 = new javax.swing.JPanel();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setToolTipText("");

        jCheckBox4.setText("jCheckBox4");

        jCheckBox5.setText("jCheckBox5");

        jCheckBox6.setText("jCheckBox6");

        jLabel1.setText("Class1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6))
                .addGap(0, 21, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox6))
        );
        
        graphicsContext.draw(jPanel1, rc.getRendererPane(), (int)center.getX(), 
                (int)center.getY(), size.width, size.height, true);
        
 
      } else if(vertex.equals("Rectangle")) {
        shape = new Rectangle((int) center.getX() - 10, (int) center.getY() - 20, 20, 40);
        color = new Color(127, 0, 127);
        graphicsContext.setPaint(color);
        graphicsContext.fill(shape);
      } else if(vertex.equals("Circle")) {
        shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 20, 20);
        color = new Color(0, 127, 127);
        graphicsContext.setPaint(color);
        graphicsContext.fill(shape);
      }

      //System.out.println("heklo");
    }

  }
}