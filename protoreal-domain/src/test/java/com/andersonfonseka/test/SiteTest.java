package com.andersonfonseka.test;

import org.junit.jupiter.api.Test;

import com.andersonfonseka.protoreal.components.Container;
import com.andersonfonseka.protoreal.components.Jumbotron;
import com.andersonfonseka.protoreal.components.Page;
import com.andersonfonseka.protoreal.components.Site;

class SiteTest {

	@Test
	void test() {
	
		Site site = new Site("PetLand - A loja do seu bichinho");
		
		Page page0 = new Page("Institucional");
		
		Container container0 = new Container(1, 1);
		Container container1 = new Container(1, 3);
		
		page0.addChild(container0);
		page0.addChild(container1);
		
		container0.addComponent(0, 0, new Jumbotron("",""));
		
		Page page1 = new Page("Eventos");
		Page page2 = new Page("Dicas & Saude");
		Page page3 = new Page("Produtos");
		
		site.addChild(page0);
		site.addChild(page1);
		site.addChild(page2);
		site.addChild(page3);
		
		
	}

}
