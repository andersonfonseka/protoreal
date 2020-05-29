package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Label;

public class LabelRepository implements Repository<Label> {
	
	private static Connection connection = null;
	
	public LabelRepository() {}
	
	public void add(Label label) {
		
		String INSERT_PAGE = "INSERT INTO LABEL (UUID, TYPE, STYLE, LABEL, VALUE) VALUES (?,?,?,?,?) ";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(1, label.getUuid());
			pstmt.setString(2, label.getType());
			pstmt.setString(3, label.getStyle());
			pstmt.setString(4, label.getLabel());
			pstmt.setString(5, label.getValue());
			
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
	
	public Label get(String uuid) {
		
		Label label = null;
		
		String SELECT_ALL = "SELECT * FROM LABEL WHERE UUID = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				label = new Label();
				
				label.setUuid(resultSet.getString(1));
				label.setType(resultSet.getString(2));
				label.setStyle(resultSet.getString(3));
				label.setLabel(resultSet.getString(4));
				label.setValue(resultSet.getString(5));
				
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
		
		return label;
	}

	@Override
	public void edit(Label label) {
		
		String INSERT_PAGE = "UPDATE LABEL SET TYPE=?, STYLE=?, LABEL=?, VALUE=? WHERE UUID=? ";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(5, label.getUuid());
			
			pstmt.setString(1, label.getType());
			pstmt.setString(2, label.getStyle());
			pstmt.setString(3, label.getLabel());
			pstmt.setString(4, label.getValue());
			
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
	
}
