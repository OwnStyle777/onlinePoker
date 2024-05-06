package com.example.onlinePoker.game;

import com.example.onlinePoker.players.Player;
import com.example.onlinePoker.table.Card;
import com.example.onlinePoker.table.Table;

import java.util.*;

public class Game {
    private List<Player> players;
    private List<Player> activePlayers;
    private Table table = new Table();
    private List<Card> cards;
    private int smallBlind = 50;
    private int bigBlind = 100;
    private int stake = bigBlind;




    public void organizeActivePlayers(List<Player> players, int firstPlayer){
        activePlayers = players;
         Collections.rotate(activePlayers, activePlayers.size() - firstPlayer);

    }


    //special method for firstRound before the flop
    public void callBets(int firstPlayer, boolean firstIteration) {
        organizeActivePlayers(players, firstPlayer);
        Scanner scanner = new Scanner(System.in);
        //iterator to allow safety remove or add elements during iteration
        Iterator<Player> iterator = activePlayers.iterator();

        //boolean for stop running iteration
        boolean continueIteration = true;


        while (iterator.hasNext() && continueIteration ) {
            Player player = iterator.next();
            //big blind can do some action ,also when stake is equal to playerRoundStake ,but only in first iteration
            if (player.getRoundStake() == stake && player.isBigBlind() && firstIteration) {
                firstIteration = false;
                System.out.println( player.getName() + " fold  check raise");
                String action = scanner.nextLine();
                switch (action) {
                    case "check" -> {}
                    case "raise" -> {
                        System.out.println("amount of raise");
                        int raise = scanner.nextInt();
                        raise(player, raise);
                        //recursive calling after someone raise move to the next player
                        callBets(activePlayers.indexOf(player) + 1, false);
                        continueIteration = false;
                    }

                }

            } else if(player.getRoundStake() == stake) {
                System.out.println();
            } else {
                System.out.println(player.getName() + " fold raise call");
                String action = scanner.nextLine();
                switch (action) {
                    case "fold" -> fold(player, iterator);
                    case "raise" -> {
                        System.out.println("amount of raise");
                        int raise = scanner.nextInt();
                        raise(player, raise);
                        callBets(activePlayers.indexOf(player) + 1 ,false);
                        continueIteration = false;
                    }
                    case "call" -> call(player);
                }
            }
        }
    }

    public void callBetsNextRounds(int firstPlayer){
        organizeActivePlayers(players, firstPlayer);
        Scanner scanner = new Scanner(System.in);

        Iterator<Player> iterator = activePlayers.iterator();

        //boolean for stop running iteration
        boolean continueIteration = true;

        while (iterator.hasNext() && continueIteration ) {
            Player player = iterator.next();

            if (player.getRoundStake() == stake && player.isBigBlind() ) {

                System.out.println( player.getName() + " fold  check raise");
                String action = scanner.nextLine();
                switch (action) {
                    case "check" -> {}
                    case "raise" -> {
                        System.out.println("amount of raise");
                        int raise = scanner.nextInt();
                        raise(player, raise);
                        //recursive calling after someone raise move to the next player
                        callBets(activePlayers.indexOf(player) + 1, false);
                        continueIteration = false;
                    }

                }

            } else if(player.getRoundStake() == stake) {
                System.out.println();
            } else {
                System.out.println(player.getName() + " fold raise call");
                String action = scanner.nextLine();
                switch (action) {
                    case "fold" -> fold(player, iterator);
                    case "raise" -> {
                        System.out.println("amount of raise");
                        int raise = scanner.nextInt();
                        raise(player, raise);
                        callBets(activePlayers.indexOf(player) + 1 ,false);
                        continueIteration = false;
                    }
                    case "call" -> call(player);
                }
            }
        }


    }



    public int raise (Player player, int raise){

        int toCall = 0;
        if(player.getRoundStake() < stake){
            toCall = stake - player.getRoundStake();
        }
        stake  += raise;
        if(player.isSmallBlind())
        player.setTotalChips(player.getTotalChips() - (raise + toCall));
        player.setRoundStake(player.getRoundStake() + raise + toCall);
        table.setPot(table.getPot() + raise + toCall);

        System.out.println(player.getName() + " raises " + raise);
    return  stake;
    }
    public void call(Player player){
        int call = stake - player.getRoundStake();
        System.out.println( player.getName() + " calls " + call);
        player.setTotalChips(player.getTotalChips() - call);
        player.setRoundStake(player.getRoundStake() + call);
        System.out.println(player.getTotalChips());
        table.setPot(table.getPot() + call);
    }


    public void fold(Player player, Iterator<Player> iterator) {
        iterator.remove();
        System.out.println(player.getName() + "fold");
    }


    public int getBlinds(int smallBlindIndex){
        int dealerIndex ;
        int bigBlindIndex ;
        deactivateBlinds();
        if (smallBlindIndex >= players.size()) {
            smallBlindIndex = 0;
        }
        if (smallBlindIndex == 0) {
            dealerIndex = players.size() - 1;
        } else {
            dealerIndex = smallBlindIndex - 1;
        }
        if (smallBlindIndex == players.size() - 1) {
            bigBlindIndex = 0;
        } else {
            bigBlindIndex = smallBlindIndex + 1;
        }
        if(players.size() == 2){
            players.get(smallBlindIndex).setSmallBlind(true);
            players.get(bigBlindIndex).setBigBlind(true);


        }else {
            Player dealer = players.get(dealerIndex);
            dealer.setDealer(true);

            Player smallBlindPl  = players.get(smallBlindIndex);
            smallBlindPl.setSmallBlind(true);
            smallBlindPl.setRoundStake(smallBlind);
            smallBlindPl.setTotalChips(smallBlindPl.getTotalChips() - smallBlind);
            table.setPot(table.getPot() + smallBlind);


            Player bigBlindPl = players.get(bigBlindIndex);
            bigBlindPl.setBigBlind(true);
            bigBlindPl.setRoundStake(bigBlind);
            bigBlindPl.setTotalChips(bigBlindPl.getTotalChips() - bigBlind);
            table.setPot(table.getPot() + bigBlind);

        }

     return smallBlindIndex + 1 ;
    }
    public int getRandomCardIndex (List<Card> cards){
        Random random = new Random();

        return random.nextInt(cards.size());

    }

    public void deactivateBlinds (){
        for(Player player: players){
            player.setBigBlind(false);
            player.setSmallBlind(false);
            player.setDealer(false);
        }

    }



    public void dealTheCards(List<Player> players) {
        if (players.size() > 1) {
            initializeDeck();
            shuffleCards();


            for (Player player : players) {
                int card1Index = getRandomCardIndex(cards);
                Card card1 = cards.get(card1Index);
                cards.remove(card1Index);
                int card2Index = getRandomCardIndex(cards);
                Card card2 = cards.get(card2Index);
                cards.remove(card2Index);


                String[] cards = {card1.toString(), card2.toString()};

                player.setCards(cards);
            }
        }

    }

    public void dealTheFlop(){
        int card1Index = getRandomCardIndex(cards);
        Card card1 = cards.get(card1Index);
        cards.remove(card1Index);

        int card2Index = getRandomCardIndex(cards);
        Card card2 = cards.get(card2Index);
        cards.remove(card2Index);

        int card3Index = getRandomCardIndex(cards);
        Card card3 = cards.get(card3Index);
        cards.remove(card3Index);

        String[] flop = {card1.toString(), card2.toString(), card3.toString()};
        table.setFlop(flop);
    }
    public void shuffleCards(){
        Collections.shuffle(cards);
    }
    public void initializeDeck(){
        cards = new ArrayList<>();
        cards.addAll(Arrays.asList(Card.values()));
    }

    public void dealTheTurn(){
        int card4Index = getRandomCardIndex(cards);
        Card card4 = cards.get(card4Index);
        cards.remove(card4Index);

        table.setTurn(card4.toString());
    }

    public void dealTheRiver(){
        int card5Index = getRandomCardIndex(cards);
        Card card5 = cards.get(card5Index);
        cards.remove(card5Index);

        table.setRiver(card5.toString());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
    public int getSmallBlind() {
        return smallBlind;
    }

    public void setSmallBlind(int smallBlind) {
        this.smallBlind = smallBlind;
    }

    public int getBigBlind() {
        return bigBlind;
    }

    public void setBigBlind(int bigBlind) {
        this.bigBlind = bigBlind;
    }
    public int getStake() {
        return stake;
    }

    public void setStake(int stake) {
        this.stake = stake;
    }
    public List<Player> getActivePlayers() {
        return activePlayers;
    }

    public void setActivePlayers(List<Player> activePlayers) {
        this.activePlayers = activePlayers;
    }



}
