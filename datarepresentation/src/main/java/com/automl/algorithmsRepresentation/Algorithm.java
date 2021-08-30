package com.automl.algorithmsRepresentation;

import java.io.Serializable;

import com.automl.algorithmsRepresentation.parameter.Parameter;
import com.sun.tools.javac.util.List;

// conserve the object into BLOB object in mysql
public class Algorithm implements Serializable{
	
	private String name;
	private List<Parameter> parameters;
	private String execute;
	
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
	/**
	 * @return the parameters
	 */
	public List<Parameter> getParameters() {
		return parameters;
	}
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	/**
	 * @return the execute
	 */
	public String getExecute() {
		return execute;
	}
	/**
	 * @param execute the execute to set
	 */
	public void setExecute(String execute) {
		this.execute = execute;
	}
	
	
	public void execute() {
		// convert the input string processus of the algorithm, into executed code, when call execute method
		
		
	}
	
}
