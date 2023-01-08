package cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Table(name = "game")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    @Column(name = "dice1")
    private int dice1 = Dice.throwDice();

    @Column(name = "dice2")
    private int dice2 = Dice.throwDice();

    @Transient
    private boolean playerHasWon = playerHasWon(dice1, dice2);

    @ManyToOne
    @JoinColumn(name = "player_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Player player;

    public Game() {
    }

    public Long getGameId() {
        return gameId;
    }

    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public boolean playerHasWon(int dice1, int dice2) {
        return dice1 + dice2 == 7;
    }

    public boolean isPlayerHasWon() {
        return playerHasWon;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}


