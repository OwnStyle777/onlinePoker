package com.example.onlinePoker;
import com.example.onlinePoker.game.HandEvaluator;
import com.example.onlinePoker.api.table.Card;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RoyalFLushTest  implements HandEvaluator{
    @ParameterizedTest
    @MethodSource("provideCardsForRoyalFlushTest")
    void isRoyalFlush(Card[] playerCards, List<Card> tableCards){
        String result = checkTheRoyalFlush(playerCards, tableCards);

        assertEquals("is Royal flush", result);

    }

    static Stream<Arguments> provideCardsForRoyalFlushTest(){
        return Stream.of(
                Arguments.of(
                        new Card[]{Card.SEVEN_CLUBS, Card.SIX_CLUBS},
                        Arrays.asList(Card.A_CLUBS, Card.K_CLUBS, Card.TEN_CLUBS, Card.Q_CLUBS, Card.J_CLUBS)

                ),
                Arguments.of(
                        new Card[]{Card.SEVEN_CLUBS, Card.SIX_CLUBS},
                        Arrays.asList(Card.A_DIAMONDS, Card.K_DIAMONDS, Card.Q_DIAMONDS, Card.J_DIAMONDS, Card.TEN_DIAMONDS)

                ),
                Arguments.of(
                        new Card[]{Card.A_SPADES, Card.SIX_CLUBS},
                        Arrays.asList(Card.J_SPADES, Card.FOUR_CLUBS, Card.TEN_SPADES, Card.Q_SPADES, Card.K_SPADES)

                ),
                Arguments.of(
                        new Card[]{Card.A_CLUBS, Card.K_CLUBS},
                        Arrays.asList(Card.J_CLUBS, Card.TEN_SPADES, Card.TEN_CLUBS, Card.THREE_DIAMONDS, Card.Q_CLUBS)

                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideCardsForNotRoyalFlushTest")
    void isNotRoyalFlush(Card[] playerCards, List<Card> tableCards){
        String result = checkTheRoyalFlush(playerCards, tableCards);

        assertEquals("", result);

    }
    static Stream<Arguments> provideCardsForNotRoyalFlushTest(){
        return Stream.of(
                Arguments.of(
                        new Card[]{Card.SEVEN_CLUBS, Card.Q_HEARTS},
                        Arrays.asList(Card.A_CLUBS, Card.K_CLUBS, Card.TEN_CLUBS, Card.Q_SPADES, Card.J_CLUBS)

                ),
                Arguments.of(
                        new Card[]{Card.SEVEN_CLUBS, Card.SIX_CLUBS},
                        Arrays.asList(Card.A_HEARTS, Card.K_DIAMONDS, Card.Q_DIAMONDS, Card.J_DIAMONDS, Card.TEN_DIAMONDS)

                ),
                Arguments.of(
                        new Card[]{Card.A_SPADES, Card.SIX_CLUBS},
                        Arrays.asList(Card.J_SPADES, Card.FOUR_CLUBS, Card.TEN_HEARTS, Card.Q_SPADES, Card.K_HEARTS)

                ),
                Arguments.of(
                        new Card[]{Card.A_CLUBS, Card.K_CLUBS},
                        Arrays.asList(Card.J_CLUBS, Card.SIX_DIAMONDS, Card.TEN_HEARTS, Card.THREE_DIAMONDS, Card.Q_CLUBS)

                )
        );
    }

}
