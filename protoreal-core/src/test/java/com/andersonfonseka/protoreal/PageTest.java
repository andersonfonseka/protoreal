package com.andersonfonseka.protoreal;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;

import com.andersonfonseka.ComponentFactory;
import com.andersonfonseka.IPage;
import com.andersonfonseka.dao.ComponentRepositoryFactory;
import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.dao.Repository;

@EnableWeld
public class PageTest {
	
	
	@WeldSetup
	public static WeldInitiator weld = WeldInitiator.of(WeldInitiator.createWeld()
														.beanClasses(ComponentFactory.class, ComponentRepositoryFactory.class));
	
	@Test
	void create(ComponentFactory absFactory) {
		IPage page = (IPage) absFactory.create("page");
		assertNotNull(page);
	}
	
	@Test
	void add(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		IPage page = (IPage) componentFactory.create("page");
		
		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(page);
		
		assertNotNull(page);
	}

	@Test
	void edit(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		IPage page = (IPage) componentFactory.create("page");
		
		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(page);
		
		page = (IPage) repository.get(page.getUuid());
		page.setTitle("Hello Test");
		
		repository.edit(page);
		
		page = (IPage) repository.get(page.getUuid());
		
		assertEquals("Hello Test", page.getTitle());
	}

	@Test
	void get(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		
		IPage page = (IPage) componentFactory.create("page");
		
		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(page);

		page = (IPage) repository.get(page.getUuid());
		
		assertNotNull(page);
	}
	
	@Test
	void remove(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		
		IPage page = (IPage) componentFactory.create("page");
		
		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(page);
		
		page = (IPage) repository.get(page.getUuid());
		
		repository.remove(page);
		
		page = (IPage) repository.get(page.getUuid());
		
		assertNull(page);
	}


}
