package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	private static DbConnection instance;

	private DbConnection(){
	}

	public static DbConnection getInstance() {
		if (instance == null) {
			instance = new DbConnection();
		}

		return instance;
	}

	public Connection getConnection(DatabaseProps databaseProps) {
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(databaseProps.getUrl(), databaseProps.getUser(), databaseProps.getPwd());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return connection;

	}

}
