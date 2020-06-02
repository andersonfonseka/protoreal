package com.andersonfonseka.dao.impl;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.TextAreaInput;

class TextAreaInputRepository extends RepositoryImpl implements Repository<TextAreaInput> {
	
	private static Jdbi handle;	
	
	public void add(TextAreaInput textInput) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO TEXTAREAINPUT (UUID, LABEL, ROWCOUNT) VALUES (?,?,?)") 
							.bind(0, textInput.getUuid())
							.bind(1, textInput.getLabel())
							.bind(2, textInput.getRows())
						.execute();
			});
	}
	
	public TextAreaInput get(String uuid) {
		return (TextAreaInput) get(uuid, "SELECT * FROM TEXTAREAINPUT WHERE UUID = ?", TextAreaInput.class);
	}

	public void edit(TextAreaInput textInput) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE TEXTAREAINPUT SET LABEL=?, ROWCOUNT=? WHERE UUID=?") 
							.bind(2, textInput.getUuid())
							.bind(0, textInput.getLabel())
							.bind(1, textInput.getRows())
						.execute();
			});
	}

	public void remove(TextAreaInput component) {
		remove(component.getUuid(), "DELETE FROM TEXTAREAINPUT WHERE UUID = ?");
	}

	public List<TextAreaInput> list(String uuid) {return null;}
	
}
