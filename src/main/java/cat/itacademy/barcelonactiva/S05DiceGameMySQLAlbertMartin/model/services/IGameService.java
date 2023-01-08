package cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.services;

import cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.domain.Game;

import java.util.List;

public interface IGameService {

    List<Game> getGameListByPlayer(Long playerId);

    Boolean deleteGameList(Long playerId);

    double getRateAllGamesWon();

}
