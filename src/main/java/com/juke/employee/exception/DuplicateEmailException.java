package com.juke.employee.exception;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String message) {
        super(message);
    }
}
