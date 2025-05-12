package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@ToString
@Entity
@Table(name = "records")
@NoArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "score")
    private int score;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "game_date", insertable = false, updatable = false)
    private String gameDate;

    public Record(int score, String playerName) {
        this.score = score;
        this.playerName = playerName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
