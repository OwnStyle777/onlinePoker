package com.example.onlinePoker.game;

import com.example.onlinePoker.players.Player;
import com.example.onlinePoker.table.Card;
import com.example.onlinePoker.table.DeckOFCards;
import com.example.onlinePoker.table.Table;

import javax.xml.catalog.Catalog;
import java.util.List;
import java.util.Random;

public class Game {
    private List<Player> players;
    private Table table = new Table();
    private final DeckOFCards deckOFCards = new DeckOFCards();
    private List<Card> cards = deckOFCards.getCards();


    public int getRandomCardIndex (List<Card> cards){
        Random random = new Random();

        return random.nextInt(cards.size());

    }


    public void dealTheCards(List<Player> players) {
        if (players.size() > 1) {
            deckOFCards.shuffleCards();
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

    public DeckOFCards getDeckOFCards() {
        return deckOFCards;
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

}
