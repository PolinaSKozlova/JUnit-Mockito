package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl {
    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean authenticate(String login, String password)
            throws AlreadyAuthenticatedException, EntityNotFoundException {
        try {
            User user = usersRepository.findByLogin(login);
            if (user.getAuthenticationStatus() == true) {
                throw new AlreadyAuthenticatedException();
            } else {
                if (user.getPassword().equals(password)) {
                    user.setAuthenticationStatus(true);
                    usersRepository.update(user);
                    return true;
                } else {
                    return false;
                }
            }
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }
}
