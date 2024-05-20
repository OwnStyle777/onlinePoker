package com.example.onlinePoker.api;

import com.example.onlinePoker.game.Game;
import com.example.onlinePoker.players.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PokerController {

    @Autowired
    SimpMessagingTemplate messageTemplate;

    List<Player> listOfPlayers = new ArrayList<>();
    Game game;


    @MessageMapping("/addPlayer")
    @SendTo("client/players")
    public ResponseEntity addPlayer(@RequestParam String name) {
        if (name != null) {
            Player player = new Player();
            player.setName(name);
            listOfPlayers.add(player);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/startGame")
    public ResponseEntity startGame() {
        if (listOfPlayers.size() >= 2) {
            game = new Game();
            game.dealTheCards(listOfPlayers);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Not enough players to start the game.");
        }
    }

    @GetMapping("/gameState")
    public ResponseEntity<?> getGameState() {
        if (game != null) {
            // Tu získate aktuálny stav hry a vrátite ho ako odpoveď
            return ResponseEntity.ok().body(game);
        } else {
            return ResponseEntity.badRequest().body("No game in progress.");
        }
    }

}
