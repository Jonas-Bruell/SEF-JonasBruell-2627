package be.odisee.gamenighthub.service;

import be.odisee.gamenighthub.model.Game;
import be.odisee.gamenighthub.model.Player;
import be.odisee.gamenighthub.model.Session;
import be.odisee.gamenighthub.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static be.odisee.gamenighthub.service.LeaderboardCalculator.berekenLeaderboard;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeaderboardCalculatorTest {

    private String emma;
    private String noah;
    private String paul;
    private String dora;

    private Player playerEmma;
    private Player playerNoah;
    private Player playerPaul;
    private Player playerDora;

    private Session sessie1;
    private Session sessie2;

    Dictionary<String, List<Object>> spelers = new Hashtable<>();

    Session sessieMet(List<List<Object>> spelersMetScores) {
        Game game = new Game("Game", 1, spelersMetScores.size() + 1, 1);
        Session sessie = new Session(game, LocalDate.of(2026, 1, 1), "Locatie");
        for (List<Object> spelerMetScore : spelersMetScores) {
            sessie.voegSpelerToe((Player) spelerMetScore.get(0));
            sessie.registreerUitslag(
                    (Player)  spelerMetScore.get(0),
                    (Integer) spelerMetScore.get(1),
                    (Boolean) spelerMetScore.get(2)
            );
        }
        return sessie;
    }

    Dictionary<String, LeaderboardEntry> generateLeaderboards(List<Session> sessions) {
        Dictionary<String, LeaderboardEntry> leaderboards = new Hashtable<>();
        for (LeaderboardEntry leaderboard : berekenLeaderboard(sessions)) {
            leaderboards.put(
                    leaderboard.getSpeler().getBijnaam(),
                    leaderboard
            );
        }
        return leaderboards;
    }

    List<String> extractLeaderboardNames(List<Session> sessions) {
        List<String> leaderboardNames = new ArrayList<>();
        for (LeaderboardEntry leaderboard : berekenLeaderboard(sessions)) {
            leaderboardNames.add(leaderboard.getSpeler().getBijnaam());
        }
        return leaderboardNames;
    }

    @BeforeEach
    void setup() {
        dora = "dora";
        emma = "emma";
        noah = "noah";
        paul = "paul";

        playerDora = new Player(dora, new User(dora, dora + "@odisee.be", "Odisee123!"));
        playerEmma = new Player(emma, new User(emma, emma + "@odisee.be", "Odisee123!"));
        playerNoah = new Player(noah, new User(noah, noah + "@odisee.be", "Odisee123!"));
        playerPaul = new Player(paul, new User(paul, paul + "@odisee.be", "Odisee123!"));
    }

    @Test
    void berekenLeaderboard_heeftSessies_geeftTotaalAantalPunten() {
        // Arrange
        int amountSessionsOfEmma = 2;
        int amountSessionsOfNoah = 1;

        spelers.put(emma, List.of(playerEmma, 5,  false));
        spelers.put(noah, List.of(playerNoah, 10, false));

        sessie1 = sessieMet(List.of(spelers.get(emma)));
        sessie2 = sessieMet(List.of(spelers.get(emma), spelers.get(noah)));

        List<Session> sessions = List.of(sessie1, sessie2);
        // Act
        Dictionary<String, LeaderboardEntry> leaderboards = generateLeaderboards(sessions);
        // Assert
        assertAll(
                () -> assertEquals(
                        (Integer) spelers.get(emma).get(1) * amountSessionsOfEmma,
                        leaderboards.get(emma).getTotaalPunten()
                ),
                () -> assertEquals(
                        (Integer) spelers.get(noah).get(1) * amountSessionsOfNoah,
                        leaderboards.get(noah).getTotaalPunten()
                )
        );
    }

    @Test
    void berekenLeaderboard_heeftSessies_geeftTotaalAantalOverwinningen() {
        // Arrange
        int amountSessionsOfEmma = 2;
        int amountSessionsOfNoah = 1;

        spelers.put(emma, List.of(playerEmma, 5, true));
        spelers.put(noah, List.of(playerNoah, 5, false));

        sessie1 = sessieMet(List.of(spelers.get(emma)));
        sessie2 = sessieMet(List.of(spelers.get(emma), spelers.get(noah)));

        List<Session> sessions = List.of(sessie1, sessie2);
        // Act
        Dictionary<String, LeaderboardEntry> leaderboards = generateLeaderboards(sessions);
        // Assert
        assertAll(
                () -> assertEquals(
                        (boolean) spelers.get(emma).get(2) ? amountSessionsOfEmma : 0,
                        leaderboards.get(emma).getAantalOverwinningen()
                ),
                () -> assertEquals(
                        (boolean) spelers.get(noah).get(2) ? amountSessionsOfNoah : 0,
                        leaderboards.get(noah).getAantalOverwinningen()
                )
        );
    }

    @Test
    void berekenLeaderboard_heeftSessies_geeftVolgordePuntenAftellend() {
        // Arrange
        spelers.put(emma, List.of(playerEmma, 1, false));
        spelers.put(noah, List.of(playerNoah, 3, false));
        spelers.put(paul, List.of(playerPaul, 2, false));
        spelers.put(dora, List.of(playerDora, 4, false));

        sessie1 = sessieMet(List.of(spelers.get(emma), spelers.get(noah), spelers.get(paul), spelers.get(dora)));

        List<Session> sessions = List.of(sessie1);
        // Act
        List<String> leaderboardNames = extractLeaderboardNames(sessions);
        // Assert
        assertEquals(List.of(dora, noah, paul, emma), leaderboardNames);
    }

    @Test
    void berekenLeaderboard_heeftSessies_geeftVolgordeOverwinningenAftellend() {
        // Arrange
        spelers.put(emma, List.of(playerEmma, 1, false));
        spelers.put(noah, List.of(playerNoah, 1, true));
        spelers.put(paul, List.of(playerPaul, 2, true));
        spelers.put(dora, List.of(playerDora, 2, false));

        sessie1 = sessieMet(List.of(spelers.get(emma), spelers.get(noah), spelers.get(paul), spelers.get(dora)));

        List<Session> sessions = List.of(sessie1);
        // Act
        List<String> leaderboardNames = extractLeaderboardNames(sessions);
        // Assert
        assertEquals(List.of(paul, dora, noah, emma), leaderboardNames);
    }

    @Test
    void berekenLeaderboard_heeftSessies_geeftVolgordeBijnamenAlfabetisch() {
        // Arrange
        spelers.put(emma, List.of(playerEmma, 1, false));
        spelers.put(noah, List.of(playerNoah, 1, false));
        spelers.put(paul, List.of(playerPaul, 1, false));
        spelers.put(dora, List.of(playerDora, 1, false));

        sessie1 = sessieMet(List.of(spelers.get(emma), spelers.get(noah), spelers.get(paul), spelers.get(dora)));

        List<Session> sessions = List.of(sessie1);
        // Act
        List<String> leaderboardNames = extractLeaderboardNames(sessions);
        // Assert
        assertEquals(List.of(dora, emma, noah, paul), leaderboardNames);
    }

    @Test
    void berekenLeaderboard_heeftEnkelLegeSessies_geeftLegeLijst() {
        // Arrange
        Game game = new Game("Game", 1, 2, 1);
        Session sessie = new Session(game, LocalDate.of(2026, 1, 1), "Locatie");
        // Act
        // Assert
        assertEquals(List.of(), berekenLeaderboard(List.of(sessie)));
    }

    @Test
    void berekenLeaderboard_heeftGeenSessies_geeftLegeLijst() {
        // Arrange
        // Act
        // Assert
        assertEquals(List.of(), berekenLeaderboard(List.of()));
    }

}
