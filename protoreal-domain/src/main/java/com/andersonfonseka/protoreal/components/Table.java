package com.andersonfonseka.protoreal.components;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.andersonfonseka.protoreal.components.common.SelectItem;
import com.andersonfonseka.protoreal.components.impl.Component;
import com.andersonfonseka.protoreal.components.render.TableRenderer;

public class Table extends Component {
	
	private String type = "Simple";
	
	private List<SelectItem> listTypes = new ArrayList<SelectItem>();

	private String headerValues;
	
	private String dataValues;
	
	
	private String[] header;

	private List<String[]> rows = new ArrayList<>();

	public Table() {
		setHeader("#, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum");

		StringBuilder data = new StringBuilder();
		data.append("1, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum A#");
		data.append("2, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum B#");
		data.append("3, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum C#");
		data.append("4, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum D#");
		data.append("5, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum E");
		
		setRows(data.toString());
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String pHeader) {
		
		this.headerValues = pHeader;
		
		this.header = null;
		String[] header = pHeader.split(",");
		this.header = header;

	}

	public List<String[]> getRows() {
		return rows;
	}

	public void setRows(String rows) {
		
		this.dataValues = rows;
				
		this.rows.clear();
		
		StringTokenizer st = new StringTokenizer(rows, "#");
		
		while (st.hasMoreElements()) {
			
			String stRow = st.nextElement().toString();
			addRow(stRow.split(","));
		}
	}

	public void addRow(String[] row) {
		this.rows.add(row);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public List<SelectItem> getListTypes() {
		
		listTypes.clear();
		
		listTypes.add(new SelectItem("Simple", "Modo Simples"));
		listTypes.add(new SelectItem("Search", "Modo Pesquisa"));
		
		for (SelectItem selectItem : listTypes) {
			if (selectItem.getValue().equals(this.type)) {
				selectItem.setSelected("selected");
			}
		}
		
		return listTypes;
	}

	public void setListTypes(List<SelectItem> listTypes) {
		this.listTypes = listTypes;
	}
	
	public String getHeaderValues() {
		return headerValues;
	}

	public void setHeaderValues(String headerValues) {
		this.headerValues = headerValues;
	}

	public String getDataValues() {
		return dataValues;
	}

	public void setDataValues(String dataValues) {
		this.dataValues = dataValues;
	}

	public String doRender() {
		return new TableRenderer(this).execute();
	}

	public String doEdit() {
		return new TableRenderer(this).executeProperties();
	}

	public String doPreview() {
		return new TableRenderer(this).executePreview();
	}

}
