package cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.services;

import cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.domain.Game;
import cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.domain.Player;

import java.util.List;
import java.util.Optional;

public interface IPlayerService {

    Player addPlayer(Player player);

    Player updatePlayer(Long id, Player playerToUpdate);

    Boolean deletePlayer(Long id);

    Game addGame (Long Id, Game game);

    Optional<Player> getPlayerById(Long id);

    List<Player> getAllPlayers();

    Optional <Player> getWinningPlayer();

    Optional <Player> getLosingPlayer();

    List<Player> getRankingPlayers();

}
