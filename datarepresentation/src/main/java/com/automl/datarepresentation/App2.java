package com.automl.datarepresentation;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphModel;

/**
 * Hello world!
 *
 */
public class App2 {

	public static void main(String args[]) {

		String url = "jdbc:mysql://localhost:3306/mydb";
		String user = "root";
		String password = "";

		try {

			Connection con = DriverManager.getConnection(url, user, password);

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(SQLrequest.GET_RELATIONAL_DB_STUCTURE);

			// nom du table, nom du colomn, et contenue de colomn
			TreeMap<String, Table> tables = new TreeMap<String, Table>();

			// TODO: a amliorer
			while (rs.next()) {

				if (tables.get(rs.getString("tableName")) == null) {

					tables.put(rs.getString("tableName"), new Table(rs.getString("tableName")));

					tables.get(rs.getString("tableName")).getColumns().put(rs.getString("columnName"),
							new Column(rs.getString("tablename"), rs.getString("columnName"), rs.getBoolean("isforeignkey"),
									rs.getString("parentTable"), rs.getString("parentColumn")));

				} else {
					tables.get(rs.getString("tableName")).getColumns().put(rs.getString("columnName"),
							new Column(rs.getString("tablename"), rs.getString("columnName"), rs.getBoolean("isforeignkey"),
									rs.getString("parentTable"), rs.getString("parentColumn")));
				}

			}

				        
 		   // JFrame frame = new JFrame();
		    
		  //  JPanel tablesContainer = new JPanel();
		    
		    // create panel for each table in the structure
			for (Map.Entry<String, Table> entry : tables.entrySet()) {

				Table table = entry.getValue();
				
				table.createPanelForTable();
			//	tablesContainer.add(table.getPanel());
			}
			
//			JPanel buttonJPanel = new JPanel();
//			
//			buttonJPanel.add(new JButton("create SQL request"));
//			
//			tablesContainer.add(buttonJPanel);
//		    frame.getContentPane().add(tablesContainer);
//		   // frame.getContentPane().add(buttonJPanel);
//
//		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		    frame.pack();
//		    frame.setVisible(true);
		    
		    // tests 
 
				 
				// Construct Model and Graph
				GraphModel model = new DefaultGraphModel();
				JGraph graph = new JGraph(model);
		 
				// Control-drag should clone selection
				graph.setCloneable(true);
		 
				// Enable edit without final RETURN keystroke
				graph.setInvokesStopCellEditing(true);
		 
				// When over a cell, jump to its default port (we only have one, anyway)
				graph.setJumpToDefaultPort(true);
		 
				// Insert all three cells in one call, so we need an array to store them
				DefaultGraphCell[] cells = new DefaultGraphCell[3];
		 
				// Create Hello Vertex
				cells[0] = createVertex(tables.get("customer").getPanel(), 20, 20, 40, 20, null, false);
		 
				// Create World Vertex
				cells[1] = createVertex(tables.get("product").getPanel(), 140, 140, 40, 20, Color.ORANGE, true);
		 
				// Create Edge
				DefaultEdge edge = new DefaultEdge();
				// Fetch the ports from the new vertices, and connect them with the edge
				edge.setSource(cells[0].getChildAt(0));
				edge.setTarget(cells[1].getChildAt(0));
				cells[2] = edge;
		 
				// Set Arrow Style for edge
				int arrow = GraphConstants.ARROW_CLASSIC;
				GraphConstants.setLineEnd(edge.getAttributes(), arrow);
				GraphConstants.setEndFill(edge.getAttributes(), true);
		 
				// Insert the cells via the cache, so they get selected
				graph.getGraphLayoutCache().insert(cells);
		 
				// Show in Frame
				JFrame frame = new JFrame();
				frame.getContentPane().add(new JScrollPane(graph));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);
	 

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static DefaultGraphCell createVertex(JPanel panel, double x,
			double y, double w, double h, Color bg, boolean raised) {
 
		// Create vertex with the given name
		DefaultGraphCell cell = new DefaultGraphCell(panel);
 
		// Set bounds
		GraphConstants.setBounds(cell.getAttributes(), new Rectangle2D.Double(
				x, y, w, h));
 
		// Set fill color
		if (bg != null) {
			GraphConstants.setGradientColor(cell.getAttributes(), bg);
			GraphConstants.setOpaque(cell.getAttributes(), true);
		}
 
		// Set raised border
		if (raised)
			GraphConstants.setBorder(cell.getAttributes(), BorderFactory
					.createRaisedBevelBorder());
		else
			// Set black border
			GraphConstants.setBorderColor(cell.getAttributes(), Color.black);
 
		// Add a Floating Port
		cell.addPort();
 
		return cell;
	}
	
}
