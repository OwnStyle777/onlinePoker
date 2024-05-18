package com.example.onlinePoker.players;

import com.example.onlinePoker.game.HandEvaluator;
import com.example.onlinePoker.table.Card;



public class Player {

   private String name;
   private int totalChips = 20000;
   private Card[] cards;
   private String combination;
   private int roundStake;
   private boolean dealer = false;
   private boolean smallBlind = false;
   private boolean bigBlind = false;
   private  boolean isFold = false;
   private String  action;



    public boolean isSmallBlind() {
        return smallBlind;
    }

    public void setSmallBlind(boolean smallBlind) {
        this.smallBlind = smallBlind;
    }

    public boolean isBigBlind() {
        return bigBlind;
    }

    public void setBigBlind(boolean bigBlind) {
        this.bigBlind = bigBlind;
    }

    public boolean isDealer() {
        return dealer;
    }

    public void setDealer(boolean dealer) {
        this.dealer = dealer;
    }

    public int getRoundStake() {
        return roundStake;
    }

    public void setRoundStake(int roundStake) {
        this.roundStake = roundStake;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalChips() {
        return totalChips;
    }

    public void setTotalChips(int totalChips) {
        this.totalChips = totalChips;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isFold() {
        return isFold;
    }

    public void setFold(boolean fold) {
        isFold = fold;
    }
}
