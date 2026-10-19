package be.odisee.gamenighthub.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class SessionTest {

    private Player emma;
    private Player noah;
    private Session session;

    @BeforeEach
    void setup() {
        this.emma = new Player("Emma", new User("Emma", "emma@odisee.be", "Odisee123!"));
        this.noah = new Player("Noah", new User("Noah", "noah@odisee.be", "Odisee123!"));
        this.session = new Session(
                new Game("Catan", 3, 6, 60),
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

        Assertions.assertEquals(1, session.getSpelers().size());
        Assertions.assertEquals(List.of(emma), session.getSpelers());
    }
}
