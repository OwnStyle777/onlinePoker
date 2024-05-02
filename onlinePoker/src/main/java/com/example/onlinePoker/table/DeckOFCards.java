package com.example.onlinePoker.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeckOFCards {
    private List<Card> cards ;

    public DeckOFCards(){
        initializeDeck();
    }

    private void initializeDeck(){
        cards = new ArrayList<>();
        cards.addAll(Arrays.asList(Card.values()));
    }

    public void shuffleCards(){
        Collections.shuffle(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

}
