package com.andersonfonseka.protoreal;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.dao.Repository;
import com.andersonfonseka.dao.RepositoryImpl;

public class TextAreaInputRepository extends RepositoryImpl implements Repository<TextAreaInput> {
	
	private static Jdbi handle;	
	
	public void add(TextAreaInput textInput) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO TEXTAREAINPUT (UUID, LABEL, ROWCOUNT, CONTENT) VALUES (?,?,?,?)") 
							.bind(0, textInput.getUuid())
							.bind(1, textInput.getLabel())
							.bind(2, textInput.getRows())
							.bind(3, textInput.getContent())
						.execute();
			});
	}
	
	public TextAreaInput get(String uuid) {
		return (TextAreaInput) get(getMode(), uuid, "SELECT * FROM TEXTAREAINPUT WHERE UUID = ?", TextAreaInput.class);
	}

	public void edit(TextAreaInput textInput) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE TEXTAREAINPUT SET LABEL=?, ROWCOUNT=?, CONTENT=? WHERE UUID=?") 
							.bind(3, textInput.getUuid())
							.bind(0, textInput.getLabel())
							.bind(1, textInput.getRows())
							.bind(2, textInput.getContent())
						.execute();
			});
	}

	public void remove(TextAreaInput component) {
		remove(getMode(), component.getUuid(), "DELETE FROM TEXTAREAINPUT WHERE UUID = ?");
	}

	public List<TextAreaInput> list(String uuid) {return null;}
	
	public void setMode(String mode) {
		
		super.setMode(mode);
		
		if (this.getMode().equals(DbConnection.TEST_MODE)) {
			
			handle = DbConnection.getInstance(getMode()).getHandle();
			
			handle.useHandle(handle -> {
				handle.execute("CREATE TABLE IF NOT EXISTS TEXTAREAINPUT (UUID VARCHAR, LABEL VARCHAR, ROWCOUNT VARCHAR)");
			});
		}
	}
	
}
