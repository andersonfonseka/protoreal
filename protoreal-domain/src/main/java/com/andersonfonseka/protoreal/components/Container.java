package com.andersonfonseka.protoreal.components;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.andersonfonseka.protoreal.components.render.ContainerRenderer;

public class Container extends Component {

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

	private void configure(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		
		for (int i = 0; i < this.rows; i++) {
			
			Row row = new Row();
			rowsMap.put(i, row);
			this.addChild(row);
			
			for (int j = 0; j < this.columns; j++) {
				row.addCell(j, new Cell());;
			}
		}
	}

	public int getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = Integer.valueOf(rows);
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = Integer.valueOf(columns);
		
		this.rowsMap.clear();
		
		configure(this.rows, this.columns);
	}
	
	public void addComponent(int row, int column, Component component) {
		this.rowsMap.get(row).getCell(column).addChild(component);
		this.addChild(component);
	}

	public Collection<Row> getRowsList(){
		return this.rowsMap.values();
	}
	
	@Override
	public String doRender() {
		return new ContainerRenderer(this).execute();
	}

	@Override
	public String doEdit() {
		return new ContainerRenderer(this).executeProperties();
	}

	
	
}
