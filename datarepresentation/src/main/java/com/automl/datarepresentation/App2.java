package com.automl.datarepresentation;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;

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

			
			// tests 
		    DirectedSparseGraph<JPanel, Number> graph = new DirectedSparseGraph<JPanel, Number>();
		    
		    tables.get("customer").createPanelForTable();
		    tables.get("order").createPanelForTable();
		    
		    JPanel panel1=tables.get("customer").getPanel();
		    JPanel panel2=tables.get("order").getPanel();
		    
		    graph.addVertex(panel1);
		    graph.addVertex(panel2);
 
			
		    graph.addEdge(1, panel1, panel2);

			VisualizationViewer<JPanel, Number> vv = new VisualizationViewer<JPanel, Number> 
			  											(new CircleLayout<JPanel, Number>(graph), new Dimension(400, 400));
	        
			vv.getRenderer().setVertexRenderer(new MyRenderer(panel1, panel2));
		    // The following code adds capability for mouse picking of vertices/edges. Vertices can even be moved!
		    final DefaultModalGraphMouse<String,Number> graphMouse = new DefaultModalGraphMouse<String,Number>();
		    vv.setGraphMouse(graphMouse);
		    graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
		    
		    JFrame frame = new JFrame();
		    frame.setContentPane(vv);
	        frame.getContentPane().setPreferredSize(new Dimension(640, 480));
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();
	        frame.setVisible(true);
	        
	        // others 
//		    JFrame frame = new JFrame();
//		    
//		    JPanel tablesContainer = new JPanel();
//		    
//		    // create panel for each table in the structure
//			for (Map.Entry<String, Table> entry : tables.entrySet()) {
//
//				Table table = entry.getValue();
//				
//				table.createPanelForTable();
//				tablesContainer.add(table.getPanel());
//			}
//			
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

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
