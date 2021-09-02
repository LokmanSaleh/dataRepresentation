package com.automl.algorithmsRepresentation.bean;

public class TypeOfAlgorithm {
	
	// the type of algorithm, help to retrieve a specific type of algorithm from the data base, to select one of them 
	public enum TYPE {CLASSIFICATION, REGRESSION, CLUSTERING, RECOMMENDATION};
	
	TYPE typeOfAlgorithm;

	public TypeOfAlgorithm(TYPE typeOfAlgorithm) {
		super();
		this.typeOfAlgorithm = typeOfAlgorithm;
	}

	/**
	 * @return the typeOfAlgorithm
	 */
	public TYPE getTypeOfAlgorithm() {
		return typeOfAlgorithm;
	}

	/**
	 * @param typeOfAlgorithm the typeOfAlgorithm to set
	 */
	public void setTypeOfAlgorithm(TYPE typeOfAlgorithm) {
		this.typeOfAlgorithm = typeOfAlgorithm;
	}
	
}
