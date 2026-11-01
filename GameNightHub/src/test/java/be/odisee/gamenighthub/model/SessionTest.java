package be.odisee.gamenighthub.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class SessionTest {

    private Player emma;
    private Player noah;
    private Player ella;
    private Player paul;
    private Player sara;
    private Session session;

    @BeforeEach
    void setup() {
        this.emma = new Player("Emma", new User("Emma", "emma@odisee.be", "Odisee123!"));
        this.noah = new Player("Noah", new User("Noah", "noah@odisee.be", "Odisee123!"));
        this.ella = new Player("Ella", new User("Ella", "ella@odisee.be", "Odisee123!"));
        this.paul = new Player("Paul", new User("Paul", "paul@odisee.be", "Odisee123!"));
        this.sara = new Player("Sara", new User("Sara", "sara@odisee.be", "Odisee123!"));
        this.session = new Session(
                new Game("Catan", 2, 4, 60),
                LocalDate.of(2026, 11, 3),
                "Odisee Brussel"
        );
    }

    @Test
    void voegSpelerToe_nieuweSpeler_komtInDeelnemers() {
        session.voegSpelerToe(emma);
        Assertions.assertEquals(List.of(emma), session.getSpelers());
    }

    @Test
    void voegSpelerToe_tweeVerschillendeSpelers_komenBeideInDeelnemers() {
        session.voegSpelerToe(emma);
        session.voegSpelerToe(noah);

        Assertions.assertEquals(List.of(emma, noah), session.getSpelers());
    }

    @Test
    void voegSpelerToe_zelfdeSpelerTweeKeer_staatSlechtsEenKeerInDeelnemers() {
        session.voegSpelerToe(emma);
        session.voegSpelerToe(emma);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, session.getSpelers().size()),
                () -> Assertions.assertEquals(List.of(emma), session.getSpelers())
        );
    }

    @Test
    void registreerUitslag_spelerKrijgtTwaalfPuntenEnWint_controleerPuntenEnGewonnen() {
        session.voegSpelerToe(emma);
        session.registreerUitslag(emma, 12, true);

        Score score = session.getScores().getFirst();
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, session.getScores().size()),
                () -> Assertions.assertEquals(emma, score.getSpeler()),
                () -> Assertions.assertEquals(12, score.getPunten()),
                () -> Assertions.assertTrue(score.isGewonnen())
        );
    }

    @Test
    void registreerUitslag_tweeSpelersKrijgenScore_komenBeideInScores() {
        session.voegSpelerToe(emma);
        session.voegSpelerToe(noah);
        session.registreerUitslag(emma, 12, true);
        session.registreerUitslag(noah, 11, false);

        Assertions.assertEquals(2, session.getScores().size());
    }

    @Test
    void registreerUitslag_spelerNietInSessionKrijgtScore_komtNietInScores() {
        session.voegSpelerToe(emma);
        session.registreerUitslag(noah, 12, true);

        Assertions.assertEquals(0, session.getScores().size());
    }

    @Test
    void isVolzet_nieuweSessie_geeftFalse() {
        Assertions.assertFalse(session.isVolzet());
    }

    @Test
    void isVolzet_vierDeelnemers_geeftTrue() {
        session.voegSpelerToe(emma);
        session.voegSpelerToe(noah);
        session.voegSpelerToe(ella);
        session.voegSpelerToe(paul);
        session.voegSpelerToe(sara);
        Assertions.assertTrue(session.isVolzet());
    }

}
