package com.andersonfonseka.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.jdbi.v3.core.Jdbi;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DbConnection {

	private static DbConnection instance;
	
	private DatabaseProps props = new DatabaseProps();
	
	private HikariConfig hc;

	private DbConnection(){
		
		try {

			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/protoreal");
			
			hc = new HikariConfig();
			hc.setDataSource(dataSource);
			hc.setMaximumPoolSize(5);
			
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
		
		Jdbi jdbi = Jdbi.create(new HikariDataSource(hc));
		return jdbi;
	}

	public Connection getConnection() {
		
		Connection connection = null;
		
		props.setUrl("jdbc:mysql://localhost:3306/protoreal");
		props.setUser("root");
		props.setPwd("root");
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(this.props.getUrl(), this.props.getUser(), this.props.getPwd());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return connection;

	}

}
