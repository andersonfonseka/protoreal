package com.andersonfonseka.protoreal.components.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.andersonfonseka.protoreal.components.Cell;
import com.andersonfonseka.protoreal.components.Row;
import com.andersonfonseka.protoreal.components.render.ContainerRenderer;
import com.andersonfonseka.protoreal.components.spec.IContainer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Container extends Component implements IContainer {

	private int rows;
	
	private int columns;
	
	private Map<Integer, Row> rowsMap = new HashMap<Integer, Row>();
	
	public Container() {
		this(2, 2);
	}
	
	public Container(int rows, int columns) {
		super();
		configure(rows, columns);
	}

	public void configure(int rows, int columns) {
		
		this.getChildrenList().clear();
		this.rowsMap.clear();
		
		
		this.rows = rows;
		this.columns = columns;
		
		for (int i = 0; i < this.rows; i++) {
			
			Row row = new Row();
			rowsMap.put(i, row);
			this.addChild(row);
			
			for (int j = 0; j < this.columns; j++) {
				row.addCell(j, new Cell());
			}
		}
	}

	public void setColumns(String columns) {
		this.columns = Integer.valueOf(columns);
	}
	
	public void setRows(String rows) {
		this.rows = Integer.valueOf(rows);
	}
	
	public void addComponent(int row, int column, Component component) {
		this.rowsMap.get(row).getCell(column).addChild(component);
		this.addChild(component);
	}
	
	public void addComponentFromDatabase(int row, int column, Component component) {
		this.rowsMap.get(row).getCell(column).addChild(component);
	}

	public Collection<Row> getRowsList(){
		return this.rowsMap.values();
	}
	
	public String doRender() {
		return new ContainerRenderer(this).execute();
	}

	
	public String doEdit() {
		return new ContainerRenderer(this).executeProperties();
	}

	
	public String doPreview() {
		return new ContainerRenderer(this).executePreview();
	}


	
}
