package be.odisee.gamenighthub.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private final String catanTitle = "Catan";
    private Game catan;
    private Game cluedo;

    @BeforeEach
    public void setUp() {
        catan = new Game(catanTitle, 3, 4, 90);
        cluedo = new Game("Cluedo", 4, 6, 30);
    }

    @Test
    void Game_nieuweGame_heeftZelfdeVeldenAlsOproep() {
        // Arrange
        String title = catanTitle;
        int minSpelers = 3;
        int maxSpelers = 4;
        int gemiddeldeSpeelduur = 90;
        // Act
        Game nieuweGame = new Game(title, minSpelers, maxSpelers, gemiddeldeSpeelduur);
        // Assert
        assertAll(
                () -> assertEquals(title, nieuweGame.getTitel()),
                () -> assertEquals(minSpelers, nieuweGame.getMinSpelers()),
                () -> assertEquals(maxSpelers, nieuweGame.getMaxSpelers()),
                () -> assertEquals(gemiddeldeSpeelduur, nieuweGame.getGemiddeldeSpeelduur())
        );
    }

    @Test
    void equals_zelfdeTitelsCaseExactGelijk_geeftTrue() {
        // Arrange
        Game catan1 = catan;
        Game catan2 = new Game(catanTitle, 3, 4, 90);
        // Act
        // Assert
        assertEquals(catan1, catan2);
    }

    @Test
    void equals_zelfdeTitelsCaseNietGelijk_geeftFalse() {
        // Arrange
        Game catan1 = catan;
        Game catan2 = new Game ("CATAN", 3, 4, 90);
        // Act
        // Assert
        assertNotEquals(catan1, catan2);
    }

    @Test
    void equals_spelEqualsAnderObject_geeftFalse() {
        // Arrange -> setup()
        // Act
        assertAll(
                () -> assertNotEquals(null, catan),
                () -> assertNotEquals(new Object(), catan)
        );
    }

    @Test
    void hashCode_zelfdeSpellen_hashGelijk() {
        // Arrange
        Game cluedo1 = cluedo;
        Game cluedo2 = cluedo;
        // Act
        // Assert
        assertEquals(cluedo1.hashCode(), cluedo2.hashCode());
    }

    @Test
    void hashCode_verschillendeSpellen_hashNietGelijk() {
        // Arrange -> setup()
        // Act
        // Assert
        assertNotEquals(cluedo.hashCode(), catan.hashCode());
    }

    @Test
    void setTitel_nieuweTitel_verandertTitelNaarNieuweTitel() {
        // Arrange -> setup()
        Game catanOudeTitel = catan;
        // Act
        String nieuweTitel = "catanNieuweTitel";
        catanOudeTitel.setTitel(nieuweTitel);
        // Assert
        assertEquals(nieuweTitel, catanOudeTitel.getTitel());
    }

}
