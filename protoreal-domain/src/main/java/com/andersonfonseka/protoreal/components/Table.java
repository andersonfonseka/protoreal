package com.andersonfonseka.protoreal.components;

import java.util.ArrayList;
import java.util.List;

import com.andersonfonseka.protoreal.components.render.TableRenderer;

public class Table extends Component {

	private String[] header;

	private List<String[]> rows = new ArrayList<>();

	public Table() {
		setHeader(new String[] { "#", "lorem ipsum", "lorem ipsum", "lorem ipsum", "lorem ipsum" });
		addRow(new String[] { "1", "lorem ipsum", "lorem ipsum", "lorem ipsum", "lorem ipsum A" });
		addRow(new String[] { "2", "lorem ipsum", "lorem ipsum", "lorem ipsum", "lorem ipsum B" });
		addRow(new String[] { "3", "lorem ipsum", "lorem ipsum", "lorem ipsum", "lorem ipsum C" });
		addRow(new String[] { "4", "lorem ipsum", "lorem ipsum", "lorem ipsum", "lorem ipsum C" });
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public List<String[]> getRows() {
		return rows;
	}

	public void setRows(List<String[]> rows) {
		this.rows = rows;
	}

	public void addRow(String[] row) {
		this.rows.add(row);
	}

	@Override
	public String doRender() {
		return new TableRenderer(this).execute();
	}

}
