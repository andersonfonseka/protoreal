package com.andersonfonseka.protoreal.components;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.andersonfonseka.protoreal.components.render.TableRenderer;

public class Table extends Component {

	private String[] header;

	private List<String[]> rows = new ArrayList<>();

	public Table() {
		setHeader("#, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum");
		addRow(new String[] { "1", "lorem ipsum", "lorem ipsum", "lorem ipsum", "lorem ipsum A" });
		addRow(new String[] { "2", "lorem ipsum", "lorem ipsum", "lorem ipsum", "lorem ipsum B" });
		addRow(new String[] { "3", "lorem ipsum", "lorem ipsum", "lorem ipsum", "lorem ipsum C" });
		addRow(new String[] { "4", "lorem ipsum", "lorem ipsum", "lorem ipsum", "lorem ipsum C" });
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String pHeader) {
		
		this.header = null;
		String[] header = pHeader.split(",");
		this.header = header;

	}

	public List<String[]> getRows() {
		return rows;
	}

	public void setRows(String rows) {
		
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

	@Override
	public String doRender() {
		return new TableRenderer(this).execute();
	}

	@Override
	public String doEdit() {
		return new TableRenderer(this).executeProperties();
	}

	@Override
	public String doPreview() {
		return new TableRenderer(this).executePreview();
	}

}
