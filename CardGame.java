package com.cardgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Vipul.G
 * @since June 21, 2020
 *
 */
public class CardGame {
	private static GameService gameService;

	static {
		gameService = GameServiceImpl.getInstance();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		boolean loopTeminate = true;
		System.out.println("NOTE: ACE is initialized as 1 and Jack, Queen, King cards as 11,12,13 ");
		int numberOfPlayers = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = 0;
		while (loopTeminate) {
			showChoices();
			try {
				input = Integer.parseInt(br.readLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Please Give Input in Integer Format...");
			}
			switch (input) {
			case 1:
				System.out.println("Enter the Number of Players :");
				List<String> nameOfPlayers = new ArrayList<String>();
				try {
					numberOfPlayers = Integer.parseInt(br.readLine());
				} catch (NumberFormatException e) {
					System.out.println("Enter the valid Number");
					break;
				}

				System.out.println("Enter the Name of Player");
				if (numberOfPlayers > 1 && numberOfPlayers < 53) {
					for (int i = 0; i < numberOfPlayers; i++) {
						try {
							nameOfPlayers.add(br.readLine().trim().toUpperCase());
						} catch (NumberFormatException e) {
							System.out.println("Enter the valid Name");
						}
					}
					gameService.addPlayer(nameOfPlayers);
					System.out.println(numberOfPlayers + " Players are added successfully");
				} else
					System.out.println("Please enter valid Number of Players...");
				break;
			case 2:
				System.out.println("Enter the Name of Players to remove in space seperated Format");
				List<String> nameOfPlayersToRemove = new ArrayList<String>();
				String str[] = br.readLine().split("\\s+");
				for (int i = 0; i < str.length; i++) {
					try {
						nameOfPlayersToRemove.add(str[i].trim().toUpperCase());
					} catch (NumberFormatException e) {
						System.out.println("Enter the valid Name");
					}
				}
				gameService.removePlayer(nameOfPlayersToRemove);

				break;
			case 3:
				gameService.shuffleDeck();
				System.out.println("Cards Shuffling Done!!");
				break;
			case 4:
				gameService.printAllCardsInDeck();
				break;
			case 5:
				System.out.println("Enter the number of cards to distrubute to each player:");
				System.out.println("Enter 1, for distributing single card to each player");
				System.out.println("Enter 2, for switching game to 2 card game for each player");
				System.out.println("Enter any number of your choice range between (52/" + numberOfPlayers
						+ ") for distributing among each player");
				gameService.startGame(Integer.parseInt(br.readLine().trim()));
				break;
			case 6:
				gameService.printCardEachPlayerHolding();
				break;
			case 7:
				gameService.findWinner();
				break;
			case 8:
				gameService.finishGame();
				System.out.println("Game is Completed and All cards are on the Deck!!!");
				break;
			case 9:
				loopTeminate = false;
				System.out.println("Thankyou for playing the Game, Hope you enjoy that..");
				break;
			default:
				System.out.println("Wrong Choice!!!");
				break;
			}
		}
	}

	public static void showChoices() {
		System.out.println();
		System.out.println("Choose any Options:");
		System.out.println("1.Add player(s) to the game.");
		System.out.println("2.Remove player(s) to the game.");
		System.out.println("3.Shuffle the deck.");
		System.out.println("4.Print all the cards present in the deck.");
		System.out.println("5.Start the game.");
		System.out.println("6.Print the card each player is holding.");
		System.out.println("7.Find the winner of the game.");
		System.out.println("8.Finish the game by returning all cards back to the deck.");
		System.out.println("9:Exit");
		System.out.println("------------------------------------------------------------");

	}

}
