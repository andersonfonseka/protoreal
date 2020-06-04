package com.andersonfonseka.protoreal;

import static org.junit.Assert.assertNotNull;

import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;

import com.andersonfonseka.ComponentFactory;
import com.andersonfonseka.dao.ComponentRepositoryFactory;

@EnableWeld
class ButtonTest {
	
	
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
		assertNotNull(button);
	}

	@Test
	void edit(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		IButton button = (IButton) componentFactory.create("button");
		assertNotNull(button);
	}

	@Test
	void get(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		IButton button = (IButton) componentFactory.create("button");
		assertNotNull(button);
	}
	
	@Test
	void list(ComponentFactory componentFactory, ComponentRepositoryFactory componentRepository) {
		IButton button = (IButton) componentFactory.create("button");
		assertNotNull(button);
	}


}
