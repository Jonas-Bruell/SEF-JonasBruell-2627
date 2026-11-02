package be.odisee.gamenighthub.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {

    private Player emma;
    private Player noah;
    private Session session;

    @BeforeEach
    void setup() {
        this.emma = new Player("Emma", new User("Emma", "emma@odisee.be", "Odisee123!"));
        this.noah = new Player("Noah", new User("Noah", "noah@odisee.be", "Odisee123!"));
        this.session = new Session(
                new Game("Catan", 2, 4, 60),
                LocalDate.of(2026, 11, 3),
                "Odisee Brussel"
        );
    }

    @Test
    void voegSpelerToe_nieuweSpeler_komtInDeelnemers() {
        // Arrange -> setup()
        // Act
        session.voegSpelerToe(emma);
        // Assert
        assertEquals(List.of(emma), session.getSpelers());
    }

    @Test
    void voegSpelerToe_tweeVerschillendeSpelers_komenBeideInDeelnemers() {
        // Arrange -> setup()
        // Act
        session.voegSpelerToe(emma);
        session.voegSpelerToe(noah);
        // Assert
        assertEquals(List.of(emma, noah), session.getSpelers());
    }

    @Test
    void voegSpelerToe_zelfdeSpelerTweeKeer_staatSlechtsEenKeerInDeelnemers() {
        // Arrange -> setup()
        // Act
        session.voegSpelerToe(emma);
        session.voegSpelerToe(emma);
        // Assert
        assertAll(
                () -> assertEquals(1, session.getSpelers().size()),
                () -> assertEquals(List.of(emma), session.getSpelers())
        );
    }

    @Test
    void registreerUitslag_spelerKrijgtTwaalfPuntenEnWint_controleerPuntenEnGewonnen() {
        // Arrange -> setup()
        // Act
        session.voegSpelerToe(emma);
        session.registreerUitslag(emma, 12, true);
        // Assert
        Score score = session.getScores().getFirst();
        assertAll(
                () -> assertEquals(1, session.getScores().size()),
                () -> assertEquals(emma, score.getSpeler()),
                () -> assertEquals(12, score.getPunten()),
                () -> assertTrue(score.isGewonnen())
        );
    }

    @Test
    void registreerUitslag_tweeSpelersKrijgenScore_komenBeideInScores() {
        // Arrange -> setup()
        // Act
        session.voegSpelerToe(emma);
        session.voegSpelerToe(noah);
        session.registreerUitslag(emma, 12, true);
        session.registreerUitslag(noah, 11, false);
        // Assert
        assertEquals(2, session.getScores().size());
    }

    @Test
    void registreerUitslag_spelerNietInSessionKrijgtScore_komtNietInScores() {
        // Arrange -> setup()
        // Act
        session.voegSpelerToe(emma);
        session.registreerUitslag(noah, 12, true);
        // Assert
        assertEquals(0, session.getScores().size());
    }

    @Test
    void isVolzet_nieuweSessie_geeftFalse() {
        // Arrange -> setup()
        // Act
        // Assert
        assertFalse(session.isVolzet());
    }

    @Test
    void isVolzet_maximumSpelersBereikt_geeftTrue() {
        // Arrange -> setup()
        Player ella = new Player("Ella", new User("Ella", "ella@odisee.be", "Odisee123!"));
        Player paul = new Player("Paul", new User("Paul", "paul@odisee.be", "Odisee123!"));
        // Act
        session.voegSpelerToe(emma);
        session.voegSpelerToe(noah);
        session.voegSpelerToe(ella);
        session.voegSpelerToe(paul);
        // Assert
        Assertions.assertTrue(session.isVolzet());
    }

    @Test
    void isVolzet_minderDanMaximumSpelers_geeftFalse() {
        // Arrange -> setup()
        Player ella = new Player("Ella", new User("Ella", "ella@odisee.be", "Odisee123!"));
        // Act
        session.voegSpelerToe(emma);
        session.voegSpelerToe(noah);
        session.voegSpelerToe(ella);
        // Assert
        assertFalse(session.isVolzet());
    }

}
