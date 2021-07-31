package com.automl.datarepresentation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.SequentialGroup;

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

		    frame.getContentPane().add(tablesContainer);
		    
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.pack();
		    frame.setVisible(true);

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
