package com.example.onlinePoker.game;

import com.example.onlinePoker.players.Player;
import com.example.onlinePoker.table.Card;

public interface HandEvaluator {
     default String checkHighestPair(Card[] playerCards, Card []cards){
return null;
    }
}
