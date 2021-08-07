package com.automl.datarepresentation.bean;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;

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
	public JPanel createPanelForEachTable() {

		JPanel mainJpanel = new JPanel();

	    // create panel for each table in the structure
		for (Map.Entry<String, Table> entry : tables.entrySet()) {

			Table table = entry.getValue();
			
			table.createPanelForTable();
			mainJpanel.add(table.getPanel());
		}
		return mainJpanel;
	}
}
