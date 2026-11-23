package be.odisee.gamenighthub.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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
    void Game_nieuweGame_titelIsNietNull() {
        // Arrange
        // Act
        // Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> new Game(null, 1, 2, 3)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "  " })
    void Game_nieuweGame_titelIsNietLeegOfSpaties(String titel) {
        // Arrange
        // Act
        // Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> new Game(titel, 1, 2, 3)
        );
    }

    @ParameterizedTest
    @CsvSource({ "0, false", "1, true" })
    void Game_minSpelersKleinerDanEen_geeftIllegalArgumentException(int minSpelers, boolean expected) {
        // Arrange
        // Act
        // Assert
        if (expected)
            assertEquals(minSpelers, new Game("Catan", minSpelers, 2, 90).getMinSpelers());
        else assertThrows(
                IllegalArgumentException.class,
                () -> new Game("Catan", 0, 2, 90)
        );
    }

    @ParameterizedTest
    @CsvSource({ "2, 1, false", "2, 2, true", "2, 3, true" })
    void Game_maxSpelersKleinerDanMinSpelers_geeftIllegalArgumentException(int minSpelers, int maxSpelers, boolean expected) {
        // Arrange
        // Act
        // Assert
        if (expected)
            assertEquals(maxSpelers, new Game("Catan", minSpelers, maxSpelers, 90).getMaxSpelers());
        else assertThrows(
                IllegalArgumentException.class,
                () -> new Game("Catan", minSpelers, maxSpelers, 90)
        );
    }

    @ParameterizedTest
    @CsvSource({ "-1, false", "0, false", "1, true" })
    void Game_gemiddeldeSpelduurKleinerGelijkAanNul_geeftIllegalArgumentException(int gemiddeldeSpelduur, boolean expected) {
        // Arrange
        // Act
        // Assert
        if (expected)
            assertEquals(gemiddeldeSpelduur,new Game("Catan", 2, 4, gemiddeldeSpelduur).getGemiddeldeSpeelduur());
        else assertThrows(
                IllegalArgumentException.class,
                () -> new Game("Catan", 2, 4, gemiddeldeSpelduur)
        );
    }

    @Test
    void equals_zelfdeTitelsCaseExactGelijk_zijnGelijk() {
        // Arrange
        Game catan1 = catan;
        Game catan2 = new Game(catanTitle, 3, 4, 90);
        // Act
        // Assert
        assertEquals(catan1, catan2);
    }

    @Test
    void equals_zelfdeTitelsCaseNietGelijk_zijnGelijk() {
        // Arrange
        Game catan1 = catan;
        Game catan2 = new Game ("CATAN", 3, 4, 90);
        // Act
        // Assert
        assertEquals(catan1, catan2);
    }

    @Test
    void equals_spelEqualsAnderObject_zijnNietGelijk() {
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
    void hashCode_zelfdeSpellenVerschillendeCase_hashGelijk() {
        // Arrange
        Game catan1 = catan;
        Game catan2 = new Game ("CATAN", 3, 4, 90);
        // Act
        // Assert
        assertEquals(catan1.hashCode(), catan2.hashCode());
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

    @ParameterizedTest
    @CsvSource({ "2, false", "3, true", "4, true", "5, false" })
    void isGeschiktVoor_testGrenzen_geeftRespectievelijkeBoolean(int aantalSpelers, boolean verwachteBoolean) {
        // Arrange -> setup()
        // Act
        boolean isGeschikt = catan.isGeschiktVoor(aantalSpelers);
        // Assert
        assertEquals(isGeschikt, verwachteBoolean);
    }

    @Test
    void isKortSpel_speelduurDertigMinuten_geeftTrue() {
        Game game = new Game("Dobble", 2, 8, 30);
        assertTrue(game.isKortSpel());
    }

}
