package com.andersonfonseka.protoreal;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.Component;
import com.andersonfonseka.IComponent;
import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.dao.Repository;
import com.andersonfonseka.dao.RepositoryImpl;


public class FormRepository extends RepositoryImpl implements Repository<Form> {
	
	private static Jdbi handle;	
	
	public FormRepository() {}
	
	public void add(Form form) {	
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO FORM (UUID, TITLE, URL) VALUES (?,?,?)") 
							.bind(0, form.getUuid())
							.bind(1, form.getTitle())
							.bind(2, form.getUrl())
						.execute();
			});
	}
	
	public Form get(String uuid) {
		
		Form container = (Form) get(getMode(), uuid, "SELECT * FROM FORM WHERE UUID = ?", Form.class);
		
		if (null != container) {
			((Component) container).setChildren(super.getComponentRepository().list(container.getUuid()));
		}
			
		return container;
	}

	public void edit(Form container) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE FORM SET TITLE=?, URL=? WHERE UUID=?") 
							.bind(2, container.getUuid())
							.bind(0, container.getTitle())
							.bind(1, container.getUrl())
						.execute();
			});
	}

	public void remove(Form component) {
		remove(getMode(), component.getUuid(), "DELETE FROM FORM WHERE UUID=?");
	}

	public List<IContainer> list(String uuid) {return null;}
	
	public void setMode(String mode) {
		super.setMode(mode);
		
		if (this.getMode().equals(DbConnection.TEST_MODE)) {
			
			handle = DbConnection.getInstance(getMode()).getHandle();
			
			handle.useHandle(handle -> {
				handle.execute("CREATE TABLE IF NOT EXISTS FORM (UUID VARCHAR, TITLE VARCHAR, URL VARCHAR)");
			});
		}
	}
	
}
