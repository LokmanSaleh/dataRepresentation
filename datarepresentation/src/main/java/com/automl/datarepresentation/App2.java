package com.automl.datarepresentation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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

				        
 		    JFrame frame = new JFrame();
		    
		    JPanel tablesContainer = new JPanel();
		    
		    // create panel for each table in the structure
			for (Map.Entry<String, Table> entry : tables.entrySet()) {

				Table table = entry.getValue();
				
				table.createPanelForTable();
				tablesContainer.add(table.getPanel());
			}
			
			JPanel buttonJPanel = new JPanel();
			JButton button = new JButton("create SQL request");
			
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					SQLAutoGenerated.generateRequest(tables);
					
					System.out.println(SQLAutoGenerated.getRequest());
				}
			});
			
			
			
			buttonJPanel.add(button);
			
			
			tablesContainer.add(buttonJPanel);
			
		    frame.getContentPane().add(tablesContainer);
		    //frame.getContentPane().add(buttonJPanel);

		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.pack();
		    frame.setVisible(true);

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
