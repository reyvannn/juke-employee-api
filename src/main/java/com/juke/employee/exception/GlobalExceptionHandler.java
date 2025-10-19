package com.juke.employee.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Resource not found");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setType(URI.create("/errors/not-found"));
        return problemDetail;
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ProblemDetail handleDuplicateEmailException(DuplicateEmailException e, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Duplicate email");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setType(URI.create("/errors/duplicate-email"));
        return problemDetail;
    }
}
