package com.automl.datarepresentation;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

public class Table {
	
	private String name;
	private TreeMap<String, Column> columns = new TreeMap<String, Column> () ;
	private JPanel panel = new JPanel();
	
	// for auto SQL request generation
	private String select = "";
	private String from = "";
	private String where = "";
	
	// The constructor
	public Table(String name) {
		super();
		this.name = name;
	}
	

	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * @return the columns
	 */
	public TreeMap<String, Column> getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(TreeMap<String, Column> columns) {
		this.columns = columns;
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
	 * @return the select
	 */
	public String getSelect() {
		return select;
	}


	/**
	 * @param select the select to set
	 */
	public void setSelect(String select) {
		this.select = select;
	}


	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}


	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}


	/**
	 * @return the where
	 */
	public String getWhere() {
		return where;
	}


	/**
	 * @param where the where to set
	 */
	public void setWhere(String where) {
		this.where = where;
	}

	/**
	 * Verify is any column has been selected
	 * @return true is any column has been selected
	 */
	public boolean isAnyColumnSelected () {
		
		// if one of the attribute is checked 
		for (Map.Entry<String, Column> column : columns.entrySet()) {
			
			if (column.getValue().getCheckBox().isSelected()) 
				return true;
			
		}
		return false;
	}

	/**
	 * Generate the Select, From , Where for each table 
	 */
	public void generateSelectFromWhere (TreeMap<String, Table> tables) {
		
		/**
		 * Select : The selected attribute.
		 * From : The current table.
		 * Where : contain the foreign keys in the table, either selected or not,
		 *  	   but the parent table of each foreign key has to have at least one column selected,
		 * 		   to be added in the where.
		 */
		
		select = from = where = "";
 		
		// if one of the attribute checked, add the table on the join 
		if (isAnyColumnSelected()) {
			
			for (Map.Entry<String, Column> column : columns.entrySet() ) {
				
				select += (column.getValue().getCheckBox().isSelected()) ? name+"."+column.getKey()+", " : "";
				
				where += (column.getValue().isForeignKey() && tables.get(column.getValue().getParentTable()).isAnyColumnSelected()) ? 
							(name +"."+column.getValue().getName() +
							  "=" + column.getValue().getParentTable()+"."+column.getValue().getParentTableColumn() + " AND ") :
							("");
			}
			
			from = name +", ";
		}
	}
	
    public void createPanelForTable () {
    	  	
        JLabel classTitle = new javax.swing.JLabel();
        
        classTitle.setText(name);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(panel);
        
        panel.setLayout(jPanelLayout);
       
        ParallelGroup parallGr = jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        SequentialGroup sequeGr = jPanelLayout.createSequentialGroup()
										        .addComponent(classTitle)
										        .addGap(9, 9, 9);
        
        // add the checkBox to the panel
    	for (Map.Entry<String, Column> column : columns.entrySet()) {
	    		Column currentColumn = column.getValue();
	    		parallGr.addComponent(currentColumn.getCheckBox());
	    		sequeGr.addComponent(currentColumn.getCheckBox())
	    		 	   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    	}
    	
        jPanelLayout.setHorizontalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLayout.createSequentialGroup()
                    .addGroup( parallGr )
                    .addGap(0, 21, Short.MAX_VALUE))
                .addGroup(jPanelLayout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(classTitle)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        
        jPanelLayout.setVerticalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(sequeGr )
            );

       // setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel.setToolTipText("");

    }
}
