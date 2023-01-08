package cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.controllers;

import cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.domain.Game;
import cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.domain.Player;
import cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.services.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    @PostMapping("/players")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        try {
            playerService.addPlayer(player);
            return new ResponseEntity<>(player, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/players/{playerId}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long playerId) {
        Optional<Player> optionalPlayer = this.playerService.getPlayerById(playerId);
        if (optionalPlayer.isPresent()) {
            return new ResponseEntity<>(optionalPlayer.get(), HttpStatus.OK);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "No s'ha trobat cap jugador amb el id: " + playerId);
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/players/{playerId}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long playerId, @RequestBody Player player) {
        Player playerToUpdate = playerService.updatePlayer(playerId, player);
        if (playerToUpdate != null) {
            return new ResponseEntity<>(playerToUpdate, HttpStatus.OK);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "No s'ha trobat cap jugador amb el id: " + playerId);
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/players/{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long playerId) {
        try {
            playerService.deletePlayer(playerId);
            return new ResponseEntity<>("S'ha esborrat el jugador amb el id: " + playerId,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No s'ha trobat cap jugador amb el id: " + playerId,
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/players")
    public ResponseEntity<List<Player>> getAllPlayers() {
        try {
            List<Player> playerList = playerService.getAllPlayers();
            if (playerList.isEmpty()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Error", "No n'hi ha cap jugador a la llista");
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(playerList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/players/{playerId}/games")
    public ResponseEntity<Game> addGame(@PathVariable Long playerId, @RequestBody Game game) {
        try {
            playerService.addGame(playerId, game);
            return new ResponseEntity<>(game, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/players/ranking/winner")
    public ResponseEntity<Player> getWinningPlayer() {
        Optional<Player> optionalPlayer = playerService.getWinningPlayer();
        if (optionalPlayer.isPresent()) {
            return new ResponseEntity<>(optionalPlayer.get(), HttpStatus.OK);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "No s'ha trobat cap jugador");
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/players/ranking/loser")
    public ResponseEntity<Player> getLosingPlayer() {
        Optional<Player> optionalPlayer = playerService.getLosingPlayer();
        if (optionalPlayer.isPresent()) {
            return new ResponseEntity<>(optionalPlayer.get(), HttpStatus.OK);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "No s'ha trobat cap jugador");
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/players/ranking")
    public ResponseEntity<List<Player>> getRankingPlayers() {
        try {
            List<Player> playerList = playerService.getRankingPlayers();
            if (playerList.isEmpty()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Error", "No n'hi ha cap jugador a la llista");
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(playerList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
