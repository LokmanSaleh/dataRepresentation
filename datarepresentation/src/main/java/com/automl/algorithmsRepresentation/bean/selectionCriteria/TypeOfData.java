package com.automl.algorithmsRepresentation.bean.selectionCriteria;

@SuppressWarnings("serial")
public class TypeOfData extends SelectionCriteria {
 
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

	@Override
	public String toString() {
		return "TypeOfData [typeOfData=" + typeOfData + "]";
	}
	
}
