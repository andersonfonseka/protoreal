package com.andersonfonseka.protoreal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;

import com.andersonfonseka.ComponentFactory;
import com.andersonfonseka.dao.ComponentRepositoryFactory;
import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.dao.Repository;

@EnableWeld
public class ButtonTest {
	
	
	@WeldSetup
	public static WeldInitiator weld = WeldInitiator.of(WeldInitiator.createWeld()
														.beanClasses(ComponentFactory.class, ComponentRepositoryFactory.class));
	
	@Test
	void create(ComponentFactory absFactory) {
		IButton button = (IButton) absFactory.create("button");
		assertNotNull(button);
	}
	
	@Test
	void add(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		IButton button = (IButton) componentFactory.create("button");
		
		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(button);
		
		assertNotNull(button);
	}

	@Test
	void edit(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		IButton button = (IButton) componentFactory.create("button");

		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(button);

		button = (IButton) repository.get(button.getUuid());
		
		button.setTitle("Hello Button");
		
		repository.edit(button);
		
		button = (IButton) repository.get(button.getUuid());
		
		assertEquals("Hello Button", button.getTitle());
	}

	@Test
	void get(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		IButton button = (IButton) componentFactory.create("button");

		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(button);

		button = (IButton) repository.get(button.getUuid());

		assertNotNull(button);
	}
	
	@Test
	void remove(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		
		IButton button = (IButton) componentFactory.create("button");

		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(button);

		button = (IButton) repository.get(button.getUuid());
		
		repository.remove(button);
		
		button = (IButton) repository.get(button.getUuid());
		
		assertNull(button);
	}


}
