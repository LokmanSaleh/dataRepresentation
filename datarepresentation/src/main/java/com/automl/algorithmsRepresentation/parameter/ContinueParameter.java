package com.automl.algorithmsRepresentation.parameter;

import com.automl.algorithmsRepresentation.Interval;

public class ContinueParameter extends Parameter {
	
	Interval inter;

	/**
	 * @param inter
	 */
	public ContinueParameter(Interval inter) {
		super();
		this.inter = inter;
	}

	/**
	 * @return the inter
	 */
	public Interval getInter() {
		return inter;
	}

	/**
	 * @param inter the inter to set
	 */
	public void setInter(Interval inter) {
		this.inter = inter;
	}
}
