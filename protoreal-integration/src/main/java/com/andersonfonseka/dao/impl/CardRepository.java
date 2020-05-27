package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.andersonfonseka.protoreal.components.Card;

public class CardRepository implements Repository<Card> {
	
	private static Connection connection = null;
	
	public CardRepository() {}
	
	public void add(Card card) {
		
		String INSERT_PAGE = "INSERT INTO CARD (UUID, TITLE, SUBTITLE) VALUES (?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(1, card.getUuid());
			pstmt.setString(2, card.getTitle());
			pstmt.setString(3, card.getSubtitle());
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	public void remove(String uuid) {
	//	this.repository.remove(uuid);
	}
	
	public Card get(String uuid) {
		
		Card card = null;
		
		String SELECT_ALL = "SELECT * FROM CARD WHERE UUID = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				card = new Card();
				
				card.setUuid(resultSet.getString(1));
				card.setTitle(resultSet.getString(2));
				card.setSubtitle(resultSet.getString(3));
			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return card;
	}

	@Override
	public void edit(Card component) {
		// TODO Auto-generated method stub
		
	}
	
}
