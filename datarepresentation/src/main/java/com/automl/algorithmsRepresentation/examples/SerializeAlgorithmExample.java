package com.automl.algorithmsRepresentation.examples;

// Source
//http://www.java2s.com/Code/Java/Database-SQL-JDBC/Storeandretrieveanobjectfromatable.htm
	
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.automl.algorithmsRepresentation.bean.Algorithm;
import com.automl.algorithmsRepresentation.bean.Interval;
import com.automl.algorithmsRepresentation.bean.parameter.ContinueParameter;
import com.automl.algorithmsRepresentation.bean.parameter.Parameter;
import com.automl.algorithmsRepresentation.bean.selectionCriteria.NumberOfFeatures;
import com.automl.algorithmsRepresentation.bean.selectionCriteria.SelectionCriteria;
import com.automl.algorithmsRepresentation.bean.selectionCriteria.TypeOfData;
  
public class SerializeAlgorithmExample {

  public static void main(String args[]) throws Exception {
    String URL =  "jdbc:mysql://localhost:3306/objectpersistence";
    Connection dbConn = DriverManager.getConnection(URL, "root", "");
    //Employee employee = new Employee(42, "AA", 9);
    String name = "KNN";
    
	List<Parameter> parameters = new ArrayList<>();
	Parameter p1 = new ContinueParameter(new Interval(0,20));
    parameters.add(p1);
   
	List<SelectionCriteria> selectionCriterias = new ArrayList<>();
	SelectionCriteria c1 = new TypeOfData(TypeOfData.TYPE.CATEGORIAL);
	SelectionCriteria c2 = new NumberOfFeatures(new Interval(2, 7));
	selectionCriterias.add(c1);
	selectionCriterias.add(c2);
	
	String execute = "	 public static void execute(String name) { \r\n" + 
					"		final int NUM_FACTS = 100;\r\n" + 
					"		for(int i = 0; i < NUM_FACTS; i++)\r\n" + 
					"			System.out.println( i + \"! is \" + factorial(i));\r\n" + 
					"	}\r\n" + 
					"	\r\n" + 
					"	public static int factorial(int n)\r\n" + 
					"	{	int result = 1;\r\n" + 
					"		for(int i = 2; i <= n; i++)\r\n" + 
					"			result *= i;\r\n" + 
					"		return result;\r\n" + 
					"	}";
	
    Algorithm algo = new Algorithm(name, parameters, selectionCriterias, execute);
     
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject(algo);
    
    byte[] employeeAsBytes = baos.toByteArray();
    PreparedStatement pstmt = dbConn
        .prepareStatement("INSERT INTO EMPLOYEE (emp) VALUES(?)");
    ByteArrayInputStream bais = new ByteArrayInputStream(employeeAsBytes);
    pstmt.setBinaryStream(1, bais, employeeAsBytes.length);
    pstmt.executeUpdate();
    pstmt.close();
    Statement stmt = dbConn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT emp FROM Employee");
    while (rs.next()) {
      byte[] st = (byte[]) rs.getObject(1);
      ByteArrayInputStream baip = new ByteArrayInputStream(st);
      ObjectInputStream ois = new ObjectInputStream(baip);
      Algorithm emp = (Algorithm) ois.readObject();
      System.out.println("hello employee : " + emp );
      emp.execute();
    }
    stmt.close();
    rs.close();
    dbConn.close();
  }
}

 