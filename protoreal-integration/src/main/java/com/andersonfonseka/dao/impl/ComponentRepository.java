package com.andersonfonseka.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.impl.Component;
import com.andersonfonseka.protoreal.components.spec.IComponent;

class ComponentRepository extends RepositoryImpl implements Repository<IComponent> {
	
	private static Jdbi handle;	

	private Map<String, Repository> repositories = new HashMap<String, Repository>();
	
	public ComponentRepository() {
		this.repositories.put("com.andersonfonseka.protoreal.components.impl.Container", new ContainerRepository(this));
		this.repositories.put("com.andersonfonseka.protoreal.components.Row", new RowsRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.Cell", new CellRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.TextInput", new TextInputRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.SelectInput", new SelectInputRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.impl.Button", new ButtonRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.Label", new LabelRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.TextAreaInput", new TextAreaInputRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.Table", new TableRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.Jumbotron", new JumbotronRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.Card", new CardRepository());
	}
	
	public void add(IComponent component) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO COMPONENTS (UUID, PARENT, TYPE, SITEUUID, PAGEUUID) VALUES (?,?,?,?,?)") 
							.bind(0, component.getUuid())
							.bind(1, component.getParent())
							.bind(2, component.getClass().getName())
							.bind(3, component.getSiteUuid())
							.bind(4, component.getPageUuid())
						.execute();
			});
		
		
		this.repositories.get(component.getClass().getName()).add(component);
	}
	
	public void edit(IComponent component) {
		this.repositories.get(component.getClass().getName()).edit(component);
	}
	
	public List<IComponent> list(String uuid){
		
		handle = DbConnection.getInstance().getHandle();
		
		List<Component> components = handle.withHandle(handle -> 
			 handle.createQuery("SELECT * FROM COMPONENTS WHERE PARENT = ? ORDER BY TIMESTAMP")
			.bind(0, uuid)
            .mapToBean(Component.class)
            .list());
		
		List<IComponent> results = new ArrayList<IComponent>();
		
		for (Component component : components) {
			results.add(this.repositories.get(component.getType()).get(component.getUuid()));
		}
		
		return results;
	}

	
	public void remove(IComponent component) {
		remove(component.getUuid(), "DELETE FROM COMPONENTS WHERE UUID=?");
		this.repositories.get(component.getClass().getName()).remove(component);
	}
	
	public IComponent get(String uuid) {
		
		IComponent component = (IComponent) get(uuid, "SELECT * FROM COMPONENTS WHERE UUID=?", Component.class);
		
		if (null != component) {
			component = this.repositories.get(component.getType()).get(component.getUuid());
		}
				
		return component;
		
	}	
}
