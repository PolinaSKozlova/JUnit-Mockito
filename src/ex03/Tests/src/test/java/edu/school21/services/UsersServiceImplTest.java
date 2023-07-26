package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsersServiceImplTest {
    private UsersServiceImpl usersService;
    private UsersRepository usersRepository;
    User userSusan, userMichaele, userBob, userSilvia;

    @BeforeEach
    void init() {
        usersRepository = Mockito.mock(UsersRepository.class);
        usersService = new UsersServiceImpl(usersRepository);
        userSusan = new User(1, "Susan",
                "123456", false);
        userBob = new User(2, "Bob",
                "9847bnds", false);
        userMichaele = new User(5, "Michaele",
                "hUfgd12", false);
        userSilvia = new User(6, "Silvia",
                "jsfFJshan", true);
    }

    @Test
    @DisplayName("Test authenticate Correct login/password ")
    void authenticateCorrect()
            throws EntityNotFoundException, AlreadyAuthenticatedException {
        Mockito.when(usersRepository.findByLogin("Susan"))
                .thenReturn(userSusan);
        Mockito.when(usersRepository.findByLogin("Bob"))
                .thenReturn(userBob);
        Mockito.when(usersRepository.findByLogin("Silvia"))
                .thenReturn(userSilvia);
        assertEquals(true, usersService
                .authenticate("Susan", "123456"));
        assertEquals(true, usersService
                .authenticate("Bob", "9847bnds"));
        assertThrows(AlreadyAuthenticatedException.class,
                () -> usersService.authenticate("Bob",
                        "9847bnds"));
        assertThrows(AlreadyAuthenticatedException.class,
                () -> usersService.authenticate("Silvia",
                        "jsfFJshan"));
    }

    @Test
    @DisplayName("Test authenticate Incorrect login")
    void authenticateIncorrectLogin() throws EntityNotFoundException {
        Mockito.when(usersRepository.findByLogin("Michael"))
                .thenThrow(EntityNotFoundException.class);
        Mockito.when(usersRepository.findByLogin("Ken"))
                .thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> usersService
                .authenticate("Michael", "hUfgd12"));
        assertThrows(EntityNotFoundException.class, () -> usersService
                .authenticate("Ken", "333333"));
    }

    @Test
    @DisplayName("Test authenticate Incorrect password")
    void authenticateIncorrectPassword()
            throws EntityNotFoundException, AlreadyAuthenticatedException {
        Mockito.when(usersRepository.findByLogin("Michaele"))
                .thenReturn(userMichaele);
        Mockito.when(usersRepository.findByLogin("Bob"))
                .thenReturn(userMichaele);
        assertEquals(false, usersService
                .authenticate("Michaele", "hUfgd"));
        assertEquals(false, usersService
                .authenticate("Bob", "hUfgd"));
    }
}
