package com.andersonfonseka.dao.impl;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Component;
import com.andersonfonseka.protoreal.components.Container;

class ContainerRepository extends RepositoryImpl implements Repository<Container> {
	
	private Repository<Component> componentRepository;
	
	private static Jdbi handle;	
	
	public ContainerRepository(Repository<Component> repository) {
		componentRepository =  repository;
	}
	
	public void add(Container container) {	
	
		Container container2 = (Container) container;
		
		for (Component row : container2.getChildrenList()) {
			componentRepository.add(row);
			
			for (Component cell: row.getChildrenList()) {
				componentRepository.add(cell);
			}
		}
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO CONTAINER (UUID, ROWCOUNT, COLUMNS) VALUES (?,?,?)") 
							.bind(0, container.getUuid())
							.bind(1, container.getRows())
							.bind(2, container.getColumns())
						.execute();
			});
	}
	
	public Container get(String uuid) {
		
		Container container = (Container) get(uuid, "SELECT * FROM CONTAINER WHERE UUID = ?", Container.class);
		
		if (null != container) {
			container.setChildren(componentRepository.list(container.getUuid()));
		}
			
		return container;
	}

	@Override
	public void edit(Container container) {
		
		remove(container.getUuid(),  "DELETE FROM COMPONENTS WHERE PARENT = ?");
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE CONTAINER SET ROWCOUNT=?, COLUMNS=? WHERE UUID=?") 
							.bind(2, container.getUuid())
							.bind(0, container.getRows())
							.bind(1, container.getColumns())
						.execute();
			});
		
		for (Component row : container.getChildrenList()) {
			componentRepository.add(row);
			
			for (Component cell: row.getChildrenList()) {
				componentRepository.add(cell);
			}
		}
		
	}

	@Override
	public void remove(Container component) {}

	@Override
	public List<Container> list(String uuid) {return null;}
	
}
