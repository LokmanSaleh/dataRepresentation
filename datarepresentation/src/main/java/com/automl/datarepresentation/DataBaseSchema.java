package com.automl.datarepresentation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.automl.datarepresentation.bd.SQLrequest;
import com.automl.datarepresentation.bean.Column;
import com.automl.datarepresentation.bean.Table;
import com.automl.datarepresentation.service.SQLAutoGenerated;
import com.automl.datarepresentation.view.ConnectionInterface;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;

/**
 * Hello world!
 *
 */
public class DataBaseSchema {

	public DataBaseSchema(Connection con) {
		visualizeDataBaseSchema(con);
	}

	void visualizeDataBaseSchema (Connection con){

		try {
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
			
			// when clicking 
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					SQLAutoGenerated.generateRequest(tables);
					
					JOptionPane.showMessageDialog(frame, SQLAutoGenerated.getRequest());
				}
			});
			
			buttonJPanel.add(button);
			
			tablesContainer.add(buttonJPanel);
			
		    frame.getContentPane().add(tablesContainer);
		    //frame.getContentPane().add(buttonJPanel);

		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.pack();
		    frame.setVisible(true);
 
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 
	}
}
