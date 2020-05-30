package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Cell;

class CellRepository implements Repository<Cell> {
	
	private static Connection connection = null;
	
	public CellRepository() {}
	
	public void add(Cell cell) {
		
		String INSERT_PAGE = "INSERT INTO CELL (UUID) VALUES (?) ";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(1, cell.getUuid());
			
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
	
	public Cell get(String uuid) {
		
		ComponentRepository componentRepository = new ComponentRepository();
		
		Cell cell = null;
		
		String SELECT_ALL = "SELECT * FROM CELL WHERE UUID = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				cell = new Cell();
				
				cell.setUuid(resultSet.getString(1));
				
				cell.setChildren(componentRepository.list(cell.getUuid()));
				
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
		
		return cell;
	}

	@Override
	public void edit(Cell component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Cell component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cell> list(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
