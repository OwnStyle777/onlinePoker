package com.example.onlinePoker;

import com.example.onlinePoker.game.Game;
import com.example.onlinePoker.players.Player;
import com.example.onlinePoker.table.Card;
import com.example.onlinePoker.table.Table;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class onlinePokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(onlinePokerApplication.class, args);

		Game game = new Game();
		Table table = game.getTable();
		Player player = new Player();
		Player player1 = new Player();
		Player player2 = new Player();
//		Player player3 = new Player();
//		Player player4 = new Player();
//		Player player5 = new Player();
//		Player player6 = new Player();
//		Player player7 = new Player();
//		Player player8 = new Player();


		List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
		player1.setName("Martin");
		playerList.add(player2);
		player2.setName("Peter");
//		playerList.add(player3);
//		player3.setName("Jozef");
//		playerList.add(player4);
//		player4.setName("Andrej");
//		playerList.add(player5);
//		playerList.add(player6);
//		playerList.add(player7);
//		playerList.add(player8);
		playerList.add(player);
		player.setName("Adam");


		game.setPlayers(playerList);




		int smallBlind = 1;
		int rounds = 0;

		while(rounds < 2 ) {

			game.preparePlayersForNextRound(playerList);
			game.dealTheCards(playerList);
			 smallBlind = game.getBlinds(smallBlind);

			 int firstPlayer = smallBlind + 1;

			for (Player playerC : playerList) {

				Card[] cards = playerC.getCards();

				System.out.println("Player:  "+ playerC.getName()  + "\n Card1: " + cards[0].toString() + "\n Card2: " + cards[1].toString());
				if (playerC.isDealer()) {
					System.out.println("DEALER");
				} else if (playerC.isBigBlind()) {
					System.out.println("BIG BLIND");
				} else if (playerC.isSmallBlind()) {
					System.out.println("SMALL BLIND");
				}

			}

			game.callBets(firstPlayer, true);
			game.dealTheFlop();
			System.out.println("Flop :" + Arrays.toString(table.getFlop()));
			game.resetRoundStakes();
			game.callBetsNextRounds(smallBlind, true);
			game.dealTheTurn();
			System.out.println(" Turn :" + table.getTurn());
			game.resetRoundStakes();
			game.callBetsNextRounds(smallBlind, true);
			game.dealTheRiver();
			System.out.println(" River :" + table.getRiver());
			game.resetRoundStakes();
			game.callBetsNextRounds(smallBlind, true);




			System.out.println("Flop :" + Arrays.toString(table.getFlop()) + " Turn :" + table.getTurn() + " River :" + table.getRiver());

		rounds ++;
		}
	}

}
