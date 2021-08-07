package com.automl.datarepresentation.controller;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.automl.datarepresentation.bean.DataBase;
import com.automl.datarepresentation.service.DataSchemaService;

/**
 * Hello world!
 *
 */
public class DataBaseSchemaController {

	DataBase dataBase;
	DataSchemaService dataSchemaService;
 
	public DataBaseSchemaController(DataSchemaService dataSchemaService, DataBase dataBase) throws SQLException {
		super();
		this.dataSchemaService = dataSchemaService;
 		this.dataBase = dataBase;
		
		visualizeDataBaseSchema();
	}

	/**
	 * @throws SQLException 
	 * 
	 */
	void visualizeDataBaseSchema() throws SQLException {

		dataBase.setTables(dataSchemaService.getTables());

		JFrame frame = new JFrame("Auto generated SQL request");

		JPanel dataBasePanel = dataBase.createPanelForEachTable();

		JPanel buttonJPanel = new JPanel();
		JButton createSqlRequestButton = new JButton("create SQL request");

		// on click button listener
		dataSchemaService.createSqlRequestButtonListener(createSqlRequestButton, frame, dataBase);

		buttonJPanel.add(createSqlRequestButton);

		dataBasePanel.add(buttonJPanel);

		frame.getContentPane().add(dataBasePanel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		// on close frame listener
		dataSchemaService.onCloseFrameListner(frame);

	}
}
