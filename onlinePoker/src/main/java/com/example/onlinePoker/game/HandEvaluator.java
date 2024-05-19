package com.example.onlinePoker.game;

import com.example.onlinePoker.players.Player;
import com.example.onlinePoker.table.Card;

import java.util.*;
import java.util.stream.Collectors;

public interface HandEvaluator {

    default String checkTheHighestCard(Card[] playerCards, List<Card> tableCards) {
        Card hightCard;
        Card secondCard;
        if (playerCards[0].getValue() < playerCards[1].getValue()) {
            hightCard = playerCards[1];
            secondCard = playerCards[0];
        } else if (playerCards[0].getValue() > playerCards[1].getValue()) {
            hightCard = playerCards[0];
            secondCard = playerCards[1];
        } else {
            List<Card> cards = new ArrayList<>(tableCards);
            sortTheCards(cards);
            hightCard = tableCards.get(tableCards.size() - 1);
            secondCard = tableCards.get(tableCards.size() - 2);
        }
        boolean highCardIsBigger = false;
        boolean secondCardIsBigger = false;
        for (Card card : tableCards) {
            if (hightCard.getValue() > card.getValue()) {
                highCardIsBigger = true;

            }

            if (secondCard.getValue() > card.getValue()) {
                secondCardIsBigger = true;
                break;
            }
        }
        if (highCardIsBigger && secondCardIsBigger) {
            return "hight card " + hightCard.toString() + " with " + secondCard.toString();
        } else if (highCardIsBigger) {
            return "hight card " + hightCard.toString();
        }


        return "hight card";
    }

    default void sortTheCards(List<Card> cards) {
        cards.sort(Comparator.comparingInt(Card::getValue));
    }

    default void sortTheCardsDescending(List<Card> cards) {
        cards.sort((card1, card2) -> Integer.compare(card2.getValue(), card1.getValue()));
    }

    default String checkHighestPair(Card[] playerCards, List<Card> tableCards) {

        List<Card> allCards = concatenateArraysToList(playerCards, tableCards);
        sortTheCardsDescending(allCards);

        Card previousCard = null;
        boolean isPair = false;

        for (int i = allCards.size() - 1; i >= 0; i--) {

            Card currentCard = allCards.get(i);

            if (previousCard != null && currentCard.getValue() == previousCard.getValue()) {
                isPair = true;
                break;

            }
            previousCard = currentCard;
        }

        if (isPair) {
            return "is pair of " + previousCard.toString();
        }

        return "";
    }

    default List<Card> concatenateArraysToList(Card[] playerCards, List<Card> tableCards) {
        List<Card> allCards = new ArrayList<>();

        allCards.addAll(Arrays.asList(playerCards));
        allCards.addAll(tableCards);
        return allCards;
    }

    default String checkHighest2pairs(Card[] playerCards, List<Card> tableCards) {
        List<Card> allCards = concatenateArraysToList(playerCards, tableCards);

        sortTheCards(allCards);
        Card previous = null;
        boolean onePair = false;
        boolean secondPair = false;
        Card highestPair = null;
        Card lowerPair = null;

        for (int i = allCards.size() - 1; i >= 0; i--) {

            Card currentCard = allCards.get(i);

            if (previous != null && previous.getValue() == currentCard.getValue()) {

                if (!onePair) {
                    onePair = true;
                    highestPair = currentCard;
                } else if (currentCard.getValue() != highestPair.getValue()) {
                    secondPair = true;
                    lowerPair = currentCard;
                    break;
                }
            }

            previous = allCards.get(i);
        }

        if (onePair && secondPair) {

            return "Pair of " + highestPair.toString() + " with " + lowerPair.toString();
        }
        return "";
    }

    default String check3ofKind(Card[] playerCards, List<Card> tableCards) {
        List<Card> allCards = concatenateArraysToList(playerCards, tableCards);
        sortTheCardsDescending(allCards);

        Card previousCard = null;
        Card prePreviousCard = null;
        boolean threeOfKind = false;

        for (Card card : allCards) {

            if (previousCard != null && previousCard.getValue() == card.getValue()) {

                if (prePreviousCard != null && prePreviousCard.getValue() == previousCard.getValue()) {
                    threeOfKind = true;
                    break;
                }
            }
            prePreviousCard = previousCard;
            previousCard = card;

        }

        if (threeOfKind) {
            return "Three of kind with " + previousCard;
        }
        return "";
    }

    default String checkStraight(Card[] playerCards, List<Card> tableCards) {
        List<Card> allCards = concatenateArraysToList(playerCards, tableCards);
        sortTheCardsDescending(allCards);

        int counter = 0;
        Card previousCard = null;
        boolean isStraight = false;

        for (Card card : allCards) {

            if (previousCard != null && previousCard.getValue() == card.getValue() + 1) {
                counter++;
                if (counter == 4) {
                    isStraight = true;
                    break;
                }
            } else if (previousCard != null && previousCard.getValue() == card.getValue()) {
            } else {
                counter = 0;
            }
            previousCard = card;
        }

        if (isStraight || checkTheLowestStraight(playerCards, tableCards)) {
            return " is Straight";
        }
        return "";
    }

    default String checkTheFlush(Card[] playerCards, List<Card> tableCards) {


        List<Card> allCards = concatenateArraysToList(playerCards, tableCards);
        sortTheCardsDescending(allCards);

        boolean flush = false;
        String typeOfFlush = "";

        //create map with 4 groups of suits with number of their occurrences
        Map<String, Long> valueCounts = allCards.stream()
                .collect(Collectors.groupingBy(Card::getSuit, Collectors.counting()));

        //iterate over the map and trying to find 5 occurrences
        for (Map.Entry<String, Long> entry : valueCounts.entrySet()) {
            String suit = entry.getKey();
            long occurrences = entry.getValue();

            if (occurrences >= 5) {
                flush = true;
                typeOfFlush = suit;
                break;
            }
        }

        if (flush) {

            //filter 5 highest cards and collect them to list
            String finalTypeOfFlush = typeOfFlush;  //lambda use effectively final variable
            List<Card> flushCards = allCards.stream()
                    .filter(card -> card.getSuit().equals(finalTypeOfFlush))
                    .limit(5)
                    .toList();
            //count power of flush cards
            int powerOfFlush = flushCards.stream()
                    .mapToInt(Card::getValue)
                    .sum();

            return "Flush with " + typeOfFlush + ", power: " + powerOfFlush;
        }


        return "";
    }

    default String checkTheFullHouse(Card[] playerCards, List<Card> tableCards) {
        String threeOfKind = check3ofKind(playerCards, tableCards);
        List<Card> allCards = concatenateArraysToList(playerCards, tableCards);

        if (threeOfKind.length() > 1) {
            //get the value of 3ofKind card
            String cardName = threeOfKind.substring(19);
            Card cardType = Card.valueOf(cardName);
            int cardValue = cardType.getValue();

            int counter = 0;
            //use the iterator to safely remove cards of trio from list during iteration
            Iterator<Card> iterator = allCards.iterator();
            while (iterator.hasNext()) {
                Card card = iterator.next();
                if (card.getValue() == cardValue && counter < 3) {
                    iterator.remove();
                    counter++;
                }
            }

            //collect cards to map, key is value of card and value is number of occurrences
            Map<Integer, Long> valueCounts = allCards.stream()
                    .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

            int highestPairValue = -1;
            //iterate over the map and find entries with 2 or more occurrences
            for (Map.Entry<Integer, Long> entry : valueCounts.entrySet()) {
                if (entry.getValue() >= 2) {
                    //find the highest pair by comparison of values
                    highestPairValue = Math.max(highestPairValue, entry.getKey());
                }
            }

            if (highestPairValue != -1) {
                return "Full House with three " + cardName + "s and two " + highestPairValue + "s";
            }

        }

        return "";

    }

    default String checkFourOfKind(Card[] playerCards, List<Card> tableCards) {
        List<Card> allCards = concatenateArraysToList(playerCards, tableCards);
        sortTheCardsDescending(allCards);

        Map<Integer, Long> valueCounts = allCards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        boolean fourOfKind = false;
        int valueOfCard = 0;
        for (Map.Entry<Integer, Long> entry : valueCounts.entrySet()) {
            valueOfCard = entry.getKey();
            long numberOfOccurrences = entry.getValue();
            if (numberOfOccurrences == 4) {
                fourOfKind = true;
                break;
            }
        }

        if (fourOfKind) {

            int finalValueOfCard = valueOfCard;//lambda use effectively final variable
            Card fourOfKindCard = allCards.stream()
                    .filter(card -> card.getValue() == finalValueOfCard)
                    .findFirst() // choose first card with finalValueOfCard
                    .orElse(null);
            return "Four of kind with " + fourOfKindCard;

        }
        return "";

    }

    default String checkTheStraightFlush(Card[] playerCards, List<Card> tableCards) {
        List<Card> allCards = concatenateArraysToList(playerCards, tableCards);
        sortTheCardsDescending(allCards);

        int counter = 0;
        Card previousCard = null;
        boolean isStraightFlush = false;

        for (Card card : allCards) {

            if (previousCard != null && previousCard.getValue() == card.getValue() + 1 && Objects.equals(previousCard.getSuit(), card.getSuit())) {
                counter++;
                if (counter == 4) {
                    isStraightFlush = true;
                    break;
                }
            } else if (previousCard != null && previousCard.getValue() == card.getValue()) {
            } else {
                counter = 0;
            }
            previousCard = card;
        }

        if (isStraightFlush || checkTheLowestStraightFlush(playerCards,tableCards)) {
            return "is Straight flush";
        }
        return "";

    }

    default boolean checkTheLowestStraight(Card[] playerCards, List<Card> tableCards){
        List <Card> allCards = concatenateArraysToList(playerCards, tableCards);

        boolean hasAce = false;
        boolean hasTwo = false;
        boolean hasThree = false;
        boolean hasFour = false;
        boolean hasFive = false;

        for (Card card: allCards){
            if(card.getValue() == 14){
                hasAce = true;
            }
            if(card.getValue() == 2){
                hasTwo = true;
            }
            if(card.getValue() == 3){
                hasThree = true;
            }
            if(card.getValue() == 4){
                hasFour = true;
            }
            if(card.getValue() == 5){
                hasFive = true;
            }

        }

        if(hasAce && hasTwo && hasThree && hasFour && hasFive){
            return  true;
        }
       return false;
    }
    default boolean checkTheLowestStraightFlush(Card[] playerCards, List<Card> tableCards) {
        List<Card> allCards = concatenateArraysToList(playerCards, tableCards);
        int clubs = 0;
        int hearts = 0;
        int diamonds = 0;
        int spades = 0;
        if (checkTheLowestStraight(playerCards, tableCards)) {

            for (Card card : allCards) {
                if (card.getValue() == 14 || card.getValue() == 2 || card.getValue() == 3 || card.getValue() == 4 || card.getValue() == 5) {
                    if (Objects.equals(card.getSuit(), "clubs")) {
                        clubs++;
                    }
                    if (Objects.equals(card.getSuit(), "hearts")) {
                        hearts++;
                    }
                    if (Objects.equals(card.getSuit(), "diamonds")) {
                        diamonds++;
                    }
                    if (Objects.equals(card.getSuit(), "spades")) {
                        spades++;
                    }
                }


            }
        }
        if(clubs == 5 || hearts == 5 || diamonds == 5 || spades == 5){
            return true;
        }
        return false;
    }

    default String checkTheRoyalFlush (Card[] playerCards, List<Card> tableCards){
        List <Card> allCards = concatenateArraysToList(playerCards, tableCards);

        sortTheCardsDescending(allCards);
        int clubs = 0;
        int hearts = 0;
        int diamonds = 0;
        int spades = 0;
        boolean hasAce = false;
        boolean hasKing = false;
        boolean hasQueen = false;
        boolean hasJack = false;
        boolean hasTen = false;
        String suit;
        int value;



        for(Card card: allCards){
            value = card.getValue();
            switch (value){
                case 14 -> hasAce = true;
                case 13 -> hasKing = true;
                case 12 -> hasQueen = true;
                case 11 -> hasJack = true;
                case 10 -> hasTen = true;
            }

            if (card.getValue() == 14 || card.getValue() == 13 || card.getValue() == 12 || card.getValue() == 11 || card.getValue() == 10) {
                suit = card.getSuit();
                switch (suit){
                    case "hearts" -> hearts++;
                    case "clubs" -> clubs++;
                    case "diamonds" -> diamonds++;
                    case "spades" -> spades++;
                }
            }
        }

        boolean isRoyalFlush = hasAce && hasKing && hasQueen && hasJack && hasTen;

        if(clubs == 5 || hearts == 5 || diamonds == 5 || spades == 5 && isRoyalFlush){
            return "is Royal flush";
        }
        return "";
    }



}
