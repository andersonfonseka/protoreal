package com.andersonfonseka.dao.impl;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Row;
import com.andersonfonseka.protoreal.components.spec.IComponent;

class RowsRepository extends RepositoryImpl {
	
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

	public void edit(Row component) {}

	public void remove(Row component) {
		remove(component.getUuid(), "DELETE FROM ROWSCOUNT WHERE UUID = ?");
	}

	public List<IComponent> list(String uuid) {return null;}

}
