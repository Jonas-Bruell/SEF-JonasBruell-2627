package be.odisee.gamenighthub.service;

import be.odisee.gamenighthub.model.Game;
import be.odisee.gamenighthub.model.Player;
import be.odisee.gamenighthub.model.Session;
import be.odisee.gamenighthub.model.User;
import be.odisee.gamenighthub.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LeaderboardServiceTest {

    private SessionRepository sessionRepository;
    private LeaderboardCalculator leaderboardCalculator;
    private LeaderboardService service;

    private Player player1;
    private Player player2;
    private Player player3;

    private Game game1;
    private Game game2;

    private Session session1;
    private Session session2;

    private final LocalDate datum = LocalDate.of(2000, 1, 1);
    private final String locatie = "Odisee Brussel";

    @BeforeEach
    public void setUp() {
        sessionRepository = mock(SessionRepository.class);
        leaderboardCalculator = mock(LeaderboardCalculator.class);
        service = new LeaderboardService(sessionRepository, leaderboardCalculator);

        player1 = new Player("Speler1", new User("Speler1", "speler1@mail.test", "ww"));
        player2 = new Player("Speler2", new User("Speler2", "speler2@mail.test", "ww"));
        player3 = new Player("Speler3", new User("Speler3", "speler3@mail.test", "ww"));

        game1 = new Game("Game1", 1, 2, 30);
        game2 = new Game("Game2", 1, 2, 30);

        session1 = new Session(game1, datum, locatie);
        session2 = new Session(game2, datum, locatie);
    }

    @Test
    void geefLeaderBoard_leegLeaderboard_geeftLegeLijst () {
        // Arrange
        when(sessionRepository.findAll()).thenReturn(new ArrayList<>());
        // Act
        List<LeaderboardEntry> leaderboard = service.geefLeaderboard();
        // Assert(Act)
        assertSame(new ArrayList<>(), leaderboard);
    }

    @Test
    void geefLeaderBoard_echteSesiesInLeaderboard_geeftLijstMetLeaderboardEntries () {
        // Arrange
        session1.voegSpelerToe(player1);
        session1.voegSpelerToe(player2);
        session1.registreerUitslag(player1, 10, true);
        session1.registreerUitslag(player2, 5, false);
        when(sessionRepository.findAll()).thenReturn(List.of(session1, session2));
        // Act
        List<LeaderboardEntry> leaderboard = service.geefLeaderboard();
        // Assert
        assertAll(
                () -> assertEquals(2, leaderboard.size()),
                () -> assertEquals(player1, leaderboard.get(0).getSpeler()),
                () -> assertEquals(10, leaderboard.get(0).getTotaalPunten()),
                () -> assertEquals(1, leaderboard.get(0).getAantalOverwinningen()),
                () -> assertEquals(player2, leaderboard.get(1).getSpeler()),
                () -> assertEquals(5, leaderboard.get(1).getTotaalPunten()),
                () -> assertEquals(0, leaderboard.get(1).getAantalOverwinningen())
        );
    }

    @Test
    void geefLeaderBoard_spySesiesInLeaderboard_geeftLijstMetLeaderboardEntries () {
        // Arrange
        session1.voegSpelerToe(player1);
        session1.voegSpelerToe(player2);
        session1.registreerUitslag(player1, 10, true);
        session1.registreerUitslag(player2, 5, false);
        List<Session> sessies = List.of(session1, session2);
        when(sessionRepository.findAll()).thenReturn(sessies);
        // Act
        List<LeaderboardEntry> leaderboard = service.geefLeaderboard();
        // Verify
        verify(leaderboardCalculator).berekenLeaderboard(sessies);
    }


    void geefLeaderBoard_MockSesiesInLeaderboard_geeftLijstMetLeaderboardEntries () {
        // Arrange
        session1.voegSpelerToe(player1);
        session1.voegSpelerToe(player2);
        session1.registreerUitslag(player1, 10, true);
        session1.registreerUitslag(player2, 5, false);
        List<Session> sessies = List.of(session1, session2);
        when(sessionRepository.findAll()).thenReturn(sessies);
        List<LeaderboardEntry> leaderboardMock = List.of(
                new LeaderboardEntry(player1, 10, 1),
                new LeaderboardEntry(player2, 5, 0)
        );
        when(leaderboardCalculator.berekenLeaderboard(sessies)).thenReturn(leaderboardMock);
        // Act
        List<LeaderboardEntry> leaderboard = service.geefLeaderboard();
        // Assert
        assertSame(leaderboardMock, leaderboard);
    }
}
