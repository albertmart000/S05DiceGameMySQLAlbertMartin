package cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.controllers;

import cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.domain.Game;
import cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.services.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private IGameService gameService;

    @DeleteMapping("/players/{playerId}/games")
    public ResponseEntity<String> deleteGameList(@PathVariable Long playerId) {
        try {
            gameService.deleteGameList(playerId);
            return new ResponseEntity<>("S'ha esborrat la llista de jugades del jugador amb el id: " + playerId,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No s'ha trobat cap jugador amb el id: " + playerId,
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/players/{playerId}/games")
    public ResponseEntity<List<Game>> getGameList(@PathVariable Long playerId) {
        try {
            List<Game> gameList = gameService.getGameListByPlayer(playerId);
            if (gameList.isEmpty()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Error", "No n'hi ha cap partida a la llista");
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(gameList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/players/ranking/rateGamesWon")
    public ResponseEntity<String> getRateAllGamesWon() {
        try {
            return new ResponseEntity<>("El percentatge de partides guanyades es de " +
                    String.format("%.2f", gameService.getRateAllGamesWon()) + " %.",
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
