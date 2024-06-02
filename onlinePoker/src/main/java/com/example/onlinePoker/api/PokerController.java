package com.example.onlinePoker.api;

import com.example.onlinePoker.game.Game;
import com.example.onlinePoker.players.Player;
import com.example.onlinePoker.players.PlayerInfo;
import com.example.onlinePoker.table.Card;
import com.example.onlinePoker.table.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
public class PokerController {

    @Autowired
    SimpMessagingTemplate messageTemplate;

    private final List<Player> listOfPlayers = new ArrayList<>(Collections.nCopies(9, null));
    private final Game game = new Game();
    private final Table table = game.getTable();
    private int smallBlind = 1;


    @MessageMapping("/getPlayers")
    @SendTo("/client/players")
    public List<Player> getPlayers() {

        return listOfPlayers;
    }


    @MessageMapping("/addPlayer")
    @SendTo("/client/game")
    public Game addPlayer(@RequestParam PlayerInfo playerInfo) {
        String name = playerInfo.getName();
        int position = playerInfo.getPosition();
        System.out.println(name);
        System.out.println(position);


        if (name != null && position >= 0 ) {
            if(listOfPlayers.get(position) == null) {
                Player player = new Player();
                player.setName(name);
                listOfPlayers.set(position, player);
                game.setPlayers(listOfPlayers);
            }else {
                throw new IllegalArgumentException("This place is occupied");
            }
            return game;

        } else{
            throw new IllegalArgumentException("Player name cannot be null");
        }


    }

    public int checkPlayersNumber(List<Player> listOfPlayers){
        int number = 0;
        for(Player player: listOfPlayers){
            if(player != null){
                number ++;
            }
        }
        return number;
    }
    @MessageMapping("/dealCards")
    public ResponseEntity<?> startGame() {
        if (checkPlayersNumber(listOfPlayers) >= 2) {

            game.setPlayers(listOfPlayers);
            game.dealTheCards(listOfPlayers);
            game.getBlinds(smallBlind, listOfPlayers);

            System.out.println("deal the cards method");

            messageTemplate.convertAndSend("/client/game", game);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Not enough players to start the game.");
        }
    }
    @MessageMapping("/dealTheFlop")
    @SendTo("/client/cards")
    public List<Card>  dealTheFlop() {

        System.out.println("deal the flop method");
            game.dealTheFlop();
            List<Card> flop = table.getFlop();
        if(flop != null){
            return flop;
        } else{
            throw new IllegalArgumentException("Flop cannot be null");
        }

    }

    @MessageMapping("/dealTheTurn")
    @SendTo("/client/turn")
    public Card  dealTheTurn() {

        System.out.println("deal the turn method");
        game.dealTheTurn();
        Card turn = table.getTurn();
        if(turn != null){
            return turn;
        } else{
            throw new IllegalArgumentException("Turn cannot be null");
        }
    }

    @MessageMapping("/dealTheRiver")
    @SendTo("/client/river")
    public Card  dealTheRiver() {

        System.out.println("deal the river");
        game.dealTheRiver();
        Card river = table.getRiver();
        if(river != null){
            return river;
        } else{
            throw new IllegalArgumentException("River cannot be null");
        }
    }






}
