package com.andersonfonseka.dao.impl;

import java.util.Optional;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Component;

public class RepositoryImpl {

	private static Jdbi handle;

	public Component get(String uuid, String query, Class<? extends Component> clazz) {

		handle = DbConnection.getInstance().getHandle();
		Optional<Component> component = (Optional<Component>) handle.withHandle(handle -> 
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
