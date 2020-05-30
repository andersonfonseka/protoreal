package com.andersonfonseka.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.jdbi.v3.core.Jdbi;

public class DbConnection {

	private static DbConnection instance;
	
	private DatabaseProps props = new DatabaseProps();

	private DbConnection(){
		
		props.setUrl("jdbc:mysql://localhost/protoreal");
		props.setPwd("root");
		props.setUser("root");

	}

	public static DbConnection getInstance() {
		if (instance == null) {
			instance = new DbConnection();
		}

		return instance;
	}
	
	public Jdbi getHandle() {
		Jdbi jdbi = Jdbi.create(this.props.getUrl(), this.props.getUser(), this.props.getPwd());
		return jdbi;
	}

	public Connection getConnection() {
		
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(this.props.getUrl(), this.props.getUser(), this.props.getPwd());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return connection;

	}

}
