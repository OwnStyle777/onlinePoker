package com.example.onlinePoker.players;

import jakarta.persistence.Entity;


public class Player {

   private String name;
   private int totalChips = 20000;
   private String[] cards;
   private String combination;
   private int stake;
   private boolean dealer = false;
   private boolean smallBlind = false;
   private boolean bigBlind = false;
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

    public int getStake() {
        return stake;
    }

    public void setStake(int stake) {
        this.stake = stake;
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

    public String[] getCards() {
        return cards;
    }

    public void setCards(String[] cards) {
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


}
