package com.automl.datarepresentation.bean;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import com.automl.visualizationTest.GraphPanel2;

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

		JDesktopPane jDesktopPane = new JDesktopPane();

		// contruct graph for the tables. 
		
			// construct the array list of nodes and distinations  
			ArrayList<ArrayList<Number>> listOfNodesAndConnections = new ArrayList<ArrayList<Number>>();
			for (Map.Entry<String, Table> entry : tables.entrySet()) {
				
				ArrayList<Number> node = new ArrayList<Number>();
				Table table = entry.getValue();
				
				// the number of the node
				node.add(table.getNumber());
				
				// the arrow come from this node
				node.add(table.getNumber());
				
				// TODO: No only we add one destination For each table, 
				// the destination 
				for (Map.Entry<String, Column> entyColumn : table.getColumns().entrySet()) {
					Column column= entyColumn.getValue();
					if(column.isForeignKey()) {
						node.add(tables.get(column.getParentTable()).getNumber());
						break;
					}
				}
				
				if (node.size() < 3) {
					node.add(table.getNumber());
				}
				listOfNodesAndConnections.add(node);
			}
			
			// Transfer the array list to classic array list 
				Number[][] list = new Number [listOfNodesAndConnections.size()][];
				for (int i = 0; i < listOfNodesAndConnections.size(); i++) {
				    ArrayList<Number> row = listOfNodesAndConnections.get(i);
				    list[i] = new Number [row.size()];
				    for (int j = 0;j<row.size(); j++) {
				    	list[i][j] = row.get(j);
				    }
				}
				
			// build the graph 
 		        GraphPanel2 g = new GraphPanel2(list);
		        ArrayList<Point2D> locationsOfTables = g.getListLocationOfTables();
		        
		        // TODO : we can impove the processus by using the name isntead the number to get the table
	        // Set the returned location for the tables
		        for (int i = 0 ; i< locationsOfTables.size(); i++) {
		        	for (Map.Entry<String, Table> entry : tables.entrySet()) {
		        		Table table = entry.getValue();
		        		if (table.getNumber() == i ) {
		        			table.setLocation(locationsOfTables.get(i));
		        		}
		    			
		        	}
		        }
			
	    // create panel for each table in the structure
		for (Map.Entry<String, Table> entry : tables.entrySet()) {

			Table table = entry.getValue();
			
			table.createJinternalFrameForTable();
			JInternalFrame currentInternalFrame = table.getJInternalFrame();
			
			jDesktopPane.add(currentInternalFrame);
		}
		jDesktopPane.setPreferredSize(new Dimension(640, 480));
		return jDesktopPane;
	}
}
