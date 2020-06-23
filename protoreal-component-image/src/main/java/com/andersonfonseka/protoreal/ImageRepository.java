package com.andersonfonseka.protoreal;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.IComponent;
import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.dao.Repository;
import com.andersonfonseka.dao.RepositoryImpl;

public class ImageRepository extends RepositoryImpl implements Repository<Image> {
	
	private static Jdbi handle;	
	
	public ImageRepository() {}
	
	public void add(Image jumbotron) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO IMAGE (UUID, FILE) VALUES (?,?)") 
							.bind(0, jumbotron.getUuid())
							.bind(1, jumbotron.getFile())
						.execute();
			});
	}
	
	public Image get(String uuid) {
		return (Image) get(getMode(), uuid, "SELECT * FROM IMAGE WHERE UUID = ?", Image.class);
	}

	public void edit(Image jumbotron) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE IMAGE SET FILE=? WHERE UUID=?") 
							.bind(1, jumbotron.getUuid())
							.bind(0, jumbotron.getFile())
						.execute();
			});
	}

	public void remove(Image component) {
		remove(getMode(), component.getUuid(), "DELETE FROM IMAGE WHERE UUID=?");
	}

	public List<IComponent> list(String uuid) {
		return null;
	}
	
	public void setMode(String mode) {
		
		super.setMode(mode);
		
		if (this.getMode().equals(DbConnection.TEST_MODE)) {
			
			handle = DbConnection.getInstance(getMode()).getHandle();
			
			handle.useHandle(handle -> {
				handle.execute("CREATE TABLE IF NOT EXISTS IMAGE (UUID VARCHAR, FILE VARCHAR)");
			});
		}
	}
	
}
