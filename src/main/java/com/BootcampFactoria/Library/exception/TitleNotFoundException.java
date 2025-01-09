package com.BootcampFactoria.Library.exception;

public class TitleNotFoundException extends RuntimeException {
    public TitleNotFoundException(String title) {
        super("Could not find book with title: " + title);
    }
}
