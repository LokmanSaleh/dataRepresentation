package com.automl.algorithmsRepresentation.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Interval implements Serializable{
	
	private int min = 0;
	private int max = Integer.MAX_VALUE;
	
	public Interval() {
		super();
	}

	public Interval(int min, int max) {
		super();
		this.min = min;
		this.max = max;
	}
	
	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}
	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}
	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}
	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "Interval [min=" + min + ", max=" + max + "]";
	}
	
}
