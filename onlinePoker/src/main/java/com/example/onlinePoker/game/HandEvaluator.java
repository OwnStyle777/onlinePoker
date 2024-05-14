package com.example.onlinePoker.game;

import com.example.onlinePoker.players.Player;
import com.example.onlinePoker.table.Card;

import java.util.*;

public interface HandEvaluator {

    default void sortTheCards(List <Card> cards){
        cards.sort(Comparator.comparingInt(Card::getValue));
    }
     default String checkHighestPair(Card[] playerCards, Card [] tableCards){
        List <Card> allCards = new ArrayList<>();

        allCards.addAll(Arrays.asList(playerCards));
        allCards.addAll(Arrays.asList(tableCards));
        sortTheCards(allCards);


        Card previousCard = null;
        boolean isPair = false;

        for (Card card: allCards){
            if (previousCard != null && card.getValue() == previousCard.getValue()){
                isPair = true;
                break;

            }
            previousCard = card;
        }

    if (isPair){
    return "is pair of " + previousCard.toString();
       }

return null;
    }
}
