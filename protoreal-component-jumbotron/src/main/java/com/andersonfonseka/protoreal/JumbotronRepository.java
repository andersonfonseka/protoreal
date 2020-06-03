package com.andersonfonseka.protoreal;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.IComponent;
import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.dao.Repository;
import com.andersonfonseka.dao.RepositoryImpl;

public class JumbotronRepository extends RepositoryImpl implements Repository<Jumbotron> {
	
	private static Jdbi handle;	
	
	public JumbotronRepository() {}
	
	public void add(Jumbotron jumbotron) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO JUMBOTRON (UUID, TITLE, SUBTITLE) VALUES (?,?,?)") 
							.bind(0, jumbotron.getUuid())
							.bind(1, jumbotron.getTitle())
							.bind(2, jumbotron.getSubtitle())
						.execute();
			});
	}
	
	public Jumbotron get(String uuid) {
		return (Jumbotron) get(uuid, "SELECT * FROM JUMBOTRON WHERE UUID = ?", Jumbotron.class);
	}

	public void edit(Jumbotron jumbotron) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE JUMBOTRON SET TITLE=?, SUBTITLE=? WHERE UUID=?") 
							.bind(2, jumbotron.getUuid())
							.bind(0, jumbotron.getTitle())
							.bind(1, jumbotron.getSubtitle())
						.execute();
			});
	}

	public void remove(Jumbotron component) {
		remove(component.getUuid(), "DELETE FROM JUMBOTRON WHERE UUID=?");
	}

	public List<IComponent> list(String uuid) {
		return null;
	}
	
}
