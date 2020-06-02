package com.andersonfonseka.dao.impl;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Label;
import com.andersonfonseka.protoreal.components.spec.IComponent;

class LabelRepository extends RepositoryImpl {
	
	private static Jdbi handle;	
	
	public LabelRepository() {}
	
	public void add(Label label) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO LABEL (UUID, TYPE, STYLE, LABEL, VALUE) VALUES (?,?,?,?,?) ") 
							.bind(0, label.getUuid())
							.bind(1, label.getType())
							.bind(2, label.getStyle())
							.bind(2, label.getLabel())
							.bind(2, label.getValue())
						.execute();
			});
	}

	
	
	public Label get(String uuid) {
		return (Label) get(uuid, "SELECT * FROM LABEL WHERE UUID = ?", Label.class);
	}

	public void edit(Label label) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE LABEL SET TYPE=?, STYLE=?, LABEL=?, VALUE=? WHERE UUID=?") 
							.bind(4, label.getUuid())
							.bind(0, label.getType())
							.bind(1, label.getStyle())
							.bind(2, label.getLabel())
							.bind(3, label.getValue())
						.execute();
			});
	}

	public void remove(Label component) {
		remove(component.getUuid(), "DELETE FROM LABEL WHERE UUID = ?");
	}

	public List<IComponent> list(String uuid) {return null;}
	
}
