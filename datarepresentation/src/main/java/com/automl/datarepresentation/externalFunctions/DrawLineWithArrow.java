package com.automl.datarepresentation.externalFunctions;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class DrawLineWithArrow {
	
	public static void draw (final Graphics2D gfx,  double startx, double starty, Point2D end, final Stroke lineStroke, final Stroke arrowStroke, final float arrowSize) {
		//https://gist.github.com/raydac/df97493f58b0521fb20a 
	    gfx.setStroke(arrowStroke);
	    final double deltax = startx - end.getX();
	    final double result;
	    if (deltax == 0.0d) {
	      result = Math.PI / 2;
	    }
	    else {
	      result = Math.atan((starty - end.getY()) / deltax) + (startx < end.getX() ? Math.PI : 0);
	    }

	    final double angle = result;

	    final double arrowAngle = Math.PI / 12.0d;

	    final double x1 = arrowSize * Math.cos(angle - arrowAngle);
	    final double y1 = arrowSize * Math.sin(angle - arrowAngle);
	    final double x2 = arrowSize * Math.cos(angle + arrowAngle);
	    final double y2 = arrowSize * Math.sin(angle + arrowAngle);

	    final double cx = (arrowSize / 2.0f) * Math.cos(angle);
	    final double cy = (arrowSize / 2.0f) * Math.sin(angle);

	    final GeneralPath polygon = new GeneralPath();
	    polygon.moveTo(end.getX(), end.getY());
	    polygon.lineTo(end.getX() + x1, end.getY() + y1);
	    polygon.lineTo(end.getX() + x2, end.getY() + y2);
	    polygon.closePath();
	    gfx.fill(polygon);

	    gfx.setStroke(lineStroke);
	    gfx.drawLine((int) startx, (int) starty, (int) (end.getX() + cx), (int) (end.getY() + cy));
	  }
}
