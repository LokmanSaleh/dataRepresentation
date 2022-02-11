package com.automl.datarepresentation.bean;

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
import java.awt.geom.Point2D;
import java.util.ArrayList;
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
 
public class JDesktopPaneCustom extends JDesktopPane {

	private static final Stroke s = new BasicStroke(4.0f);
	ArrayList<ArrayList<Point2D>> locationOfEdges;
	
	public JDesktopPaneCustom(ArrayList<ArrayList<Point2D>> locationOfEdges) {
		super();
		this.locationOfEdges = locationOfEdges;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.lightGray);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setColor(Color.blue);
        g2d.setStroke(s);

		for (ArrayList<Point2D> locationOfEdge : locationOfEdges) {
			int x1 = (int) locationOfEdge.get(0).getX() + 30;// + one.getWidth() / 2;
			int y1 = (int) locationOfEdge.get(0).getY();// + one.getHeight() / 2;
			int x2 = (int) locationOfEdge.get(1).getX() + 30;// + two.getWidth() / 2;
			int y2 = (int) locationOfEdge.get(1).getY();// + two.getHeight() / 2;

			CubicCurve2D cubcurve = new CubicCurve2D.Float(x1, y1, x1 + 200, y1 - 115, x2 - 200, y2 + 115, x2, y2);
			g2d.draw(cubcurve);
			// break;
		}
	}
}
