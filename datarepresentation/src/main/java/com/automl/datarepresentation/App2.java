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
		    
		    JPanel container = new JPanel();
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(container);
	        container.setLayout(layout);
		    SequentialGroup sqGr1 = layout.createSequentialGroup();
		    
		    SequentialGroup sqGr2 = layout.createSequentialGroup();
		    sqGr2.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING));
                
		    // create panel for each table in the structure
			for (Map.Entry<String, Table> entry : tables.entrySet()) {

				Table table = entry.getValue();
				
				table.createPanelForTable();
				sqGr1.addComponent(table.getPanel(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				 	.addGap(38, 38, 38)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addGap(38, 38, 38);
                sqGr2.addComponent(table.getPanel(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
		
			}
	    

	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(sqGr1));
	        
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(sqGr2)
	                 )
	                .addContainerGap(339, Short.MAX_VALUE))
	        );
		    

		    frame.getContentPane().add(container);
 
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.pack();
		    frame.setVisible(true);

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
