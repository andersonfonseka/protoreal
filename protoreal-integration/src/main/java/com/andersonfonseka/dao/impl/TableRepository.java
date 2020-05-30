package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Table;

class TableRepository implements Repository<Table> {
	
	private static Connection connection = null;
	
	public TableRepository() {}
	
	public void add(Table table) {
		
		String INSERT_PAGE = "INSERT INTO TABLEINPUT (UUID, TYPE, HEADERVALUES, DATAVALUES) VALUES (?,?,?,?) ";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(1, table.getUuid());
			pstmt.setString(2, table.getType());
			pstmt.setString(3, table.getHeaderValues());
			pstmt.setString(4, table.getDataValues());
			
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
	
	public Table get(String uuid) {
		
		Table table = null;
		
		String SELECT_ALL = "SELECT * FROM TABLEINPUT WHERE UUID = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				table = new Table();
				
				table.setUuid(resultSet.getString(1));
				table.setType(resultSet.getString(2));
				table.setHeaderValues(resultSet.getString(3));
				table.setDataValues(resultSet.getString(4));
				
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
		
		return table;
	}

	@Override
	public void edit(Table table) {
		
		String INSERT_PAGE = "UPDATE TABLEINPUT SET TYPE=?, HEADERVALUES=?, DATAVALUES=? WHERE UUID=? ";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(4, table.getUuid());
			
			pstmt.setString(1, table.getType());
			pstmt.setString(2, table.getHeaderValues());
			pstmt.setString(3, table.getDataValues());
			
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
	public void remove(Table component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Table> list(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
