package com.example.onlinePoker.game;

import com.example.onlinePoker.players.Player;
import com.example.onlinePoker.table.Card;
import com.example.onlinePoker.table.DeckOFCards;

import java.util.List;
import java.util.Random;

public class Game {
    private List<Player> players;
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
}
