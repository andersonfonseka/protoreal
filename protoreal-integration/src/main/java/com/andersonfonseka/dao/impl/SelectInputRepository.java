package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.SelectInput;

public class SelectInputRepository implements Repository<SelectInput> {
	
	private static Connection connection = null;
	
	public SelectInputRepository() {}
	
	public void add(SelectInput selectInput) {
		
		String INSERT_PAGE = "INSERT INTO SELECTINPUT (UUID, TYPE, LABEL, PLACEHOLDER, READONLY, VALUE, OPTIONVALUES) VALUES (?,?,?,?,?,?,?) ";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(1, selectInput.getUuid());
			pstmt.setString(2, selectInput.getType());
			pstmt.setString(3, selectInput.getLabel());
			pstmt.setString(4, selectInput.getPlaceholder());
			pstmt.setString(5, selectInput.getReadonly());
			pstmt.setString(6, selectInput.getValue());
			pstmt.setString(7, selectInput.getOptionValues());
			
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
	
	public SelectInput get(String uuid) {
		
		SelectInput selectInput = null;
		
		String SELECT_ALL = "SELECT * FROM SELECTINPUT WHERE UUID = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				selectInput = new SelectInput();
				
				selectInput.setUuid(resultSet.getString(1));
				selectInput.setType(resultSet.getString(2));
				selectInput.setLabel(resultSet.getString(3));
				selectInput.setPlaceholder(resultSet.getString(4));
				selectInput.setReadonly(resultSet.getString(5));
				selectInput.setValue(resultSet.getString(6));
				selectInput.setOptions(resultSet.getString(7));
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
		
		return selectInput;
	}

	@Override
	public void edit(SelectInput selectInput) {
		
		String INSERT_PAGE = "UPDATE SELECTINPUT SET  TYPE=?, LABEL=?, PLACEHOLDER=?, READONLY=?, VALUE=?, OPTIONVALUES=? WHERE UUID=? ";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(7, selectInput.getUuid());
			
			pstmt.setString(1, selectInput.getType());
			pstmt.setString(2, selectInput.getLabel());
			pstmt.setString(3, selectInput.getPlaceholder());
			pstmt.setString(4, selectInput.getReadonly());
			pstmt.setString(5, selectInput.getValue());
			pstmt.setString(6, selectInput.getOptionValues());
			
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
