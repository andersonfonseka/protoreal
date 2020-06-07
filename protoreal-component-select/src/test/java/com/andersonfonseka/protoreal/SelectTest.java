package com.andersonfonseka.protoreal;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;

import com.andersonfonseka.ComponentFactory;
import com.andersonfonseka.dao.ComponentRepositoryFactory;
import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.dao.Repository;

@EnableWeld
class SelectInputTest {
	
	
	@WeldSetup
	public static WeldInitiator weld = WeldInitiator.of(WeldInitiator.createWeld()
														.beanClasses(ComponentFactory.class, ComponentRepositoryFactory.class));
	
	@Test
	void create(ComponentFactory absFactory) {
		SelectInput select = (SelectInput) absFactory.create("selectItem");
		assertNotNull(select);
	}
	
	@Test
	void add(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		SelectInput select = (SelectInput) componentFactory.create("selectItem");
		
		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(select);
		
		assertNotNull(select);
	}

	@Test
	void edit(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		SelectInput select = (SelectInput) componentFactory.create("selectItem");

		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(select);

		select = (SelectInput) repository.get(select.getUuid());
		
		select.setLabel("Teste");
		
		repository.edit(select);
		
		select = (SelectInput) repository.get(select.getUuid());
		
		assertEquals("Teste", select.getLabel());
	}

	@Test
	void get(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		SelectInput select = (SelectInput) componentFactory.create("selectItem");

		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(select);

		select = (SelectInput) repository.get(select.getUuid());

		assertNotNull(select);
	}
	
	@Test
	void remove(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		
		SelectInput select = (SelectInput) componentFactory.create("selectItem");

		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(select);

		select = (SelectInput) repository.get(select.getUuid());
		
		repository.remove(select);
		
		select = (SelectInput) repository.get(select.getUuid());
		
		assertNull(select);
	}


}
