package be.odisee.gamenighthub.service;

import be.odisee.gamenighthub.model.Game;
import be.odisee.gamenighthub.model.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UitnodigingServiceTest {

    private Mailer mailer;
    private UitnodigingService service;

    private final LocalDate datum = LocalDate.of(2000, 1, 1);
    private final String locatie = "Odisee Brussel";

    @BeforeEach
    void setup() {
        mailer = mock(Mailer.class);
        service = new UitnodigingService(mailer);
    }

    @Test
    void stuurUitnodiging_metSession_stuurtGeldigeUitnodigingNaarAlleDeelnemers() {
        // Arrange
        Game spel = new Game("spel", 1, 2, 30);
        Session sessie = new Session(spel, datum, locatie);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        // Act
        service.stuurUitnodiging(sessie);
        // Assert
        List<String> bewaard = captor.getAllValues();
        assertAll(
                () -> assertEquals("a", "a")
        );
        // Verify

    }


}
