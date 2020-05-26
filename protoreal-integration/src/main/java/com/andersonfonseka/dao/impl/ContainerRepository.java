package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.andersonfonseka.protoreal.components.Cell;
import com.andersonfonseka.protoreal.components.Component;
import com.andersonfonseka.protoreal.components.Container;
import com.andersonfonseka.protoreal.components.Row;

public class ContainerRepository implements Repository {
	
	private static Connection connection = null;
	
	public ContainerRepository() {}
	
	public void add(Component container) {	
		
		ComponentRepository componentRepository = new ComponentRepository();
		RowsRepository rowsRepository = new RowsRepository();
		CellRepository cellRepository = new CellRepository();
		
		Container container2 = (Container) container;
		
		for (Component row : container2.getChildrenList()) {
			componentRepository.add(row);
			
			for (Component cell: row.getChildrenList()) {
				componentRepository.add(cell);
			}
		}
		
		String INSERT_CONTAINER = "INSERT INTO CONTAINER (UUID, ROWCOUNT, COLUMNS) VALUES (?,?,?) ";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_CONTAINER);
			
			pstmt.setString(1, container2.getUuid());
			pstmt.setInt(2, container2.getRows());
			pstmt.setInt(3, container2.getColumns());

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
	
	public Container get(String uuid) {
		
		ComponentRepository componentRepository = new ComponentRepository();
		
		Container container = null; 
		
		String SELECT_ALL = "SELECT * FROM CONTAINER WHERE UUID = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				container = new Container();
				
				container.setUuid(resultSet.getString(1));
				container.setRows(resultSet.getString(2));
				container.setColumns(resultSet.getString(3));
				
				container.setChildren(componentRepository.list(container.getUuid()));
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
		
		return container;
	}
	
}
