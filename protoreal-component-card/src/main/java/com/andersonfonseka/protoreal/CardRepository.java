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
		return (Card) get(uuid, "SELECT * FROM CARD WHERE UUID=?", Card.class);
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
		remove(component.getUuid(), "DELETE FROM CARD WHERE UUID=?");
	}

	public List<IComponent> list(String uuid) {return null;}
	
}