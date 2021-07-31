package com.automl.datarepresentation;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.renderers.Renderer.Vertex;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;

public class MyRenderer implements Vertex<JPanel, Number> {
	
	JPanel panel1, panel2;

	public MyRenderer(JPanel panel1, JPanel panel2) {
		super();
		this.panel1 = panel1;
		this.panel2 = panel2;
	}

	@Override
	public void paintVertex(RenderContext<JPanel, Number> rc, Layout<JPanel, Number> layout, JPanel vertex) {
		// TODO Auto-generated method stub
        GraphicsDecorator graphicsContext = rc.getGraphicsContext();
        
        Point2D center = layout.transform(vertex);
        
        Dimension size = new Dimension(200, 400);

      //  System.out.printf("Vertex[%d] X = %d Y = %d: Running paintVertex()\n", vertex, (int)center.getX(), (int)center.getY());

        JPanel sv = new JPanel();
        sv.add(vertex);
       // sv.add(panel2);
        //OK
        graphicsContext.draw(sv, rc.getRendererPane(), (int)center.getX(), 
                             (int)center.getY(), size.width, size.height, true);
	}

}
