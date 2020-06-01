package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.SelectInput;

class SelectInputRepository extends RepositoryImpl implements Repository<SelectInput> {
	
	private static Jdbi handle;	
	
	private static Connection connection = null;
	
	public SelectInputRepository() {}
	
	public void add(SelectInput selectInput) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO SELECTINPUT (UUID, TYPE, LABEL, PLACEHOLDER, READONLY, VALUE, OPTIONVALUES) VALUES (?,?,?,?,?,?,?) ") 
							.bind(0, selectInput.getUuid())
							.bind(1, selectInput.getType())
							.bind(2, selectInput.getLabel())
							.bind(3, selectInput.getPlaceholder())
							.bind(4, selectInput.getReadonly())
							.bind(5, selectInput.getValue())
							.bind(6, selectInput.getOptionValues())
						.execute();
			});
	}
	
	public SelectInput get(String uuid) {
		return (SelectInput) get(uuid, "SELECT * FROM SELECTINPUT WHERE UUID = ?", SelectInput.class);
	}

	@Override
	public void edit(SelectInput selectInput) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE SELECTINPUT SET  TYPE=?, LABEL=?, PLACEHOLDER=?, READONLY=?, VALUE=?, OPTIONVALUES=? WHERE UUID=?") 
							.bind(6, selectInput.getUuid())
							.bind(0, selectInput.getType())
							.bind(1, selectInput.getLabel())
							.bind(2, selectInput.getPlaceholder())
							.bind(3, selectInput.getReadonly())
							.bind(4, selectInput.getValue())
							.bind(5, selectInput.getOptionValues())
						.execute();
			});
	}

	@Override
	public void remove(SelectInput component) {
		remove(component.getUuid(), "DELETE FROM SELECTINPUT WHERE UUID = ?");
	}

	@Override
	public List<SelectInput> list(String uuid) {return null;}
	
}
