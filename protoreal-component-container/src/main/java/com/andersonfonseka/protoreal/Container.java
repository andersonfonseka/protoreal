package com.andersonfonseka.protoreal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.andersonfonseka.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Container extends Component implements IContainer {

	private String rows;
	
	private String rowCount;
	
	private String columns;
	
	private Map<Integer, Row> rowsMap = new HashMap<Integer, Row>();
	
	public Container() {
		this("2", "2");
	}
	
	public Container(String rows, String columns) {
		super();
		configure(rows, columns);
	}

	public void configure(String rows, String columns) {
		
		this.getChildrenList().clear();
		this.rowsMap.clear();
		
		
		this.rows = String.valueOf(rows);
		this.columns = String.valueOf(columns);
		
		for (int i = 0; i <  Integer.valueOf(rows).intValue(); i++) {
			
			Row row = new Row();
			rowsMap.put(i, row);
			this.addChild(row);
			
			for (int j = 0; j < Integer.valueOf(columns).intValue(); j++) {
				row.addCell(j, new Cell());
			}
		}
	}
	
	public void setRows(String rows) {
		this.rows = rows;
	}

	public void setColumns(String pColumns) {
		this.columns = pColumns;
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
