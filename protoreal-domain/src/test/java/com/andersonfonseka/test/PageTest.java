package com.andersonfonseka.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.andersonfonseka.protoreal.components.Button;
import com.andersonfonseka.protoreal.components.Container;
import com.andersonfonseka.protoreal.components.Form;
import com.andersonfonseka.protoreal.components.NavDropdown;
import com.andersonfonseka.protoreal.components.NavLink;
import com.andersonfonseka.protoreal.components.Navbar;
import com.andersonfonseka.protoreal.components.Page;
import com.andersonfonseka.protoreal.components.SelectInput;
import com.andersonfonseka.protoreal.components.Separator;
import com.andersonfonseka.protoreal.components.Site;
import com.andersonfonseka.protoreal.components.Table;
import com.andersonfonseka.protoreal.components.TextAreaInput;
import com.andersonfonseka.protoreal.components.TextInput;

class PageTest {

	@Test
	void test() {

		Site site = new Site("Portal Administrativo - PJ");

		Page page0 = new Page("");
		site.addChild(page0);
		
		Navbar navbar = new Navbar();
		page0.addChild(navbar);
		
		NavDropdown dropdown = new NavDropdown("Sistema de Reserva");
		navbar.addChild(dropdown);

		NavLink navLink = new NavLink("Fornecedores");
		navbar.addChild(navLink);
		
		
		Page page = new Page("Gerenciar Mensagem");
		site.addChild(page);

		Container container4 = new Container(1, 1);
		page.addChild(container4);
		
		Form form = new Form();
		container4.addComponent(0, 0, form);
			
		Container container = new Container(2, 2);
		form.addChild(container);
		
		TextInput textInput0 = new TextInput("Codigo da Mensagem");
		SelectInput selectInput = new SelectInput("Tipo de Mensagem", "GACB", "FNEB", "GCCD");

		container.addComponent(0, 0, textInput0);
		container.addComponent(0, 1, selectInput);
		
		Container container3 = new Container(2, 1);
		form.addChild(container3);

		TextAreaInput textInput1 = new TextAreaInput("Mensagem Interna", 3);
		TextAreaInput textInput2 = new TextAreaInput("Mensagem Externa", 3);
				
		container3.addComponent(0, 0, textInput1);
		container3.addComponent(1, 0, textInput2);

		Container container1 = new Container(1, 1);
		page.addChild(container1);

		Button button1 = new Button("Limpar", Button.INFO);
		Button button2 = new Button("Cadastrar", Button.SECONDARY);
		Button button0 = new Button("Pesquisar", Button.PRIMARY);

		container1.addComponent(0, 0, button1);
		container1.addComponent(0, 0, button2);
		container1.addComponent(0, 0, button0);
		container1.addComponent(0, 0, new Separator());

		Container container2 = new Container(1, 1);
		page.addChild(container2);

		Table table = new Table();
		table.setHeader(new String[] { "#", "Sistema de origem", "Mensagem interna", "Mensagem externa", "Tipo de mensagem"});
		table.addRow(new String[] { "1", "GACB", "lorem ipsum", "lorem ipsum", "Tipo A" });
		table.addRow(new String[] { "2", "FNEB", "lorem ipsum", "lorem ipsum", "Tipo B" });
		table.addRow(new String[] { "3", "NPC", "lorem ipsum", "lorem ipsum", "Tipo C" });
		table.addRow(new String[] { "4", "LEAS", "lorem ipsum", "lorem ipsum", "Tipo C" });

		container2.addComponent(0, 0, table);

		StringBuilder builder = new StringBuilder();
		builder.append(site.doRender());

		File file = new File("c://temp//teste.html");
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(file);
			fos.write(builder.toString().getBytes());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(builder.toString());

	}

}
