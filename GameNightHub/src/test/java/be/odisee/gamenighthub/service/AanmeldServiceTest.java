package be.odisee.gamenighthub.service;

import be.odisee.gamenighthub.model.User;
import be.odisee.gamenighthub.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AanmeldServiceTest {

    private UserRepository userRepository;
    private PasswordHasher passwordHasher;
    private AanmeldService service;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordHasher = mock(PasswordHasher.class);
        service = new AanmeldService(userRepository, passwordHasher);
    }

    @Test
    void registreer_emailAlInGebruik_gooitExceptionEnBewaartNiets() {
        // Arrange
        when(userRepository.bestaatEmail("emma@odisee.be")).thenReturn(true);
        // Assert(Act)
        assertThrows(IllegalArgumentException.class,
                () -> service.registreer("Emma", "emma@odisee.be", "password"));
        // Verify
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registreer_nieuweGebruiker_bewaartGebruikerMetGehashtWachtwoord() {
        // Arrange
        when(passwordHasher.hash("wachtwoord")).thenReturn("hashed");
        // Act
        service.registreer("Noah", "noah@odisee.be", "wachtwoord");
        // Verify
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        // Assert
        User bewaard = captor.getValue();
        assertAll(
                () -> assertEquals("Noah", bewaard.getNaam()),
                () -> assertEquals("noah@odisee.be", bewaard.getEmail()),
                () -> assertEquals("hashed", bewaard.getWachtwoordHash())
        );

    }
}
