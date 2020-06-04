package com.andersonfonseka.protoreal;


import static org.junit.Assert.assertNotNull;

import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;

import com.andersonfonseka.ComponentFactory;
import com.andersonfonseka.dao.ComponentRepositoryFactory;

@EnableWeld
class TextInputTest {
	
	
	@WeldSetup
	public static WeldInitiator weld = WeldInitiator.of(WeldInitiator.createWeld()
														.beanClasses(ComponentFactory.class, ComponentRepositoryFactory.class));
	
	@Test
	void create(ComponentFactory absFactory) {
		
		TextInput textInput = (TextInput) absFactory.create("textInput");
		Label label = (Label) absFactory.create("label");
		TextAreaInput textAreaInput = (TextAreaInput) absFactory.create("textArea");
		
		
		assertNotNull(textInput);
		assertNotNull(label);
		assertNotNull(textAreaInput);

	}
	
	@Test
	void add(ComponentFactory absFactory, ComponentRepositoryFactory componentRepository) {
		TextInput textInput = (TextInput) absFactory.create("textInput");
		Label label = (Label) absFactory.create("label");
		TextAreaInput textAreaInput = (TextAreaInput) absFactory.create("textArea");
		
		
		assertNotNull(textInput);
		assertNotNull(label);
		assertNotNull(textAreaInput);	}

	@Test
	void edit(ComponentFactory absFactory, ComponentRepositoryFactory componentRepository) {
		TextInput textInput = (TextInput) absFactory.create("textInput");
		Label label = (Label) absFactory.create("label");
		TextAreaInput textAreaInput = (TextAreaInput) absFactory.create("textArea");
		
		
		assertNotNull(textInput);
		assertNotNull(label);
		assertNotNull(textAreaInput);	}

	@Test
	void get(ComponentFactory absFactory, ComponentRepositoryFactory componentRepository) {
		TextInput textInput = (TextInput) absFactory.create("textInput");
		Label label = (Label) absFactory.create("label");
		TextAreaInput textAreaInput = (TextAreaInput) absFactory.create("textArea");
		
		
		assertNotNull(textInput);
		assertNotNull(label);
		assertNotNull(textAreaInput);	}
	
	@Test
	void list(ComponentFactory absFactory, ComponentRepositoryFactory componentRepository) {
		TextInput textInput = (TextInput) absFactory.create("textInput");
		Label label = (Label) absFactory.create("label");
		TextAreaInput textAreaInput = (TextAreaInput) absFactory.create("textArea");
		
		
		assertNotNull(textInput);
		assertNotNull(label);
		assertNotNull(textAreaInput);	}


}
