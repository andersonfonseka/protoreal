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
class ContainerTest {
	
	
	@WeldSetup
	public static WeldInitiator weld = WeldInitiator.of(WeldInitiator.createWeld()
														.beanClasses(ComponentFactory.class, ComponentRepositoryFactory.class));
	
	@Test
	void create(ComponentFactory absFactory) {
		Container container = (Container) absFactory.create("container");
		assertNotNull(container);
	}
	
	@Test
	void add(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		Container container = (Container) componentFactory.create("container");
		
		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(container);
		
		assertNotNull(container);
	}

	@Test
	void edit(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		Container container = (Container) componentFactory.create("container");

		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(container);

		container = (Container) repository.get(container.getUuid());
		
		container.setRows("1");
		
		repository.edit(container);
		
		container = (Container) repository.get(container.getUuid());
		
		assertEquals("1", container.getRowCount());
	}

	@Test
	void get(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		Container container = (Container) componentFactory.create("container");

		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(container);

		container = (Container) repository.get(container.getUuid());

		assertNotNull(container);
	}
	
	@Test
	void remove(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		
		Container container = (Container) componentFactory.create("container");

		Repository repository = componentRepository.getComponentRepository();
		repository.setMode(DbConnection.TEST_MODE);
		repository.add(container);

		container = (Container) repository.get(container.getUuid());
		
		repository.remove(container);
		
		container = (Container) repository.get(container.getUuid());
		
		assertNull(container);
	}


}
