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
class JumbotronTest {
	
	
	@WeldSetup
	public static WeldInitiator weld = WeldInitiator.of(WeldInitiator.createWeld()
														.beanClasses(ComponentFactory.class, ComponentRepositoryFactory.class));
	
	@Test
	void create(ComponentFactory absFactory) {
		Jumbotron jumbotron = (Jumbotron) absFactory.create("jumbotron");
		assertNotNull(jumbotron);
	}
	
	@Test
	void add(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		Jumbotron jumbotron = (Jumbotron) componentFactory.create("jumbotron");
		
		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(jumbotron);
		
		assertNotNull(jumbotron);
	}

	@Test
	void edit(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		Jumbotron jumbotron = (Jumbotron) componentFactory.create("jumbotron");

		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(jumbotron);

		jumbotron = (Jumbotron) repository.get(jumbotron.getUuid());
		
		jumbotron.setTitle("Teste");
		
		repository.edit(jumbotron);
		
		jumbotron = (Jumbotron) repository.get(jumbotron.getUuid());
		
		assertEquals("Teste", jumbotron.getTitle());
	}

	@Test
	void get(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		Jumbotron jumbotron = (Jumbotron) componentFactory.create("jumbotron");

		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(jumbotron);

		jumbotron = (Jumbotron) repository.get(jumbotron.getUuid());

		assertNotNull(jumbotron);
	}
	
	@Test
	void remove(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		
		Jumbotron jumbotron = (Jumbotron) componentFactory.create("jumbotron");

		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(jumbotron);

		jumbotron = (Jumbotron) repository.get(jumbotron.getUuid());
		
		repository.remove(jumbotron);
		
		jumbotron = (Jumbotron) repository.get(jumbotron.getUuid());
		
		assertNull(jumbotron);
	}


}
