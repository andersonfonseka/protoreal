package com.andersonfonseka.dao.impl;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Button;

class ButtonRepository extends RepositoryImpl implements Repository<Button> {
	
	private static Jdbi handle;	
	
	public ButtonRepository() {}
	
	public void add(Button button) {
			
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO BUTTON (UUID, CSSCLASS, OPENTYPE, ALIGNMENT, PAGEUUID, LABEL, SITEUUID) VALUES (?,?,?,?,?,?,?)") 
							.bind(0, button.getUuid())
							.bind(1, button.getCssClass())
							.bind(2, button.getOpenType())
							.bind(3, button.getAlignment())
							.bind(4, button.getPageUuid())
							.bind(5, button.getLabel())
							.bind(6, button.getSiteUuid())
						.execute();
			});
	}
	
	public void remove(Button button) {
		remove(button.getUuid(), "DELETE FROM BUTTON WHERE UUID=?");
	}
	
	public Button get(String uuid) {
		return (Button) get(uuid, "SELECT * FROM BUTTON WHERE UUID=?", Button.class);
	}

	@Override
	public void edit(Button button) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE BUTTON SET CSSCLASS=?, OPENTYPE=?, ALIGNMENT=?, PAGEUUID=?, LABEL=?, SITEUUID=? WHERE UUID=?") 
							.bind(6, button.getUuid())
							.bind(0, button.getCssClass())
							.bind(1, button.getOpenType())
							.bind(2, button.getAlignment())
							.bind(3, button.getPageUuid())
							.bind(4, button.getLabel())
							.bind(5, button.getSiteUuid())
						.execute();
			});
	}

	@Override
	public List<Button> list(String uuid) {
		return null;
	}
	
}
