package com.andersonfonseka.dao.impl;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.impl.Component;
import com.andersonfonseka.protoreal.components.impl.Container;
import com.andersonfonseka.protoreal.components.spec.IComponent;
import com.andersonfonseka.protoreal.components.spec.IContainer;

class ContainerRepository extends RepositoryImpl implements Repository<IContainer> {
	
	private Repository<IComponent> componentRepository;
	
	private static Jdbi handle;	
	
	public ContainerRepository(Repository<IComponent> repository) {
		componentRepository =  repository;
	}
	
	public void add(IContainer container) {	
	
		IContainer container2 = (Container) container;
		
		for (IComponent row : container2.getChildrenList()) {
			componentRepository.add(row);
			
			for (IComponent cell: row.getChildrenList()) {
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
	
	public IContainer get(String uuid) {
		
		IContainer container = (IContainer) get(uuid, "SELECT * FROM CONTAINER WHERE UUID = ?", Container.class);
		
		if (null != container) {
			((Component) container).setChildren(componentRepository.list(container.getUuid()));
		}
			
		return container;
	}

	public void edit(IContainer container) {
		
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
		
		for (IComponent row : container.getChildrenList()) {
			componentRepository.add(row);
			
			for (IComponent cell: row.getChildrenList()) {
				componentRepository.add(cell);
			}
		}
		
	}

	public void remove(IContainer component) {
		remove(component.getUuid(), "DELETE FROM CONTAINER WHERE UUID=?");
	}

	public List<IContainer> list(String uuid) {return null;}
	
}
