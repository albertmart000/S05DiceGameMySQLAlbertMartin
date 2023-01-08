package cat.itacademy.barcelonactiva.S05DiceGameMySQLAlbertMartin.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "player")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "registration_date")
    private Date registrationDate;

    @Transient
    private double rateGamesWon;

    @OneToMany(mappedBy = "player")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Game> gameList = new ArrayList<>();

    public Player() {
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public double getRateGamesWon() {
        if (gameList.size() == 0) {
            this.rateGamesWon = 0.0;
        } else {
            for (Game game : gameList) {
                int gamesWon = 0;
                if (game.isPlayerHasWon()) {
                    gamesWon++;
                    this.rateGamesWon = (double) gamesWon / gameList.size() * 100;
                }
            }
        }
        return rateGamesWon;
    }

    public void setRateGamesWon(double rateGamesWon) {
        this.rateGamesWon = rateGamesWon;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

}
