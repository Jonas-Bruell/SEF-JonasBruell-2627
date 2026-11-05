package be.odisee.gamenighthub.service;

import be.odisee.gamenighthub.model.Game;
import be.odisee.gamenighthub.model.Session;
import be.odisee.gamenighthub.repository.GameRepository;
import be.odisee.gamenighthub.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SessionServiceTest {

    private GameRepository gamerepository;
    private SessionRepository sessionrepository;
    private SessionService service;

    private final LocalDate datum = LocalDate.of(2000, 1, 1);
    private final String locatie = "Odisee Brussel";

    @BeforeEach
    void setUp() {
        gamerepository = mock(GameRepository.class);
        sessionrepository = mock(SessionRepository.class);
        service = new SessionService(sessionrepository, gamerepository);
    }

    @Test
    void plan_onbekendSpel_geeftIllegalArgumentException() {
        // Arrange
        when(gamerepository.zoekOpTitel("onbekend spel")).thenReturn(null);
        // Assert(Act)
        assertThrows(IllegalArgumentException.class,
                () -> service.plan("onbekend spel", datum, locatie));
        // Verify
        verify(gamerepository, never()).save(any(Game.class));
    }

    @Test
    void plan_gekendSpel_maaktNieuweSessionEnSlaatOpEnGeeftTerug() {
        // Arrange
        ArgumentCaptor<Session> captor = ArgumentCaptor.forClass(Session.class);
        when(gamerepository.zoekOpTitel("gekend spel")).thenReturn(new Game("gekend spel", 1, 2, 30));
        // Act
        Session sessie = service.plan("gekend spel", datum, locatie);
        // Assert
        Session bewaard = captor.getValue();
        assertAll(
                () -> assertEquals("gekend spel", bewaard.getGame().getTitel()),
                () -> assertEquals(datum, bewaard.getDatum()),
                () -> assertEquals(locatie, bewaard.getLocation())
        );
        assertSame(sessie, bewaard);
        // Verify
        verify(sessionrepository).save(captor.capture());
    }
}
