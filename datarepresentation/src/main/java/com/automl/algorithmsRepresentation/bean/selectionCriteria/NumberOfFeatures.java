package com.automl.algorithmsRepresentation.bean.selectionCriteria;

import com.automl.algorithmsRepresentation.bean.Interval;

@SuppressWarnings("serial")
public class NumberOfFeatures extends SelectionCriteria {

	Interval numberOfFeature;

	
	public NumberOfFeatures(Interval numberOfFeature) {
		super();
		this.numberOfFeature = numberOfFeature;
	}

	/**
	 * @return the numberOfFeature
	 */
	public Interval getNumberOfFeature() {
		return numberOfFeature;
	}

	/**
	 * @param numberOfFeature the numberOfFeature to set
	 */
	public void setNumberOfFeature(Interval numberOfFeature) {
		this.numberOfFeature = numberOfFeature;
	}
}
