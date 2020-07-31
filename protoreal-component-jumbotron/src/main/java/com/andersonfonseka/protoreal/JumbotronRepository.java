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
						.createUpdate("INSERT INTO JUMBOTRON (UUID, TITLE, SUBTITLE, FILE,FONTCOLOR, BUTTONTEXT, BUTTONURL) VALUES (?,?,?,?,?,?,?)") 
							.bind(0, jumbotron.getUuid())
							.bind(1, jumbotron.getTitle())
							.bind(2, jumbotron.getSubtitle())
							.bind(3,  jumbotron.getFile())
							.bind(4,  jumbotron.getFontColor())
							.bind(5,  jumbotron.getButtonText())
							.bind(6,  jumbotron.getButtonUrl())
						.execute();
			});
	}
	
	public Jumbotron get(String uuid) {
		return (Jumbotron) get(getMode(), uuid, "SELECT * FROM JUMBOTRON WHERE UUID = ?", Jumbotron.class);
	}

	public void edit(Jumbotron jumbotron) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE JUMBOTRON SET TITLE=?, SUBTITLE=?, FILE=?, FONTCOLOR=?, BUTTONTEXT=?, BUTTONURL=? WHERE UUID=?") 
							.bind(6, jumbotron.getUuid())
							.bind(0, jumbotron.getTitle())
							.bind(1, jumbotron.getSubtitle())
							.bind(2, jumbotron.getFile())
							.bind(3, jumbotron.getFontColor())
							.bind(4, jumbotron.getButtonText())
							.bind(5, jumbotron.getButtonUrl())
						.execute();
			});
	}

	public void remove(Jumbotron component) {
		remove(getMode(), component.getUuid(), "DELETE FROM JUMBOTRON WHERE UUID=?");
	}

	public List<IComponent> list(String uuid) {
		return null;
	}
	
	public void setMode(String mode) {
		
		super.setMode(mode);
		
		if (this.getMode().equals(DbConnection.TEST_MODE)) {
			
			handle = DbConnection.getInstance(getMode()).getHandle();
			
			handle.useHandle(handle -> {
				handle.execute("CREATE TABLE IF NOT EXISTS JUMBOTRON (UUID VARCHAR, TITLE VARCHAR, SUBTITLE VARCHAR, FILE VARCHAR)");
			});
		}
	}
	
}
