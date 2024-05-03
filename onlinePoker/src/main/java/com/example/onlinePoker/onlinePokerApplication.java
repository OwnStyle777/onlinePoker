package com.example.onlinePoker;

import com.example.onlinePoker.game.Game;
import com.example.onlinePoker.players.Player;
import com.example.onlinePoker.table.DeckOFCards;
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

		Player player = new Player();
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		Player player5 = new Player();
		Player player6 = new Player();
		Player player7 = new Player();
		Player player8 = new Player();


		List<Player> playerList = new ArrayList<>();
		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);
		playerList.add(player4);
		playerList.add(player5);
		playerList.add(player6);
		playerList.add(player7);
		playerList.add(player8);
		playerList.add(player);



		Game game = new Game();
		game.setPlayers(playerList);

		game.dealTheCards(playerList);
		game.dealTheFlop();
		game.dealTheTurn();
		game.dealTheRiver();

		int playerNumber = 1;
		for(Player playerC: playerList){

			String[] cards = playerC.getCards();

			System.out.println("Player " + playerNumber + "Card1: " + cards[0] + " Card2: " + cards[1] );
			playerNumber ++;
		}


		Table
		System.out.println(ta);

	}

}
