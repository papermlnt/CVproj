package com.cvproject.cvProject.exception;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
