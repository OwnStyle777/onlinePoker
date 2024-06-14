package com.example.onlinePoker.game;

import com.example.onlinePoker.players.Player;
import com.example.onlinePoker.table.Card;
import com.example.onlinePoker.table.Table;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private List<Player> players;
    private Table table = new Table();
    private List<Card> cards;
    private int smallBlind = 50;
    private int bigBlind = 100;
    private int stake = bigBlind;




    public void organizeActivePlayers(List<Player> players){
        int firstPlayer = 0;
        for(Player player: players){
            if(player.isBigBlind()){
                firstPlayer =  players.indexOf(player) + 1;
            }
        }

        if (firstPlayer == players.size()){
            firstPlayer = 0;
        }else if(firstPlayer > players.size()){
            firstPlayer = 1;
        }
         Collections.rotate(players, players.size() - firstPlayer);


    }
    public void organizeActivePlayers(List<Player> players, int firstPlayer){

        if (firstPlayer == players.size()){
            firstPlayer = 0;
        }else if(firstPlayer > players.size()){
            firstPlayer = 1;
        }
        Collections.rotate(players, players.size() - firstPlayer);


    }

    public void preparePlayersForNextRound(List<Player> players){
        for (Player player: players){
            player.setFold(false);
            player.setRoundStake(0);
        }
    }

    public void resetRoundStakes(){
        for (Player player: players){
            player.setRoundStake(0);
        }
        setStake(0);
    }


    //special method for firstRound before the flop
    public void callBets(int firstPlayer, boolean firstIteration) {
        if(firstIteration){
        setStake(100);}
        if(firstPlayer >= 0){
            organizeActivePlayers(players, firstPlayer);
        }else {
            organizeActivePlayers(players);
        }
        Scanner scanner = new Scanner(System.in);


        //boolean for stop running iteration
        boolean continueIteration = true;

        for(Player player: players) {
            if (player != null) {
                System.out.println("roundStake: " + player.getRoundStake());
                System.out.println("bigBlind: " + player.isBigBlind());
                System.out.println("isFold: " + player.isFold());
                System.out.println("player: " + player.getName());
                System.out.println("stake: " + stake);

                if (continueIteration) {

                    //big blind can do some action ,also when stake is equal to playerRoundStake ,but only in first iteration
                    if (player.getRoundStake() == stake && player.isBigBlind() && firstIteration && !player.isFold()) {
                        firstIteration = false;
                        System.out.println(player.getName() + " fold  check raise");
                        String action = scanner.nextLine();
                        switch (action) {
                            case "check" -> {
                            }
                            case "raise" -> {
                                System.out.println("amount of raise");
                                int raise = scanner.nextInt();
                                setStake(raise(player, raise));
                                //recursive calling after someone raise move to the next player
                                callBets(players.indexOf(player) + 1, false);
                                continueIteration = false;
                            }

                        }

                    } else if (player.getRoundStake() == stake) {
                        System.out.println();
                    } else if (!player.isFold()) {
                        System.out.println(player.getName() + " fold raise call");
                        String action = scanner.nextLine();
                        switch (action) {
                            case "fold" -> fold(player);
                            case "raise" -> {
                                System.out.println("amount of raise");
                                int raise = scanner.nextInt();
                                setStake(raise(player, raise));
                                callBets(players.indexOf(player) + 1, false);
                                continueIteration = false;
                            }
                            case "call" -> call(player);
                        }
                    }
                }
            }

        }

    }

    public void callBetsNextRounds(int firstPlayer, boolean firstIteration){

        if(firstPlayer >= 0){
            organizeActivePlayers(players, firstPlayer);
        }else {
            organizeActivePlayers(players);
        }
        Scanner scanner = new Scanner(System.in);


        //boolean for stop running iteration
        boolean continueIteration = true;
        for(Player player: players) {
            if (continueIteration) {
                System.out.println("roundStake: " + player.getRoundStake());
                System.out.println("bigBlind: " + player.isBigBlind());
                System.out.println("isFold: " + player.isFold());
                System.out.println("player: " + player.getName());
                System.out.println("stake: " + stake);


                if (player.getRoundStake() == stake && !player.isFold() && firstIteration) {

                    System.out.println(player.getName() + " check raise");
                    String action = scanner.nextLine();
                    switch (action) {
                        case "check" -> {
                        }
                        case "raise" -> {
                            System.out.println("amount of raise");
                            int raise = scanner.nextInt();
                            setStake(raise(player, raise));
                            //recursive calling after someone raise move to the next player
                            callBetsNextRounds(players.indexOf(player) + 1, false);
                            continueIteration = false;
                        }
                    }
                } else if (!player.isFold() && player.getRoundStake() < stake) {
                    System.out.println(player.getName() + " fold raise call");
                    String action = scanner.nextLine();
                    switch (action) {
                        case "fold" -> fold(player);
                        case "raise" -> {
                            System.out.println("amount of raise");
                            int raise = scanner.nextInt();
                            setStake(raise(player, raise));
                            callBetsNextRounds(players.indexOf(player) + 1, false);
                            continueIteration = false;
                        }
                        case "call" -> call(player);
                    }
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


    public void fold(Player player) {
        player.setFold(true);
        System.out.println(player.getName() + "fold");
    }

    public List<Player> returnNonNullPlayers(List<Player> players){
       return players.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    public int getBlinds(int smallBlindIndex, List<Player> players){
        List <Player> nonNullPlayers = returnNonNullPlayers(players);
        int dealerIndex ;
        int bigBlindIndex ;
        deactivateBlinds();

        Player dealer = null;
        Player smallBlindPl;
        Player bigBlindPl;

        if (smallBlindIndex >= nonNullPlayers.size()) {
            smallBlindIndex = 0;
        }
        if (smallBlindIndex == 0) {
            dealerIndex = nonNullPlayers.size() - 1;
        } else {
            dealerIndex = smallBlindIndex - 1;
        }
        if (smallBlindIndex == nonNullPlayers.size() - 1) {
            bigBlindIndex = 0;
        } else {
            bigBlindIndex = smallBlindIndex + 1;
        }

     if(nonNullPlayers.size() > 2 ) {
         dealer = nonNullPlayers.get(dealerIndex);
     }
            smallBlindPl  = nonNullPlayers.get(smallBlindIndex);

            bigBlindPl = nonNullPlayers.get(bigBlindIndex);


            for(Player player: players){
                if(player != null && nonNullPlayers.contains(player)){
                    if(player.equals(smallBlindPl)) { // Kontrola pre small blind
                        player.setSmallBlind(true);
                        player.setRoundStake(smallBlind);
                        player.setTotalChips(player.getTotalChips() - smallBlind);
                        table.setPot(table.getPot() + smallBlind);
                    }
                    if(player.equals(bigBlindPl)) { // Kontrola pre big blind
                        player.setBigBlind(true);
                        player.setRoundStake(bigBlind);
                        player.setTotalChips(player.getTotalChips() - bigBlind);
                        table.setPot(table.getPot() + bigBlind);
                    }
                    if(player.equals(dealer)) { // Kontrola pre dealera
                        player.setDealer(true);
                    }
                }
            }


     return smallBlindIndex + 1 ;
    }
    public int getRandomCardIndex (List<Card> cards){
        Random random = new Random();

        return random.nextInt(cards.size());

    }

    public void deactivateBlinds (){
        for(Player player: players){
            if(player != null) {
                player.setBigBlind(false);
                player.setSmallBlind(false);
                player.setDealer(false);
            }
        }

    }

    public int checkPlayersNumber(List<Player> listOfPlayers){
        int number = 0;
        for(Player player: listOfPlayers){
            if(player != null){
                number ++;
            }
        }
        return number;
    }



    public void dealTheCards(List<Player> players) {
        if (players.size() > 1) {
            initializeDeck();
            shuffleCards();


            for (Player player : players) {
                if(player != null) {
                    int card1Index = getRandomCardIndex(cards);
                    Card card1 = cards.get(card1Index);
                    cards.remove(card1Index);
                    int card2Index = getRandomCardIndex(cards);
                    Card card2 = cards.get(card2Index);
                    cards.remove(card2Index);


                    Card[] cards = {card1, card2};

                    player.setCards(cards);
                }
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

        List<Card> flop = List.of(card1, card2, card3);
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

        table.setTurn(card4);
    }

    public void dealTheRiver(){
        int card5Index = getRandomCardIndex(cards);
        Card card5 = cards.get(card5Index);
        cards.remove(card5Index);

        table.setRiver(card5);
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



}
