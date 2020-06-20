package com.cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
/**
 * @author Vipul.G
 * @since June 21, 2020
 *
 */
public class GameServiceImpl implements GameService {
	private static GameService gameService;
	private static List<Player> listOfPlayers;
	
	public static Stack<Card> cards;
	
	private static Map<String, Integer> valueOfSuits=new HashMap<String,Integer>();
	
	static {
		gameService=new GameServiceImpl();
		listOfPlayers=new ArrayList<Player>();
		cards=new Stack<Card>();
		
		for(int faceValue=1;faceValue<=13;faceValue++) {
			for(SUIT suit:SUIT.values()) {
				cards.add(new Card(suit, faceValue));
			}
		}
	}
	static {
		valueOfSuits.put(SUIT.SPADE.toString(), 4);
		valueOfSuits.put(SUIT.HEART.toString(), 3);
		valueOfSuits.put(SUIT.DIAMOND.toString(),2);
		valueOfSuits.put(SUIT.CLUB.toString(), 1);
	}
	
	public static GameService getInstance() {
		return gameService;
	}
	
	@Override
	public void addPlayer(List<String> listOfPlayerName) {
		for(int i=0;i<listOfPlayerName.size();i++) {
			String name=listOfPlayerName.get(i);
			listOfPlayers.add(new Player(name));
		}	
	}

	@Override
	public void removePlayer(List<String> nameOfPlayersToRemove) {
		int sizeOfList=nameOfPlayersToRemove.size();
		if(sizeOfList>0 && sizeOfList <listOfPlayers.size()) {
			for(int i=0;i<sizeOfList;i++) {
				for(int j=0;j<listOfPlayers.size();j++) {
					if(listOfPlayers.get(j).getPlayerName().equalsIgnoreCase(nameOfPlayersToRemove.get(i))) {
						List<Card> list=listOfPlayers.get(j).getCard();
						int count=list.size()-1;
						while(!list.isEmpty()) {
							//adding the cards of Players back to the deck we are removing
							cards.add(list.remove(count));
							count--;
						}
						//removing the player from the list
						listOfPlayers.remove(j);
						break;
					}	
				}
			}
			System.out.println("Players are removed successfully");
		}else {
			System.out.println("Please enter Valid number of Players to remove");
		}
	}

	@Override
	public void shuffleDeck() {
		Collections.shuffle(cards);
	}

	@Override
	public void printAllCardsInDeck() {
		for(Card card:cards)
			System.out.print(card+ ", ");
		System.out.println();	
	}

	@Override
	public void startGame(int num) {
		//check for total cards will be in range like(total number of cards/number of player)
		int maxCardToDistribute= cards.size()/listOfPlayers.size();
		if(num<=maxCardToDistribute) {
			for(int i=0;i<num;i++) {
				for(int j=0;j<listOfPlayers.size();j++) {
					Player player=listOfPlayers.get(j);
					player.setCard(cards.pop());
				}
			}
			System.out.println("Cards are distributed..");
		}else {
			System.out.println("Please enter the valid count to distribute");
		}
		
	}

	@Override
	public void printCardEachPlayerHolding() {
		for(int i=0;i<listOfPlayers.size();i++) {
			System.out.println(listOfPlayers.get(i).getPlayerName());
			for(int j=0;j<listOfPlayers.get(i).getCard().size();j++) {
				System.out.println(listOfPlayers.get(i).getCard().get(j));
			}
			System.out.println();
		}	
	}

	@Override
	public void findWinner() {
		Player winner=new Player();
		if(listOfPlayers.size()>0)
		winner=listOfPlayers.get(0);
		for(int i=1;i<listOfPlayers.size();i++) {
			//Checking he value of the card
			if(listOfPlayers.get(i).getCard().get(0).getFaceValue()>winner.getCard().get(0).getFaceValue()) {
				winner=listOfPlayers.get(i);	
			}//checking the value of suit
			else if(listOfPlayers.get(i).getCard().get(0).getFaceValue()==winner.getCard().get(0).getFaceValue()) {
				String suitP1=listOfPlayers.get(i).getCard().get(0).getSuit().toString();
				String suitP2=winner.getCard().get(0).getSuit().toString();
				if(valueOfSuits.get(suitP1)>valueOfSuits.get(suitP2)) {
					winner=listOfPlayers.get(i);
				}
			}
		}
		System.out.println(winner.getPlayerName()+" "+winner.getCard());
	}
	@Override
	public void finishGame() {
		for(int i=0;i<listOfPlayers.size();i++) {
			//getting the cards from each player and putting back to deck.
			List<Card> listOfCards=listOfPlayers.get(i).getCard();
			for(int j=0;j<listOfCards.size();j++) {
				cards.add(listOfCards.get(j));
			}
		}
		listOfPlayers=new ArrayList<>();
	}
}
