package com.automl.algorithmsRepresentation.bean.selectionCriteria;

import java.io.Serializable;

// in the first version of the project, the criteria are already fixed  
public class SelectionCriteria implements Serializable{

	private String name;

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
}
