package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.andersonfonseka.protoreal.components.Jumbotron;
import com.andersonfonseka.protoreal.components.Label;

public class JumbotronRepository implements Repository<Jumbotron> {
	
	private static Connection connection = null;
	
	public JumbotronRepository() {}
	
	public void add(Jumbotron jumbotron) {
		
		String INSERT_PAGE = "INSERT INTO JUMBOTRON (UUID, TITLE, SUBTITLE) VALUES (?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(1, jumbotron.getUuid());
			pstmt.setString(2, jumbotron.getTitle());
			pstmt.setString(3, jumbotron.getSubtitle());
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	public void remove(String uuid) {
	//	this.repository.remove(uuid);
	}
	
	public Jumbotron get(String uuid) {
		
		Jumbotron jumbotron = null;
		
		String SELECT_ALL = "SELECT * FROM JUMBOTRON WHERE UUID = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				jumbotron = new Jumbotron();
				
				jumbotron.setUuid(resultSet.getString(1));
				jumbotron.setTitle(resultSet.getString(2));
				jumbotron.setSubtitle(resultSet.getString(3));
			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return jumbotron;
	}
	
}
