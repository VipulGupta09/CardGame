package com.cardgame;

import java.util.List;
/**
 * @author Vipul.G
 * @since June 21, 2020
 *
 */

public interface GameService {
	public void addPlayer(List<String> listOfPlayerNames);
	public void removePlayer(List<String> listOfPlayerNames);
	public void shuffleDeck();
	public void printAllCardsInDeck();
	public void startGame(int number);
	public void printCardEachPlayerHolding();
	public void findWinner();
	public void finishGame();
}
