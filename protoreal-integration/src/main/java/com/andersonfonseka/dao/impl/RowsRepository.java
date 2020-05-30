package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Row;

class RowsRepository implements Repository<Row> {

	private static Connection connection = null;

	public RowsRepository() {
	}

	public void add(Row row) {

		String INSERT_PAGE = "INSERT INTO ROWSCOUNT (UUID) VALUES (?) ";
		PreparedStatement pstmt = null;

		try {

			connection = DbConnection.getInstance().getConnection();

			pstmt = connection.prepareStatement(INSERT_PAGE);

			pstmt.setString(1, row.getUuid());

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

		String SELECT_ALL = "DELETE FROM ROWSCOUNT WHERE UUID = ?";
		PreparedStatement pstmt = null;

		try {

			connection = DbConnection.getInstance().getConnection();

			pstmt = connection.prepareStatement(SELECT_ALL);

			pstmt.setString(1, uuid);

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

	public Row get(String uuid) {

		ComponentRepository componentRepository = new ComponentRepository();

		Row row = null;

		String SELECT_ALL = "SELECT * FROM ROWSCOUNT WHERE UUID = ?";
		PreparedStatement pstmt = null;

		try {

			connection = DbConnection.getInstance().getConnection();

			pstmt = connection.prepareStatement(SELECT_ALL);

			pstmt.setString(1, uuid);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				row = new Row();

				row.setUuid(resultSet.getString(1));

				row.setChildren(componentRepository.list(row.getUuid()));

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

		return row;
	}

	@Override
	public void edit(Row component) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Row component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Row> list(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

}
