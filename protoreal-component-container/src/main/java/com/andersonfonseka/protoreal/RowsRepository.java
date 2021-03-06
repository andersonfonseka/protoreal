package com.andersonfonseka.protoreal;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.IComponent;
import com.andersonfonseka.dao.ComponentRepository;
import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.dao.Repository;
import com.andersonfonseka.dao.RepositoryImpl;

public class RowsRepository extends RepositoryImpl implements Repository<Row> {
	
	private static Jdbi handle;	

	public RowsRepository() {
	}

	public void add(Row row) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO ROWSCOUNT (UUID) VALUES (?) ") 
							.bind(0, row.getUuid())
						.execute();
			});
	}

	public Row get(String uuid) {
		
		ComponentRepository componentRepository = new ComponentRepository();
		
		Row row = (Row) get(getMode(), uuid, "SELECT * FROM ROWSCOUNT WHERE UUID = ?", Row.class);
		
		if (null != row) {
			row.setChildren(componentRepository.list(row.getUuid()));
		}
		
		return row;
	}

	public void edit(Row component) {}

	public void remove(Row component) {
		remove(getMode(), component.getUuid(), "DELETE FROM ROWSCOUNT WHERE UUID = ?");
	}

	public List<IComponent> list(String uuid) {return null;}
	
	public void setMode(String mode) {
		
		super.setMode(mode);
		
		if (this.getMode().equals(DbConnection.TEST_MODE)) {
			
			handle = DbConnection.getInstance(getMode()).getHandle();
			
			handle.useHandle(handle -> {
				handle.execute("CREATE TABLE IF NOT EXISTS ROWSCOUNT (UUID VARCHAR)");
			});
		}
	}

}
