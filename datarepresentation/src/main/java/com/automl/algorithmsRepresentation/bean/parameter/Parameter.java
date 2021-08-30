package com.automl.algorithmsRepresentation.bean.parameter;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Parameter implements Serializable {
 
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
