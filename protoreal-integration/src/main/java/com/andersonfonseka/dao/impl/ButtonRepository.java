package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.andersonfonseka.protoreal.components.Button;

public class ButtonRepository implements Repository<Button> {
	
	private static Connection connection = null;
	
	public ButtonRepository() {}
	
	public void add(Button textInput) {
		
		String INSERT_PAGE = "INSERT INTO BUTTON (UUID, CSSCLASS, OPENTYPE, ALIGNMENT, PAGEUUID, LABEL) VALUES (?,?,?,?,?,?) ";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(1, textInput.getUuid());
			pstmt.setString(2, textInput.getCssClass());
			pstmt.setString(3, textInput.getOpenType());
			pstmt.setString(4, textInput.getAlignment());
			pstmt.setString(5, textInput.getPageUuid());
			pstmt.setString(6, textInput.getLabel());
			
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
	
	public Button get(String uuid) {
		
		Button button = null;
		
		String SELECT_ALL = "SELECT * FROM BUTTON WHERE UUID = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				button = new Button();
				
				button.setUuid(resultSet.getString(1));
				button.setCssClass(resultSet.getString(2));
				button.setOpenType(resultSet.getString(3));
				button.setAlignment(resultSet.getString(4));
				button.setPageUuid(resultSet.getString(5));
				button.setLabel(resultSet.getString(6));
				
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
		
		return button;
	}
	
}
