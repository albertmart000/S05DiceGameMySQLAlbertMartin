package cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.repository;

import cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayerRepository extends JpaRepository<Player, Long> {

    boolean existsByName (String name);

}
