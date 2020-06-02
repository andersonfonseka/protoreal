package com.andersonfonseka.protoreal.components.spec;

import java.util.Collection;

import com.andersonfonseka.protoreal.components.Row;
import com.andersonfonseka.protoreal.components.impl.Component;

public interface IContainer extends IComponent {
	
	public String getRows();

	public void setRows(String rows);

	public String getColumns();

	public void setColumns(String columns);
	
	public void addComponent(int row, int column, Component component);
	
	public void addComponentFromDatabase(int row, int column, Component component);

	public Collection<Row> getRowsList();
	
	public void configure(String rows, String columns);

}
