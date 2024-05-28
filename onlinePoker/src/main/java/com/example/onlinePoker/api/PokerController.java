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

    private  List<Player> listOfPlayers = new ArrayList<>(Collections.nCopies(9, null));
    Game game = new Game();
    Table table = game.getTable();

    @MessageMapping("/getPlayers")
    @SendTo("/client/players")
    public List<Player> getPlayers() {
        return listOfPlayers;
    }


    @MessageMapping("/addPlayer")
    @SendTo("/client/players")
    public List<Player> addPlayer(@RequestParam PlayerInfo playerInfo) {
        String name = playerInfo.getName();
        int position = playerInfo.getPosition();
        System.out.println(name);
        System.out.println(position);


        if (name != null && position >= 0 ) {
            if(listOfPlayers.get(position) == null) {
                Player player = new Player();
                player.setName(name);
                listOfPlayers.set(position, player);
            }
            return listOfPlayers;

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
            game.dealTheCards(listOfPlayers);
            messageTemplate.convertAndSend("/client/players", listOfPlayers);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Not enough players to start the game.");
        }
    }
    @MessageMapping("/dealTheFlop")
    @SendTo("/client/cards")
    public List<Card>  dealTheFlop() {

            game.dealTheFlop();
            List<Card> flop = table.getFlop();
        if(flop != null){
            return flop;
        } else{
            throw new IllegalArgumentException("Flop cannot be null");
        }

    }




}
