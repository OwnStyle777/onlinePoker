package com.example.onlinePoker.game;

import com.example.onlinePoker.players.Player;
import com.example.onlinePoker.table.Card;

import java.util.*;

public interface HandEvaluator {

   default String checkTheHighestCard(Card[] playerCards, Card[] tableCards){
        Card hightCard;
        Card secondCard;
        if(playerCards[0].getValue() < playerCards[1].getValue()){
            hightCard = playerCards[1];
            secondCard = playerCards[0];
        }else if(playerCards[0].getValue() > playerCards[1].getValue()){
            hightCard = playerCards[0];
            secondCard = playerCards[1];
        }else {
            Arrays.sort(tableCards);
            hightCard = tableCards[tableCards.length -1];
            secondCard = tableCards[tableCards.length -2];
        }
        boolean highCardIsBigger = false;
        boolean secondCardIsBigger = false;
        for (Card card: tableCards){
            if (hightCard.getValue() > card.getValue()){
                highCardIsBigger = true;

            }

                if (secondCard.getValue() > card.getValue()) {
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
    default void sortTheCardsDescending(List <Card> cards){
        cards.sort((card1, card2) -> Integer.compare(card2.getValue(), card1.getValue()));
    }
     default String checkHighestPair(Card[] playerCards, Card [] tableCards){
        List <Card> allCards = concatenateArraysToList(playerCards, tableCards);

        Card previousCard = null;
        boolean isPair = false;
         for(int i = allCards.size() - 1; i >= 0; i--) {
            if (previousCard != null && allCards.get(i).getValue() == previousCard.getValue()){
                isPair = true;
                break;

            }
            previousCard = allCards.get(i);
        }

    if (isPair){
    return "is pair of " + previousCard.toString();
       }

return "";
    }
    default List <Card> concatenateArraysToList(Card[] playerCards, Card[] tableCards){
        List <Card> allCards = new ArrayList<>();

        allCards.addAll(Arrays.asList(playerCards));
        allCards.addAll(Arrays.asList(tableCards));
        return allCards;
    }

    default    String checkHighest2pairs(Card[] playerCards, Card [] tableCards){
       List <Card> allCards = concatenateArraysToList(playerCards, tableCards);

     sortTheCards(allCards);
      Card previous = null;
      boolean onePair = false;
      boolean secondPair = false;
       Card highestPair = null;
       Card lowerPair = null;

     for(int i = allCards.size() - 1; i >= 0; i--) {

         Card currentCard = allCards.get(i);

             if (previous != null && previous.getValue() == currentCard.getValue()) {

                 if(!onePair){
                 onePair = true;
                 highestPair = currentCard;
                 }
                 else if (currentCard.getValue() != highestPair.getValue()){
                     secondPair = true;
                     lowerPair = currentCard;
                     break;
                 }
             }

         previous = allCards.get(i);
     }

     if(onePair && secondPair){

         return "Pair of " + highestPair.toString() + " with " + lowerPair.toString();
     }
     return "";
    }
    default String check3ofKind(Card[] playerCards, Card [] tableCards){
        List <Card> allCards = concatenateArraysToList(playerCards, tableCards);
       return "";
    }
}
