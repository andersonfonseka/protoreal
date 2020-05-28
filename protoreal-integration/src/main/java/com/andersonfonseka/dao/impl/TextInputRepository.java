package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.andersonfonseka.protoreal.components.TextInput;

public class TextInputRepository implements Repository<TextInput> {
	
	private static Connection connection = null;
	
	public TextInputRepository() {}
	
	public void add(TextInput textInput) {
		
		String INSERT_PAGE = "INSERT INTO TEXTINPUT (UUID, TYPE, LABEL, PLACEHOLDER, READONLY, VALUE) VALUES (?,?,?,?,?,?) ";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(1, textInput.getUuid());
			pstmt.setString(2, textInput.getType());
			pstmt.setString(3, textInput.getLabel());
			pstmt.setString(4, textInput.getPlaceholder());
			pstmt.setString(5, textInput.getReadonly());
			pstmt.setString(6, textInput.getValue());
			
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
	
	public TextInput get(String uuid) {
		
		TextInput textInput = null;
		
		String SELECT_ALL = "SELECT * FROM TEXTINPUT WHERE UUID = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				textInput = new TextInput();
				
				textInput.setUuid(resultSet.getString(1));
				textInput.setType(resultSet.getString(2));
				textInput.setLabel(resultSet.getString(3));
				textInput.setPlaceholder(resultSet.getString(4));
				textInput.setReadonly(resultSet.getString(5));
				textInput.setValue(resultSet.getString(6));
				
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
		
		return textInput;
	}

	@Override
	public void edit(TextInput textInput) {
		
		String INSERT_PAGE = "UPDATE TEXTINPUT SET TYPE=?, LABEL=?, PLACEHOLDER=?, READONLY=?, VALUE=? WHERE UUID=? ";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(6, textInput.getUuid());
			pstmt.setString(1, textInput.getType());
			pstmt.setString(2, textInput.getLabel());
			pstmt.setString(3, textInput.getPlaceholder());
			pstmt.setString(4, textInput.getReadonly());
			pstmt.setString(5, textInput.getValue());
			
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
