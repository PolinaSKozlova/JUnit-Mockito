package edu.school21.services;

import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UsersServiceImplTest {
    @Test
    @DisplayName("Test authenticate Correct login/password ")
    void authenticateCorrect() {
        UsersRepository usersRepository =
                Mockito.mock(UsersRepository.class);
    }

    @Test
    @DisplayName("Test authenticate Incorrect login")
    void authenticateIncorrectLogin() {
        UsersRepository usersRepository =
                Mockito.mock(UsersRepository.class);
    }

    @Test
    @DisplayName("Test authenticate Incorrect password")
    void authenticateIncorrectPassword() {
        UsersRepository usersRepository =
                Mockito.mock(UsersRepository.class);
    }
}
