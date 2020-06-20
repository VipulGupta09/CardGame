package com.cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * @author Vipul.G
 * @since June 21, 2020
 *
 */
public class Player {
	private String playerName;
	private List<Card> card;
	
	public Player() {
		card=new ArrayList<>();
	}
	
	public Player(String playerName) {
		this.playerName=playerName;
		this.card=new ArrayList<Card>();
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void setCard(Card card) {
		this.card.add(card);
		//Sorting the card according to their value
		Collections.sort(this.card, new Comparator<Card>() {

			@Override
			public int compare(Card o1, Card o2) {
				return o2.getFaceValue()-o1.getFaceValue();
			}
		});
	}

	public String getPlayerName() {
		return playerName;
	}
	public List<Card> getCard() {
		return card;
	}
	
	public String toString() {
		return playerName+" "+card.toString();
	}

}
