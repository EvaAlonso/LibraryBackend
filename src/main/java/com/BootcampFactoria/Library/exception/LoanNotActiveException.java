package com.BootcampFactoria.Library.exception;

public class LoanNotActiveException extends RuntimeException {
    public LoanNotActiveException(String bookTitle) {
        super("the book" + bookTitle + "has been returned");
    }
}
