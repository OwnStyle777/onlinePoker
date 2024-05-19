package com.example.onlinePoker;

import com.example.onlinePoker.game.HandEvaluator;
import com.example.onlinePoker.table.Card;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PokerHandEvaluatorTest implements HandEvaluator {


    @ParameterizedTest
    @MethodSource("provideCardsForStraightFlushTest")
    void testCheckTheStraightFlush(Card[] playerCards, List<Card> tableCards, String expected) {
        String result = checkTheStraightFlush(playerCards, tableCards);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideCardsForStraightFlushTest")
    void testCheckTheStraight(Card[] playerCards, List<Card> tableCards) {

        String result = checkStraight(playerCards, tableCards);
        assertEquals(" is Straight", result);
    }

    static Stream<Arguments> provideCardsForStraightFlushTest() {
        return Stream.of(
                Arguments.of(
                        new Card[]{Card.SEVEN_CLUBS, Card.SIX_CLUBS},
                        Arrays.asList(Card.FIVE_CLUBS, Card.FOUR_CLUBS, Card.THREE_CLUBS, Card.TWO_CLUBS, Card.A_DIAMONDS),
                        "is Straight flush"
                ),
                Arguments.of(
                        new Card[]{Card.TWO_CLUBS, Card.THREE_DIAMONDS},
                        Arrays.asList(Card.FIVE_DIAMONDS, Card.SEVEN_DIAMONDS, Card.SIX_DIAMONDS, Card.EIGHT_DIAMONDS, Card.NINE_DIAMONDS),
                        "is Straight flush"
                ),
                Arguments.of(
                        new Card[]{Card.NINE_CLUBS, Card.TEN_CLUBS},
                        Arrays.asList(Card.J_CLUBS, Card.Q_CLUBS, Card.K_CLUBS, Card.A_CLUBS, Card.TWO_DIAMONDS),
                        "is Straight flush"
                ),
                Arguments.of(
                        new Card[]{Card.A_HEARTS, Card.TWO_HEARTS},
                        Arrays.asList(Card.FOUR_HEARTS, Card.Q_CLUBS, Card.FIVE_HEARTS, Card.A_CLUBS, Card.THREE_HEARTS),
                        "is Straight flush"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideCardsForNotStraightFlushTest")
    void testIsNotTheStraight(Card[] playerCards, List<Card> tableCards) {

        String result = checkStraight(playerCards, tableCards);
        assertEquals("", result);
    }

    @MethodSource("provideCardsForNotStraightFlushTest")
    void testIsNotTheStraightFlush(Card[] playerCards, List<Card> tableCards) {

        String result = checkStraight(playerCards, tableCards);
        assertEquals("", result);
    }

    static Stream<Arguments> provideCardsForNotStraightFlushTest() {
        return Stream.of(
                Arguments.of(
                        new Card[]{Card.SEVEN_CLUBS, Card.SIX_CLUBS},
                        Arrays.asList(Card.FIVE_CLUBS, Card.FOUR_CLUBS, Card.TEN_CLUBS, Card.TWO_CLUBS, Card.A_DIAMONDS)

                ),
                Arguments.of(
                        new Card[]{Card.TWO_DIAMONDS, Card.THREE_DIAMONDS},
                        Arrays.asList(Card.FIVE_DIAMONDS, Card.A_DIAMONDS, Card.SIX_DIAMONDS, Card.EIGHT_DIAMONDS, Card.NINE_DIAMONDS)

                ),
                Arguments.of(
                        new Card[]{Card.EIGHT_CLUBS, Card.NINE_CLUBS},
                        Arrays.asList(Card.J_CLUBS, Card.Q_CLUBS, Card.K_CLUBS, Card.A_CLUBS, Card.TWO_DIAMONDS)

                        )

                );
    }
}
