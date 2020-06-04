package com.andersonfonseka.protoreal;


import static org.junit.Assert.assertNotNull;

import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;

import com.andersonfonseka.ComponentFactory;
import com.andersonfonseka.dao.ComponentRepositoryFactory;

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
		assertNotNull(jumbotron);
	}

	@Test
	void edit(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		Jumbotron jumbotron = (Jumbotron) componentFactory.create("jumbotron");
		assertNotNull(jumbotron);
	}

	@Test
	void get(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		Jumbotron jumbotron = (Jumbotron) componentFactory.create("jumbotron");
		assertNotNull(jumbotron);
	}
	
	@Test
	void list(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		Jumbotron jumbotron = (Jumbotron) componentFactory.create("jumbotron");
		assertNotNull(jumbotron);
	}


}
