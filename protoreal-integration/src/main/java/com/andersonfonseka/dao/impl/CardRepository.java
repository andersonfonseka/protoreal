package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Card;

class CardRepository implements Repository<Card> {
	
	private static Connection connection = null;
	
	public CardRepository() {}
	
	public void add(Card card) {
		
		String INSERT_PAGE = "INSERT INTO CARD (UUID, TITLE, SUBTITLE, CONTENT) VALUES (?,?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(1, card.getUuid());
			pstmt.setString(2, card.getTitle());
			pstmt.setString(3, card.getSubtitle());
			pstmt.setString(4, card.getContent());
			
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
				card.setContent(resultSet.getString(4));
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
	public void edit(Card card) {
		
		String INSERT_PAGE = "UPDATE CARD SET TITLE=?, SUBTITLE=?, CONTENT=? WHERE UUID=?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(4, card.getUuid());
			
			pstmt.setString(1, card.getTitle());
			pstmt.setString(2, card.getSubtitle());
			pstmt.setString(3, card.getContent());
			
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

	@Override
	public void remove(Card component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Card> list(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
