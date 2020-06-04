package com.andersonfonseka.protoreal;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.Component;
import com.andersonfonseka.IComponent;
import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.dao.Repository;
import com.andersonfonseka.dao.RepositoryImpl;


public class ContainerRepository extends RepositoryImpl implements Repository<IContainer> {
	
	private static Jdbi handle;	
	
	public ContainerRepository() {}
	
	public void add(IContainer container) {	
	
		IContainer container2 = (Container) container;
		
		for (IComponent row : container2.getChildrenList()) {
			super.getComponentRepository().add(row);
			
			for (IComponent cell: row.getChildrenList()) {
				super.getComponentRepository().add(cell);
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
			((Component) container).setChildren(super.getComponentRepository().list(container.getUuid()));
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
			super.getComponentRepository().add(row);
			
			for (IComponent cell: row.getChildrenList()) {
				super.getComponentRepository().add(cell);
			}
		}
		
	}

	public void remove(IContainer component) {
		remove(component.getUuid(), "DELETE FROM CONTAINER WHERE UUID=?");
	}

	public List<IContainer> list(String uuid) {return null;}
	
}
