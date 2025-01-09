package com.BootcampFactoria.Library.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String authorName) {
        super("Could not find books by author: " + authorName);
    }
}
