package edu.school21.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException() {
        super("User not found");
    }
}
