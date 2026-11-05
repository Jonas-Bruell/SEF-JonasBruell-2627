package be.odisee.gamenighthub.service;

import be.odisee.gamenighthub.model.User;
import be.odisee.gamenighthub.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AanmeldServiceTest {

    private UserRepository userRepository;
    private PasswordHasher passwordHasher;
    private AanmeldService service;
    private User user;

    private final String klaarWachtwoord  = "klaarWachtwoord";
    private final String hashedWachtwoord = "hashedWachtwoord";;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordHasher = mock(PasswordHasher.class);
        service = new AanmeldService(userRepository, passwordHasher);
        user = mock(User.class);

        when(passwordHasher.hash(anyString())).thenReturn(hashedWachtwoord);
        when(passwordHasher.matches(klaarWachtwoord, klaarWachtwoord)).thenReturn(true);
    }

    @Test
    void registreer_emailAlInGebruik_gooitExceptionEnBewaartNiets() {
        // Arrange
        when(userRepository.bestaatEmail("emma@odisee.be")).thenReturn(true);
        // Assert(Act)
        assertThrows(IllegalArgumentException.class,
                () -> service.registreer("Emma", "emma@odisee.be", klaarWachtwoord));
        // Verify
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registreer_nieuweGebruiker_bewaartGebruikerMetGehashtWachtwoord() {
        // Arrange
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        // Act
        User user = service.registreer("Noah", "noah@odisee.be", klaarWachtwoord);
        // Assert
        User bewaard = captor.getValue();
        assertAll(
                () -> assertEquals("Noah", bewaard.getNaam()),
                () -> assertEquals("noah@odisee.be", bewaard.getEmail()),
                () -> assertEquals(hashedWachtwoord, bewaard.getWachtwoordHash())
        );
        assertSame(bewaard, user);
        // Verify
        verify(userRepository).save(captor.capture());
    }

    @Test
    void registreer_wachtwoordNull_geeftExceptionEnBewaartNietsEnBerekentGeenHash() {
        // Arrange
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.registreer("emma", "emma@odisee.be", null));
        // Verify
        verify(userRepository, never()).save(any(User.class));
        verify(passwordHasher, never()).hash(any());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "       ", "abcdefg", "1234567" })
    void registreer_ongeldigWachtwoord_geeftExceptionEnBewaartNietsEnBerekentGeenHash(String wachtwoord) {
        // Arrange
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.registreer("emma", "emma@odisee.be", wachtwoord));
        // Verify
        verify(userRepository, never()).save(any(User.class));
        verify(passwordHasher, never()).hash(any());
    }

    @ParameterizedTest
    @ValueSource(strings = { "        ", "abcdefgh", "12345678" })
    void registreer_geldigWachtwoord_geeftUserEnBewaartUser(String wachtwoord) {
        // Arrange
        // Act
        User emma = service.registreer("emma", "emma@odisee.be", wachtwoord);
        // Assert
        assertEquals("emma@odisee.be" , emma.getEmail());
        // Verify
        verify(userRepository).save(any(User.class));
    }

    @Test
    void registreer_registratieGeslaagd_klaarWachtwoordNietGelijkAanHashedWachtwoord() {
        // Arrange
        // Act
        User emma = service.registreer("emma", "emma@odisee.be", klaarWachtwoord);
        // Assert
        assertNotEquals(klaarWachtwoord, emma.getWachtwoordHash());
    }

    @Test
    void meldAan_onbekendEmailadres_geeftNullEnRoeptMatchesNietAan() {
        // Arrange
        when(userRepository.bestaatEmail("onbekend@odisee.be")).thenReturn(false);
        // Act
        // Assert
        assertNull(service.meldAan("onbekend@odisee.be", "wachtwoord"));
        verify(passwordHasher, never()).matches(any(), any());
    }

    @Test
    void meldAan_foutWachtwoord_geeftNull() {
        // Arrange
        when(userRepository.bestaatEmail("emma@odisee.be")).thenReturn(true);
        when(userRepository.findByEmail("emma@odisee.be")).thenReturn(new User("emma", "emma@odisee.be", klaarWachtwoord));
        // Act
        // Assert
        assertNull(service.meldAan("emma@odisee.be", "foutWachtwoord"));
    }

    @Test
    void meldAan_juistWachtwoord_geeftUser() {
        // Arrange
        when(userRepository.bestaatEmail("emma@odisee.be")).thenReturn(true);
        when(userRepository.findByEmail("emma@odisee.be")).thenReturn(new User("emma", "emma@odisee.be", klaarWachtwoord));
        when(user.getWachtwoordHash()).thenReturn(klaarWachtwoord);
        // Act
        // Assert
        assertSame(User.class, service.meldAan("emma@odisee.be", klaarWachtwoord).getClass());
    }
}
