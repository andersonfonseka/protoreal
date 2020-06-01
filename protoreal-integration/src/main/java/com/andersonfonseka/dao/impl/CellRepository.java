package com.andersonfonseka.dao.impl;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Cell;

class CellRepository extends RepositoryImpl implements Repository<Cell> {
	
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

	@Override
	public void edit(Cell component) {}

	@Override
	public void remove(Cell component) {}

	@Override
	public List<Cell> list(String uuid) {return null;}
	
	
	
}
