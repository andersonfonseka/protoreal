package com.andersonfonseka.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.jdbi.v3.core.Jdbi;

public class DbConnection {

	private static DbConnection instance;
	
	private DataSource dataSource = null;

	private DbConnection(){
		
		try {

			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			
			dataSource = (DataSource) envContext.lookup("jdbc/protoreal");

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static DbConnection getInstance() {
		if (instance == null) {
			instance = new DbConnection();
		}

		return instance;
	}
	
	public Jdbi getHandle() {
		
		Jdbi jdbi = Jdbi.create(dataSource);
		return jdbi;
	}

}
