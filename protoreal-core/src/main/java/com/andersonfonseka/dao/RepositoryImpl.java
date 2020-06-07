package com.andersonfonseka.dao;

import java.util.Optional;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.IComponent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class RepositoryImpl<T extends IComponent>{
	
	private Repository<IComponent> componentRepository;

	private static Jdbi handle;
	
	private String mode = "run";

	public IComponent get(String mode, String uuid, String query, Class<?> clazz) {
		
		handle = DbConnection.getInstance(mode).getHandle();
		Optional<IComponent> component = (Optional<IComponent>) handle.withHandle(handle -> 
				handle.createQuery(query)
				.bind(0, uuid)
				.mapToBean(clazz)
				.findFirst());

		
		if (component.isPresent()) {
			return component.get();	
		}
		
		return null;
	}
	
	public void remove(String mode, String uuid, String query) {

		handle = DbConnection.getInstance(mode).getHandle();
		handle.useHandle(handle -> 
			 handle.createUpdate(query)
			.bind(0, uuid)
            .execute());
	}

	public Repository<IComponent> getComponentRepository() {
		return componentRepository;
	}

	public void setComponentRepository(Repository<IComponent> componentRepository) {
		this.componentRepository = componentRepository;
	}
	

}
