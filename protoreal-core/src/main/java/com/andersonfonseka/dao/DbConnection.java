package com.andersonfonseka.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.jdbi.v3.core.Jdbi;

public class DbConnection {

	private static DbConnection instance;
	
	private DataSource dataSource = null;
	
	public static final String RUN_MODE = "run";
	
	public static final String TEST_MODE = "test";
	
	private static String mode = "run";
	
	private DbConnection(){
		
		if (mode.equals(RUN_MODE)) {
			try {
				Context initContext = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				dataSource = (DataSource) envContext.lookup("jdbc/protoreal");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static DbConnection getInstance() {
		if (instance == null) {
			instance = new DbConnection();
		}

		return instance;
	}

	public static DbConnection getInstance(String pMode) {
		if (instance == null) {
			mode = pMode;
			instance = new DbConnection();

		}

		return instance;
	}
	
	public Jdbi getHandle() {
		
		Jdbi jdbi = null;
		
		if (mode.equals(RUN_MODE)) {
			jdbi = Jdbi.create(dataSource);
			
		} else if (mode.equals(TEST_MODE)) {
			jdbi = Jdbi.create("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		}
		
		
		return jdbi;
	}

}
