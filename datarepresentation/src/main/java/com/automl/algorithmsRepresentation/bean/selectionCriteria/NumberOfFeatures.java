package com.automl.algorithmsRepresentation.selectionCriteria;

import com.automl.algorithmsRepresentation.Interval;

public class NumberOfFeatures extends SelectionCriteria {

	Interval numberOfFeature;

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
