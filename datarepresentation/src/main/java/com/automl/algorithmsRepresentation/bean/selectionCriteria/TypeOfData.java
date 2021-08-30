package com.automl.algorithmsRepresentation.bean.selectionCriteria;


public class TypeOfData extends SelectionCriteria {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum TYPE {CATEGORIAL, CONTINUE, ALLTYPE};
	
	private TYPE typeOfData;

	
	public TypeOfData(TYPE typeOfData) {
		super();
		this.typeOfData = typeOfData;
	}

	/**
	 * @return the typeOfData
	 */
	public TYPE getTypeOfData() {
		return typeOfData;
	}

	/**
	 * @param typeOfData the typeOfData to set
	 */
	public void setTypeOfData(TYPE typeOfData) {
		this.typeOfData = typeOfData;
	}
}
