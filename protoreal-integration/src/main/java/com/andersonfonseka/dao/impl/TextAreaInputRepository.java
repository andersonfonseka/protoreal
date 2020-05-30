package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.TextAreaInput;

class TextAreaInputRepository implements Repository<TextAreaInput> {
	
	private static Connection connection = null;
	
	public TextAreaInputRepository() {}
	
	public void add(TextAreaInput textInput) {
		
		String INSERT_PAGE = "INSERT INTO TEXTAREAINPUT (UUID, LABEL, ROWCOUNT) VALUES (?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(1, textInput.getUuid());
			pstmt.setString(2, textInput.getLabel());
			pstmt.setInt(3, textInput.getRows());
			
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
	
	public TextAreaInput get(String uuid) {
		
		TextAreaInput textInput = null;
		
		String SELECT_ALL = "SELECT * FROM TEXTAREAINPUT WHERE UUID = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				textInput = new TextAreaInput();
				
				textInput.setUuid(resultSet.getString(1));
				textInput.setLabel(resultSet.getString(2));
				textInput.setRows(resultSet.getString(3));
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
	public void edit(TextAreaInput textInput) {
		
		String INSERT_PAGE = "UPDATE TEXTAREAINPUT SET LABEL=?, ROWCOUNT=? WHERE UUID=?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(3, textInput.getUuid());
			
			pstmt.setString(1, textInput.getLabel());
			pstmt.setInt(2, textInput.getRows());
			
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

	@Override
	public void remove(TextAreaInput component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TextAreaInput> list(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
