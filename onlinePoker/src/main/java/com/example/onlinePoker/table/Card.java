package com.example.onlinePoker.table;

public enum Card {
    TWO_HEARTS(2, "hearts"),
    TWO_DIAMONDS(2, "diamonds"),
    TWO_CLUBS(2, "clubs"),
    TWO_SPADES(2, "spades"),
    THREE_HEARTS(3, "hearts"),
    THREE_DIAMONDS(3, "diamonds"),
    THREE_CLUBS(3, "clubs"),
    THREE_SPADES(3, "spades"),
    FOUR_HEARTS(4, "hearts"),
    FOUR_DIAMONDS(4, "diamonds"),
    FOUR_CLUBS(4,"clubs"),
    FOUR_SPADES(4, "spades"),
    FIVE_HEARTS(5, "hearts"),
    FIVE_DIAMONDS(5, "diamonds"),
    FIVE_CLUBS(5,"clubs"),
    FIVE_SPADES(5, "spades"),
    SIX_HEARTS(6, "hearts"),
    SIX_DIAMONDS(6, "diamonds"),
    SIX_CLUBS(6, "clubs"),
    SIX_SPADES(6, "spades"),
    SEVEN_HEARTS(7, "hearts"),
    SEVEN_DIAMONDS(7, "diamonds"),
    SEVEN_CLUBS(7, "clubs"),
    SEVEN_SPADES(7, "spades"),
    EIGHT_HEARTS(8, "hearts"),
    EIGHT_DIAMONDS(8, "diamonds"),
    EIGHT_CLUBS(8, "clubs"),
    EIGHT_SPADES(8, "spades"),
    NINE_HEARTS(9, "hearts"),
    NINE_DIAMONDS(9, "diamonds"),
    NINE_CLUBS(9,"clubs"),
    NINE_SPADES(9, "spades"),
    TEN_HEARTS(10, "hearts"),
    TEN_DIAMONDS(10, "diamonds"),
    TEN_CLUBS(10, "clubs"),
    TEN_SPADES(10, "spades"),
    J_HEARTS(11, "hearts"),
    J_DIAMONDS(11, "diamonds"),
    J_CLUBS(11, "clubs"),
    J_SPADES(11, "spades"),
    Q_HEARTS(12, "hearts"),
    Q_DIAMONDS(12, "diamonds"),
    Q_CLUBS(12, "clubs"),
    Q_SPADES(12, "spades"),
    K_HEARTS(13, "hearts"),
    K_DIAMONDS(13, "diamonds"),
    K_CLUBS(13, "clubs"),
    K_SPADES(13, "spades"),
    A_HEARTS(14, "hearts"),
    A_DIAMONDS(14, "diamonds"),
    A_CLUBS(14, "clubs"),
   A_SPADES(14, "spades");

    private final int value;
    private final String suit;
    public int getValue() {
        return value;
    }


    public String getSuit() {
        return suit;
    }

    Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }
}
