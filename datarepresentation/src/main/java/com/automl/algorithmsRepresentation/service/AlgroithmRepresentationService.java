package com.automl.algorithmsRepresentation.service;

import java.io.IOException;
import java.sql.SQLException;

import com.automl.algorithmsRepresentation.bd.AlgorithmRepresentationBd;
import com.automl.algorithmsRepresentation.bean.Algorithm;

public class AlgroithmRepresentationService {

	AlgorithmRepresentationBd algorithmRepresentationBd ;
	
	public AlgroithmRepresentationService() throws SQLException {
		super();
		algorithmRepresentationBd = new AlgorithmRepresentationBd();
	}
	
	public void saveAlgorithm(Algorithm algorithm) throws IOException, SQLException {
		
		algorithmRepresentationBd.saveAlgorithm(algorithm);
	}

	
}
