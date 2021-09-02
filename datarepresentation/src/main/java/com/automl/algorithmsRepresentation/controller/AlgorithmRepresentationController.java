package com.automl.algorithmsRepresentation.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.automl.algorithmsRepresentation.bean.Algorithm;
import com.automl.algorithmsRepresentation.service.AlgroithmRepresentationService;

public class AlgorithmRepresentationController {
	
	private static AlgroithmRepresentationService algroithmRepresentationService;
	// to be able to create a static attribute that can return exception
	static {
		AlgroithmRepresentationService tmp = null;
		try {
			tmp = new AlgroithmRepresentationService();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		algroithmRepresentationService = tmp;
	}

	/**
	 * 
	 * @param algorithm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void saveAlgorithm(Algorithm algorithm) throws IOException, SQLException {

		algroithmRepresentationService.saveAlgorithm(algorithm);
	}
}