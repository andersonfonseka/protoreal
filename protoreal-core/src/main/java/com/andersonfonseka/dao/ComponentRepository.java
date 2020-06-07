package com.andersonfonseka.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.Component;
import com.andersonfonseka.IComponent;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ComponentRepository extends RepositoryImpl implements Repository<IComponent> {
	
	private static Jdbi handle;	
	
	private String mode = "run";

	private Map<String, String> repositories = new HashMap<String, String>();
	
	public ComponentRepository() {
		
		this.repositories.put("com.andersonfonseka.Page", "com.andersonfonseka.dao.PageRepository");
		
		this.repositories.put("com.andersonfonseka.protoreal.Button", "com.andersonfonseka.protoreal.ButtonRepository");
		this.repositories.put("com.andersonfonseka.protoreal.Container", "com.andersonfonseka.protoreal.ContainerRepository");
		this.repositories.put("com.andersonfonseka.protoreal.Row", "com.andersonfonseka.protoreal.RowsRepository");
		this.repositories.put("com.andersonfonseka.protoreal.Cell", "com.andersonfonseka.protoreal.CellRepository");
		this.repositories.put("com.andersonfonseka.protoreal.TextInput", "com.andersonfonseka.protoreal.TextInputRepository");
		this.repositories.put("com.andersonfonseka.protoreal.SelectInput", "com.andersonfonseka.protoreal.SelectInputRepository");
		this.repositories.put("com.andersonfonseka.protoreal.Label", "com.andersonfonseka.protoreal.LabelRepository");
		this.repositories.put("com.andersonfonseka.protoreal.TextAreaInput", "com.andersonfonseka.protoreal.TextAreaInputRepository");
		this.repositories.put("com.andersonfonseka.protoreal.Table", "com.andersonfonseka.protoreal.TableRepository");
		this.repositories.put("com.andersonfonseka.protoreal.Jumbotron", "com.andersonfonseka.protoreal.JumbotronRepository");
		this.repositories.put("com.andersonfonseka.protoreal.Card", "com.andersonfonseka.protoreal.CardRepository");
	}
	
	public void add(IComponent component) {
		
		handle = DbConnection.getInstance(getMode()).getHandle();
		
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
		
		try {
			Repository repository = (Repository) Class.forName(this.repositories.get(component.getClass().getName())).newInstance();
			repository.setComponentRepository(this);
			repository.setMode(getMode());
			
			repository.add(component);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void edit(IComponent component) {
		
		try {
			Repository repository = (Repository) Class.forName(this.repositories.get(component.getClass().getName())).newInstance();
			repository.setComponentRepository(this);
			repository.setMode(getMode());
			
			repository.edit(component);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<IComponent> list(String uuid){
		
		handle = DbConnection.getInstance(getMode()).getHandle();
		
		List<Component> components = handle.withHandle(handle -> 
			 handle.createQuery("SELECT * FROM COMPONENTS WHERE PARENT = ? ORDER BY TIMESTAMP")
			.bind(0, uuid)
            .mapToBean(Component.class)
            .list());
		
		List<IComponent> results = new ArrayList<IComponent>();
		
		for (Component component : components) {
			
			try {
				Repository repository = (Repository) Class.forName(this.repositories.get(component.getType())).newInstance();
				repository.setComponentRepository(this);
				repository.setMode(getMode());
				
				results.add(repository.get(component.getUuid()));
				
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return results;
	}

	
	public void remove(IComponent component) {
		remove(getMode(), component.getUuid(), "DELETE FROM COMPONENTS WHERE UUID=?");
		
		try {
			Repository repository = (Repository) Class.forName(this.repositories.get(component.getClass().getName())).newInstance();
			repository.setComponentRepository(this);
			repository.setMode(getMode());
			
			repository.remove(component);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public IComponent get(String uuid) {
		
		IComponent component = (IComponent) get(getMode(), uuid, "SELECT * FROM COMPONENTS WHERE UUID=?", Component.class);
		
		if (null != component) {

			try {
				Repository repository = (Repository) Class.forName(this.repositories.get(component.getType())).newInstance();
				repository.setComponentRepository(this);
				repository.setMode(getMode());
				
				component = repository.get(component.getUuid());
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
				
		return component;
		
	}



	public void setMode(String mode) {
		this.mode = mode;
		
		if (this.mode.equals(DbConnection.TEST_MODE)) {
			
			handle = DbConnection.getInstance(getMode()).getHandle();
			
			handle.useHandle(handle -> {
				handle.execute("CREATE TABLE IF NOT EXISTS COMPONENTS (UUID VARCHAR, PARENT VARCHAR, TYPE VARCHAR, SITEUUID VARCHAR, PAGEUUID VARCHAR, TIMESTAMP TIMESTAMP)");
			});
		}
	}	
}
