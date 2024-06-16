package com.example.onlinePoker;

import com.example.onlinePoker.game.Game;
import com.example.onlinePoker.game.HandEvaluator;
import com.example.onlinePoker.players.Player;
import com.example.onlinePoker.api.table.Card;
import com.example.onlinePoker.api.table.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import java.util.List;

@SpringBootApplication
public class onlinePokerApplication   {

	private static final Logger logger = LoggerFactory.getLogger(onlinePokerApplication.class);



	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		SpringApplication.run(onlinePokerApplication.class, args);



		Game game = new Game();
		Table table = game.getTable();
		Player player = new Player();
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		Player player5 = new Player();
		Player player6 = new Player();
////		Player player7 = new Player();
////		Player player8 = new Player();


		List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
		player1.setName("Martin");
		playerList.add(player2);
		player2.setName("Peter");
		playerList.add(player3);
		player3.setName("Jozef");
		playerList.add(player4);
		player4.setName("Andrej");
		playerList.add(player5);
		player5.setName("Ondrej");
		playerList.add(player6);
		player6.setName("Kamil");
//		playerList.add(player7);
//		playerList.add(player8);
		playerList.add(player);
		player.setName("Adam");


		game.setPlayers(playerList);
		logger.info("Players Initialized");

		int smallBlind = 1;
		int rounds = 0;

		while(rounds < 15 ) {

			game.preparePlayersForNextRound(playerList);
			game.dealTheCards(playerList);
			logger.info("Game Started");
			 smallBlind = game.getBlinds(smallBlind,playerList);

			 int firstPlayer = smallBlind + 1;

			for (Player playerC : playerList) {

				Card[] cards = playerC.getCards();
				String role = "";
				if (playerC.isDealer()) {
					role =" DEALER";
				} else if (playerC.isBigBlind()) {
					role =" BIG BLIND";
				} else if (playerC.isSmallBlind()) {
					role =" SMALL BLIND";
				}else {
					role ="";
				}

				logger.info("Player:  "+ playerC.getName()  + " Card1: " + cards[0].toString() + " Card2: " + cards[1].toString() + role);


			}

			game.callBets(firstPlayer, true);

		HandEvaluator handEvaluator = new HandEvaluator() {
		};


			game.dealTheFlop();

			logger.info("Flop :" + (table.getFlop()));

			game.resetRoundStakes();
//			game.callBetsNextRounds(smallBlind, true);
			game.dealTheTurn();
			logger.info(" Turn :" + table.getTurn());
			game.resetRoundStakes();
//			game.callBetsNextRounds(smallBlind, true);
			game.dealTheRiver();
			logger.info(" River :" + table.getRiver());
			game.resetRoundStakes();
//			game.callBetsNextRounds(smallBlind, true);

			List<Card> allTable = new ArrayList<>(table.getFlop());

			allTable.add(table.getTurn());
			allTable.add(table.getRiver());
			logger.info("Table :" + (allTable));

			for(Player playerk: playerList){
				String combination = "";
				String[] methodsToCheck = {
						"checkTheHighestCard",
						"checkHighestPair",
						"checkHighest2pairs",
						"check3ofKind",
						"checkStraight",
						"checkTheFlush",
						"checkTheFullHouse",
						"checkFourOfKind",
						"checkTheStraightFlush",
						"checkTheRoyalFlush"
				};

				for (String methodName : methodsToCheck) {
					//first get class of instance , get specific method by name and argument types after all invoke method on instance with specific arguments
					String currentCombination = handEvaluator.getClass().getMethod(methodName, Card[].class, List.class)
							.invoke(handEvaluator, playerk.getCards(), allTable).toString();

					if (currentCombination.length() > 1) {
						combination = currentCombination;

					}
				}

				playerk.setCombination(combination);
			}
			System.out.println();
			for(Player playerC: playerList){
				logger.info(playerC.getName() + " combination: " + playerC.getCombination());
			}
			System.out.println();

		rounds ++;
		}
	}

}
