package com.andersonfonseka.dao.impl;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Table;

class TableRepository extends RepositoryImpl implements Repository<Table> {
	
	private static Jdbi handle;	
	
	public TableRepository() {}
	
	public void add(Table table) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO TABLEINPUT (UUID, TYPE, HEADERVALUES, DATAVALUES) VALUES (?,?,?,?) ") 
							.bind(0, table.getUuid())
							.bind(1, table.getType())
							.bind(2, table.getHeaderValues())
							.bind(3, table.getDataValues())
						.execute();
			});
	}
	
	public Table get(String uuid) {
		return (Table) get(uuid, "SELECT * FROM TABLEINPUT WHERE UUID = ?", Table.class);
	}

	@Override
	public void edit(Table table) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE TABLEINPUT SET TYPE=?, HEADERVALUES=?, DATAVALUES=? WHERE UUID=?") 
							.bind(3, table.getUuid())
							.bind(0, table.getType())
							.bind(1, table.getHeaderValues())
							.bind(2, table.getDataValues())
						.execute();
			});
	}

	@Override
	public void remove(Table component) {
		remove(component.getUuid(), "DELETE FROM TABLEINPUT WHERE UUID = ?");
	}

	@Override
	public List<Table> list(String uuid) {return null;}
	
}
