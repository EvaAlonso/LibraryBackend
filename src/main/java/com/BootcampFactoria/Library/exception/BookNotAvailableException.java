package com.BootcampFactoria.Library.exception;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(String bookTitle) {
        super("Book" + bookTitle + "is not available");
    }
}
