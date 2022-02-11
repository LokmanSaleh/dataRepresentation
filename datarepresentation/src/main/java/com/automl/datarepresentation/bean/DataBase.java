package com.automl.datarepresentation.bean;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import com.automl.datarepresentation.externalFunctions.DrawLineWithArrow;
import com.automl.datarepresentation.externalFunctions.GraphBuilderPositions;

public class DataBase {
	
	String name;
	String url;
	String user;
	String pass;
	String autoSqlRequestGenerated;
	
	TreeMap<String, Table> tables = new TreeMap<String, Table>();
	
	public DataBase(String url, String user, String pass) {
		super();
		
		// get the name of the data base from the url
		this.name = url.substring(url.lastIndexOf("/")+1, url.length());
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	
	/**
	 * @return the autoSqlRequestGenerated
	 */
	public String getAutoSqlRequestGenerated() {
		return autoSqlRequestGenerated;
	}


	/**
	 * @param autoSqlRequestGenerated the autoSqlRequestGenerated to set
	 */
	public void setAutoSqlRequestGenerated(String autoSqlRequestGenerated) {
		this.autoSqlRequestGenerated = autoSqlRequestGenerated;
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the tables
	 */
	public TreeMap<String, Table> getTables() {
		return tables;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param tables the tables to set
	 */
	public void setTables(TreeMap<String, Table> tables) {
		this.tables = tables;
	}

	/**
	 * generate panel for the all table panel
	 * @return a panel contain the panel of each table
	 */
	public JDesktopPane createDesktopPaneForTables() {

		// contruct graph for the tables. 
		
			// construct the array list of nodes and distinations  
			ArrayList<ArrayList<Number>> listOfEdges = new ArrayList<ArrayList<Number>>();
			int numberOfEdge = 0;
			for (Map.Entry<String, Table> entry : tables.entrySet()) {
				
				Table table = entry.getValue();
				
 				// the destination 
				for (Map.Entry<String, Column> entyColumn : table.getColumns().entrySet()) {
					Column column= entyColumn.getValue();
					if(column.isForeignKey()) {
						
						ArrayList<Number> edge = new ArrayList<Number>();
						
						// the number of the edge
						edge.add(numberOfEdge);
						
						// the arrow come from this node
						edge.add(table.getNumber());
						
						// the destination table
						edge.add(tables.get(column.getParentTable()).getNumber());
						
						listOfEdges.add(edge);
						
						numberOfEdge++;
					}
				}
			}
			
			// Transfer the array list to classic array list 
				Number[][] listOfEdgesT = new Number [listOfEdges.size()][];
				for (int i = 0; i < listOfEdges.size(); i++) {
				    ArrayList<Number> row = listOfEdges.get(i);
				    listOfEdgesT[i] = new Number [row.size()];
				    for (int j = 0;j<row.size(); j++) {
				    	listOfEdgesT[i][j] = row.get(j);
				    }
				}
				
			// build the graph 
 		        GraphBuilderPositions g = new GraphBuilderPositions(listOfEdgesT, tables.size());
		        ArrayList<Point2D> locationsOfTables = g.getListLocationOfTables();
		        
		        // TODO : we can impove the processus by using the name instead the number to get the table
	        // Set the returned location for the tables
		        for (int i = 0 ; i< locationsOfTables.size(); i++) {
		        	for (Map.Entry<String, Table> entry : tables.entrySet()) {
		        		Table table = entry.getValue();
		        		if (table.getNumber() == i ) {
		        			table.setLocation(locationsOfTables.get(i));
		        		}
		        	}
		        }
 
		@SuppressWarnings("serial")
		JDesktopPane jDesktopPane = new JDesktopPane() {

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setColor(Color.lightGray);
				g2d.fillRect(0, 0, getWidth(), getHeight());
				g2d.setColor(Color.blue);
				Stroke s = new BasicStroke(4.0f);
				g2d.setStroke(s);
				
				int x1 = 0, x2 = 0, y1 = 0, y2 = 0;

				Point2D locationEnd = null;
				for (int i = 0; i < listOfEdgesT.length; i++) {

					for (Map.Entry<String, Table> entry : tables.entrySet()) {
						Table table = entry.getValue();

						if ( listOfEdgesT[i][1].equals( table.getNumber())) {
							x1 = tables.get(table.getName()).getJInternalFrame().getX()  + tables.get(table.getName()).getJInternalFrame().getWidth() / 2;
							y1 = tables.get(table.getName()).getJInternalFrame().getY()  + tables.get(table.getName()).getJInternalFrame().getHeight() / 2;

						} else if (listOfEdgesT[i][2].equals( table.getNumber())) {
							x2 = tables.get(table.getName()).getJInternalFrame().getX() + tables.get(table.getName()).getJInternalFrame().getWidth()/2 ;
							y2 = tables.get(table.getName()).getJInternalFrame().getY()  + tables.get(table.getName()).getJInternalFrame().getHeight() ;
							locationEnd = new Point(x2, y2);
						}
					}

				    DrawLineWithArrow.draw(g2d, x1, y1, locationEnd, s, s, 20);
		        }

			}};
 
			// create panel for each table in the structure
			for (Map.Entry<String, Table> entry : tables.entrySet()) {

				Table table = entry.getValue();

				table.createJinternalFrameForTable();
				JInternalFrame currentInternalFrame = table.getJInternalFrame();
				currentInternalFrame.addComponentListener(new ComponentAdapter() {

					@Override
					public void componentMoved(ComponentEvent e) {
						jDesktopPane.repaint(); 
					}
				});
				jDesktopPane.add(currentInternalFrame);

			}
		
		jDesktopPane.setPreferredSize(new Dimension(740, 740));
 
		return jDesktopPane;
	}
}
	
	

