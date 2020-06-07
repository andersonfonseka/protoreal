package com.andersonfonseka.protoreal;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.IComponent;
import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.dao.Repository;
import com.andersonfonseka.dao.RepositoryImpl;

public class CardRepository extends RepositoryImpl implements Repository<Card> {
	
	private static Jdbi handle;	
	
	public CardRepository() {}
	
	public void add(Card card) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO CARD (UUID, TITLE, SUBTITLE, CONTENT) VALUES (?,?,?,?)") 
							.bind(0, card.getUuid())
							.bind(1, card.getTitle())
							.bind(2, card.getSubtitle())
							.bind(3, card.getContent())
						.execute();
			});
		
	}

	public Card get(String uuid) {
		return (Card) get(getMode(), uuid, "SELECT * FROM CARD WHERE UUID=?", Card.class);
	}


	public void edit(Card card) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE CARD SET TITLE=?, SUBTITLE=?, CONTENT=? WHERE UUID=?") 
							.bind(3, card.getUuid())
							.bind(0, card.getTitle())
							.bind(1, card.getSubtitle())
							.bind(2, card.getContent())
						.execute();
			});

	}

	public void remove(Card component) {
		remove(getMode(), component.getUuid(), "DELETE FROM CARD WHERE UUID=?");
	}

	public List<IComponent> list(String uuid) {return null;}
	
	public void setMode(String mode) {
		
		super.setMode(mode);
		
		if (this.getMode().equals(DbConnection.TEST_MODE)) {
			
			handle = DbConnection.getInstance(getMode()).getHandle();
			
			handle.useHandle(handle -> {
				handle.execute("CREATE TABLE IF NOT EXISTS CARD (UUID VARCHAR, TITLE VARCHAR, SUBTITLE VARCHAR, CONTENT VARCHAR)");
			});
		}
	}
	
}
