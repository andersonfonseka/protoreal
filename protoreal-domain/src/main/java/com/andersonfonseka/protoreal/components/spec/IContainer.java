package com.andersonfonseka.protoreal.components.spec;

import java.util.Collection;

import com.andersonfonseka.protoreal.components.Row;
import com.andersonfonseka.protoreal.components.impl.Component;

public interface IContainer extends IComponent {
	
	public int getRows();

	public void setRows(String rows);

	public int getColumns();

	public void setColumns(String columns);
	
	public void addComponent(int row, int column, Component component);
	
	public void addComponentFromDatabase(int row, int column, Component component);

	public Collection<Row> getRowsList();
	
	public void configure(int rows, int columns);

}
