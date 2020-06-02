package com.andersonfonseka.dao.impl;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.TextInput;

class TextInputRepository extends RepositoryImpl implements Repository<TextInput> {
	
	private static Jdbi handle;	
	
	public void add(TextInput textInput) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO TEXTINPUT (UUID, TYPE, LABEL, PLACEHOLDER, READONLY, VALUE) VALUES (?,?,?,?,?,?)") 
							.bind(0, textInput.getUuid())
							.bind(1, textInput.getType())
							.bind(2, textInput.getLabel())
							.bind(3, textInput.getPlaceholder())
							.bind(4, textInput.getReadonly())
							.bind(5, textInput.getValue())
						.execute();
			});
	}
	
	public TextInput get(String uuid) {
		return (TextInput) get(uuid, "SELECT * FROM TEXTINPUT WHERE UUID = ?", TextInput.class);
	}

	public void edit(TextInput textInput) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE TEXTINPUT SET TYPE=?, LABEL=?, PLACEHOLDER=?, READONLY=?, VALUE=? WHERE UUID=?") 
							.bind(5, textInput.getUuid())
							.bind(0, textInput.getType())
							.bind(1, textInput.getLabel())
							.bind(2, textInput.getPlaceholder())
							.bind(3, textInput.getReadonly())
							.bind(4, textInput.getValue())
						.execute();
			});
	}

	public void remove(TextInput component) {
		remove(component.getUuid(), "DELETE FROM TEXTINPUT WHERE UUID = ?");
	}

	public List<TextInput> list(String uuid) {return null;}
	
}
