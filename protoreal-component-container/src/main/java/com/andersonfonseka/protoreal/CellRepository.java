package com.andersonfonseka.protoreal;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.IComponent;
import com.andersonfonseka.dao.ComponentRepository;
import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.dao.Repository;
import com.andersonfonseka.dao.RepositoryImpl;

public class CellRepository extends RepositoryImpl implements Repository<Cell> {
	
	private static Jdbi handle;	
	
	public CellRepository() {}
	
	public void add(Cell cell) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO CELL (UUID) VALUES (?)") 
							.bind(0, cell.getUuid())
						.execute();
			});
	}
	
	public Cell get(String uuid) {
		
		ComponentRepository componentRepository = new ComponentRepository();
		
		Cell cell  = (Cell) get(uuid, "SELECT * FROM CELL WHERE UUID = ?", Cell.class);
		cell.setChildren(componentRepository.list(cell.getUuid()));
		
		return cell;
	}

	public void edit(Cell component) {}

	public void remove(Cell component) {}

	public List<IComponent> list(String uuid) {return null;}
	
	
	
}
