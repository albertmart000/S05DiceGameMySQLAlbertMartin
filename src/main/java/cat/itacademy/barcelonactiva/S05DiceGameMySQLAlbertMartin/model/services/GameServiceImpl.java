package cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.services;

import cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.domain.Game;
import cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.repository.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements IGameService {

    @Autowired
    private IGameRepository gameRepository;

    @Override
    public List<Game> getGameListByPlayer(Long playerId) {
        return gameRepository.findAllByPlayerPlayerId(playerId);
    }

    @Override
    public Boolean deleteGameList(Long playerId) {
        gameRepository.deleteGamesByPlayerPlayerIdIs(playerId);
        return true;
    }

    @Override
    public double getRateAllGamesWon() {
        List<Game> allGamesList = new ArrayList<>();
        allGamesList.addAll(gameRepository.findAll());
        double rateAllGamesWon = 0.0;
        if (allGamesList.size() == 0) {
           rateAllGamesWon = 0.0;
        } else {
            for (Game game : allGamesList) {
                int allGamesWon = 0;
                if (game.isPlayerHasWon()) {
                    allGamesWon++;
                    rateAllGamesWon = (double) allGamesWon / allGamesList.size() * 100;
                }
            }
        }
        return rateAllGamesWon;
    }
}
