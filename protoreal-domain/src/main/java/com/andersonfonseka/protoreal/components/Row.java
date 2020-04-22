package com.andersonfonseka.protoreal.components;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Row extends Component {
	
	private Map<Integer, Cell> cellsMap = new HashMap<Integer, Cell>();
	
	public void addCell(int column, Cell cell) {
		this.cellsMap.put(column, cell);
		this.addChild(cell);
	}

	public Cell getCell(int column) {
		return cellsMap.get(column);
	}

	public Collection<Cell> getCellList() {
		return this.cellsMap.values();
	}
	
	@Override
	public String doRender() {
		return "";
	}


	
}
