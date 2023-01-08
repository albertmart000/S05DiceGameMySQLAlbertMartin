package cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.repository;

import cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IGameRepository extends JpaRepository<Game, Long> {

    @Transactional
    void deleteGamesByPlayerPlayerIdIs(Long playerId);

    List<Game> findAllByPlayerPlayerId(Long playerId);

}
