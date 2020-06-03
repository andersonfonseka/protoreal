package com.andersonfonseka.dao;

import java.util.Optional;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.IComponent;

public abstract class RepositoryImpl {

	private static Jdbi handle;

	public IComponent get(String uuid, String query, Class<?> clazz) {

		handle = DbConnection.getInstance().getHandle();
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
	
	public void remove(String uuid, String query) {

		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> 
			 handle.createUpdate(query)
			.bind(0, uuid)
            .execute());
	}

}
