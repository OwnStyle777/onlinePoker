package com.example.onlinePoker.table;

public enum Card {
    TWO_HEARTS(2),
    TWO_DIAMONDS(2),
    TWO_CLUBS(2),
    TWO_SPADES(2),
    THREE_HEARTS(3),
    THREE_DIAMONDS(3),
    THREE_CLUBS(3),
    THREE_SPADES(3),
    FOUR_HEARTS(4),
    FOUR_DIAMONDS(4),
    FOUR_CLUBS(4),
    FOUR_SPADES(4),
    FIVE_HEARTS(5),
    FIVE_DIAMONDS(5),
    FIVE_CLUBS(5),
    FIVE_SPADES(5),
    SIX_HEARTS(6),
    SIX_DIAMONDS(6),
    SIX_CLUBS(6),
    SIX_SPADES(6),
    SEVEN_HEARTS(7),
    SEVEN_DIAMONDS(7),
    SEVEN_CLUBS(7),
    SEVEN_SPADES(7),
    EIGHT_HEARTS(8),
    EIGHT_DIAMONDS(8),
    EIGHT_CLUBS(8),
    EIGHT_SPADES(8),
    NINE_HEARTS(9),
    NINE_DIAMONDS(9),
    NINE_CLUBS(9),
    NINE_SPADES(9),
    TEN_HEARTS(10),
    TEN_DIAMONDS(10),
    TEN_CLUBS(10),
    TEN_SPADES(10),
    J_HEARTS(11),
    J_DIAMONDS(11),
    J_CLUBS(11),
    J_SPADES(11),
    Q_HEARTS(12),
    Q_DIAMONDS(12),
    Q_CLUBS(12),
    Q_SPADES(12),
    K_HEARTS(13),
    K_DIAMONDS(13),
    K_CLUBS(13),
    K_SPADES(13),
    A_HEARTS(14),
    A_DIAMONDS(14),
    A_CLUBS(14),
   A_SPADES(14);

    public int getValue() {
        return value;
    }

    private final int value;

    Card(int value) {
        this.value = value;
    }
}
