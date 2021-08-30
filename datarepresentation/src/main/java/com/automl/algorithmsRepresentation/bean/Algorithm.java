package com.automl.algorithmsRepresentation.bean;

import java.io.Serializable;
import java.util.List;

import com.automl.algorithmsRepresentation.bean.parameter.Parameter;
import com.automl.algorithmsRepresentation.bean.selectionCriteria.SelectionCriteria;
import com.automl.algorithmsRepresentation.externalSource.MethodInvocationUtils;
import com.automl.algorithmsRepresentation.externalSource.RuntimeCompiler;

// conserve the object into BLOB object in mysql
public class Algorithm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private List<Parameter> parameters;
	private List<SelectionCriteria> SelectionCriterias;
	
	private String execute;
	
	public Algorithm(String name, List<Parameter> parameters, List<SelectionCriteria> selectionCriterias, String execute) {
		super();
		this.name = name;
		this.parameters = parameters;
		SelectionCriterias = selectionCriterias;
		this.execute = execute;
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
	
	/**
	 * @return the selectionCriterias
	 */
	public List<SelectionCriteria> getSelectionCriterias() {
		return SelectionCriterias;
	}
	/**
	 * @param selectionCriterias the selectionCriterias to set
	 */
	public void setSelectionCriterias(List<SelectionCriteria> selectionCriterias) {
		SelectionCriterias = selectionCriterias;
	}
	
	public void execute() {
		// TODO : clean
		// convert the input string processus of the algorithm, into executed code, when call execute method

    	//String execute = "System.out.println(\"hello test execute \" + name);" ; 
        String className = "ExecuteClass";
        String code =
            "public class ExecuteClass {" + "\n" + 
         //   "    public static void execute(String name) {" + "\n" + 
           
            execute +
            
      //      "    }" + "\n" + 
            "}" + "\n";

        System.out.println(code);
        
        RuntimeCompiler r = new RuntimeCompiler();
        r.addClass(className, code);
        r.compile();
       
        MethodInvocationUtils.invokeStaticMethod(
            r.getCompiledClass(className), 
            "execute", "lokman");
	}

	@Override
	public String toString() {
		return "Algorithm [name=" + name + ", parameters=" + parameters + ", SelectionCriterias=" + SelectionCriterias
				+ ", execute=" + execute + "]";
	}
	
}
