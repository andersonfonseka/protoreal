package com.andersonfonseka.dao.impl;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.spec.IComponent;

public class RepositoryImpl implements Repository<IComponent> {

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

	public void add(IComponent component) {
		// TODO Auto-generated method stub
		
	}

	public void edit(IComponent component) {
		// TODO Auto-generated method stub
		
	}

	public void remove(IComponent component) {
		// TODO Auto-generated method stub
		
	}

	public IComponent get(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends IComponent> list(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
