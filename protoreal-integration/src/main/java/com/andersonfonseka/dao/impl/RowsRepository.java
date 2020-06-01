package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Row;

class RowsRepository extends RepositoryImpl implements Repository<Row> {
	
	private static Jdbi handle;	

	public RowsRepository() {
	}

	public void add(Row row) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO ROWSCOUNT (UUID) VALUES (?) ") 
							.bind(0, row.getUuid())
						.execute();
			});
	}

	public Row get(String uuid) {
		
		ComponentRepository componentRepository = new ComponentRepository();
		
		Row row = (Row) get(uuid, "SELECT * FROM ROWSCOUNT WHERE UUID = ?", Row.class);
		
		if (null != row) {
			row.setChildren(componentRepository.list(row.getUuid()));
		}
		
		return row;
	}

	@Override
	public void edit(Row component) {}

	@Override
	public void remove(Row component) {
		remove(component.getUuid(), "DELETE FROM ROWSCOUNT WHERE UUID = ?");
	}

	@Override
	public List<Row> list(String uuid) {return null;}

}
