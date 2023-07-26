package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl {
    private UsersRepository usersRepository;

    public boolean authenticate(String login, String password)
            throws AlreadyAuthenticatedException {
        try {
            User user = new User();
            user = usersRepository.findByLogin(login);
            if (user.getPassword() == password) {
                return true;
            } else {
                return false;
            }
        } catch (EntityNotFoundException e) {
            throw new AlreadyAuthenticatedException();
        }
    }
}
