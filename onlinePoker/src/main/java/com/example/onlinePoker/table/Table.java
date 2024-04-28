package com.example.onlinePoker.table;


public class Table {

    private int pot;
    private String[] flop;
    private String turn;
    private String river;

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public String[] getFlop() {
        return flop;
    }

    public void setFlop(String[] flop) {
        this.flop = flop;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }
}
