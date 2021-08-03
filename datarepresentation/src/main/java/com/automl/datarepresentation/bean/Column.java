package com.automl.datarepresentation.bean;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

public class Column {

	private String name;
	private String tableName;
	private Boolean foreignKey;
	private String parentTable;
	private String parentTableColumn;
	private JCheckBox checkBox;

	public Column(String tablename, String name, Boolean foreignKey, String parentTable, String parentTableColumn) {
		
		this.tableName = tablename;
		this.name = name;
		this.foreignKey = foreignKey;
		this.parentTable = parentTable;
		this.parentTableColumn = parentTableColumn;
		
		String labelValue = (foreignKey) ? (name + " --> T(" + parentTable + "), A(" + parentTableColumn + ")")
										 : (name);

		checkBox = new JCheckBox();
		checkBox.setText(labelValue);
		
		// add listener to the checkbox
		checkBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox cb = (JCheckBox) e.getSource();
				
				if (cb.isSelected()) {
					System.out.println(name + " of " + tablename + " is selected");
				} else {
					System.out.println(name + " of " + tablename +" is Unselected");
				}
			}
		});
		
	}

	
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the checkBox
	 */
	public JCheckBox getCheckBox() {
		return checkBox;
	}

	/**
	 * @param checkBox the checkBox to set
	 */
	public void setCheckBox(JCheckBox checkBox) {
		this.checkBox = checkBox;
	}

	@Override
	public String toString() {
		return "Column [name=" + name + ", foreignKey=" + foreignKey + ", parentTable=" + parentTable
				+ ", parentTableColumn=" + parentTableColumn + "]";
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
	 * @return the foreignKey
	 */
	public Boolean isForeignKey() {
		return foreignKey;
	}

	/**
	 * @param foreignKey the foreignKey to set
	 */
	public void setForeignKey(Boolean foreignKey) {
		this.foreignKey = foreignKey;
	}

	/**
	 * @return the parentTable
	 */
	public String getParentTable() {
		return parentTable;
	}

	/**
	 * @param parentTable the parentTable to set
	 */
	public void setParentTable(String parentTable) {
		this.parentTable = parentTable;
	}

	/**
	 * @return the parentTableColumn
	 */
	public String getParentTableColumn() {
		return parentTableColumn;
	}

	/**
	 * @param parentTableColumn the parentTableColumn to set
	 */
	public void setParentTableColumn(String parentTableColumn) {
		this.parentTableColumn = parentTableColumn;
	}
}
