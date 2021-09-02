package com.automl.algorithmsRepresentation.bean.parameter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.automl.algorithmsRepresentation.bean.Interval;

@SuppressWarnings("serial")
public class Parameter implements Serializable {
 
 	private String name;
 	private List<Interval> intervals = new ArrayList<Interval>();
 	
	public Parameter(String name) {
		super();
		this.name = name;
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
	
	public void addInterval(Interval inter) {
		intervals.add(inter);
	}

	@Override
	public String toString() {
		return "Parameter [name=" + name + ", intervals=" + intervals + "]";
	}
	
}
