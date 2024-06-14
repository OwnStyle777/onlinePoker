package com.example.onlinePoker.api.table;


import java.util.List;

public class Table {

    private int pot;
    private List<Card> flop;
    private Card turn;
    private Card river;


    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public List<Card> getFlop() {
        return flop;
    }

    public void setFlop(List<Card> flop) {
        this.flop = flop;
    }

    public Card getTurn() {
        return turn;
    }

    public void setTurn(Card turn) {
        this.turn = turn;
    }

    public Card getRiver() {
        return river;
    }

    public void setRiver(Card river) {
        this.river = river;
    }
}
