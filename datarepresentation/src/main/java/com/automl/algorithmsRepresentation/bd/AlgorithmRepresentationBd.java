package com.automl.algorithmsRepresentation.bd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.automl.algorithmsRepresentation.bean.Algorithm;

public class AlgorithmRepresentationBd {
   
	private String URL =  "jdbc:mysql://localhost:3306/objectpersistence";
	private Connection dbConn ;

    
	public AlgorithmRepresentationBd() throws SQLException {
		super();
		this.dbConn = DriverManager.getConnection(URL, "root", "");;
	}

	public static final String INSERT_ALGORITHM_SQL = "INSERT INTO EMPLOYEE (emp) VALUES(?)";
	public static final String GET_ALGORITHM_SQL = "SELECT emp FROM Employee";

	//TODO
	public void saveAlgorithm(Algorithm algorithm) throws IOException, SQLException {
		
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ObjectOutputStream oos = new ObjectOutputStream(baos);
	    oos.writeObject(algorithm);
	    
	    byte[] algorithmAsBytes = baos.toByteArray();
	    
	    PreparedStatement pstmt = dbConn.prepareStatement(INSERT_ALGORITHM_SQL);
	    ByteArrayInputStream bais = new ByteArrayInputStream(algorithmAsBytes);
	    pstmt.setBinaryStream(1, bais, algorithmAsBytes.length);
	    pstmt.executeUpdate();
	    pstmt.close();
	}
	
	//TODO
	public void getAlgorithm(Algorithm algorithm) throws IOException, SQLException, ClassNotFoundException {
	   
		Statement stmt = dbConn.createStatement();
	    ResultSet rs = stmt.executeQuery(GET_ALGORITHM_SQL);
	    while (rs.next()) {
	      byte[] st = (byte[]) rs.getObject(1);
	      ByteArrayInputStream baip = new ByteArrayInputStream(st);
	      ObjectInputStream ois = new ObjectInputStream(baip);
	      Algorithm emp = (Algorithm) ois.readObject();
	      System.out.println( emp );
	      emp.execute("parametre");
	    }
	    
	    stmt.close();
	    rs.close();
	}
}
