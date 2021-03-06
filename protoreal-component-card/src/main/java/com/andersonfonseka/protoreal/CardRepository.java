package com.andersonfonseka.protoreal;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.Component;
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
						.createUpdate("INSERT INTO CARD (UUID, TITLE, CONTENT, FILECARD, BUTTONTEXT, BUTTONURL) VALUES (?,?,?,?,?,?)") 
							.bind(0, card.getUuid())
							.bind(1, card.getTitle())
							.bind(2, card.getContent())
							.bind(3, card.getFileCard())
							.bind(4, card.getButtonText())
							.bind(5, card.getButtonUrl())
						.execute();
			});
		
	}

	public Card get(String uuid) {
		
		Card card = (Card) get(getMode(), uuid, "SELECT * FROM CARD WHERE UUID=?", Card.class);
		
		if (null != card) {
			((Component) card).setChildren(super.getComponentRepository().list(card.getUuid()));
		}
		
		return card;
		
	}


	public void edit(Card card) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE CARD SET TITLE=?, CONTENT=?, FILECARD=?, BUTTONTEXT=?, BUTTONURL=? WHERE UUID=?") 
							.bind(5, card.getUuid())
							.bind(0, card.getTitle())
							.bind(1, card.getContent())
							.bind(2, card.getFileCard())
							.bind(3, card.getButtonText())
							.bind(4, card.getButtonUrl())
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
				handle.execute("CREATE TABLE IF NOT EXISTS CARD (UUID VARCHAR, TITLE VARCHAR, CONTENT VARCHAR)");
			});
		}
	}
	
}
