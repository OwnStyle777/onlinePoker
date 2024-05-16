package com.example.onlinePoker.game;

import com.example.onlinePoker.players.Player;
import com.example.onlinePoker.table.Card;

import java.util.*;

public interface HandEvaluator {

    default  String checkTheHighestCard(Card[] playerCards, Card [] tableCards){
        Card hightCard = null;
        Card secondCard = null;
        if(playerCards[0].getValue() < playerCards[1].getValue()){
            hightCard = playerCards[1];
            secondCard = playerCards[0];
        }else if(playerCards[0].getValue() > playerCards[1].getValue()){
            hightCard = playerCards[0];
            secondCard = playerCards[1];
        }
        boolean highCardIsBigger = false;
        boolean secondCardIsBigger = false;
        for (Card card: tableCards){
            if (hightCard.getValue() > card.getValue()){
                highCardIsBigger = true;

            }
            if (secondCard.getValue() > card.getValue()){
                secondCardIsBigger = true;
                break;
            }
        }
        if (highCardIsBigger && secondCardIsBigger){
            return "hight card " + hightCard.toString() +" with " + secondCard.toString();
        } else if (highCardIsBigger) {
            return "hight card " + hightCard.toString();
        }


        return "hight card";
    }

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

    default  String checkHighest2pairs(Card[] playerCards, Card [] tableCards){
        List <Card> allCards = new ArrayList<>();

        allCards.addAll(Arrays.asList(playerCards));
        allCards.addAll(Arrays.asList(tableCards));

     sortTheCards(allCards);
      Card previous = null;
      boolean onePair = false;
      boolean secondPair = false;
       Card highestPair = null;
       Card lowerPair = null;

     for(int i = allCards.size() - 1; i >= 0; i--) {

         Card currentCard = allCards.get(i);

         if (previous != null) {
             if (previous.getValue() == currentCard.getValue()) {
                 onePair = true;
                 highestPair = currentCard;

             }
         }


         if (highestPair != null) {
             if (currentCard.getValue() != highestPair.getValue() && currentCard.getValue() == previous.getValue()) {
                secondPair = true;
                lowerPair = currentCard;
                break;
             }
         }
         previous = allCards.get(i);
     }

     if(onePair && secondPair){

         return highestPair.toString() + "with " + lowerPair.toString();
     }
     return "";
    }
}
