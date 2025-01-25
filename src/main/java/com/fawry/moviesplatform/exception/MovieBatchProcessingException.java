package com.fawry.moviesplatform.exception;

public class MovieBatchProcessingException extends RuntimeException {
    public MovieBatchProcessingException(String message) {
        super(message);
    }

    public MovieBatchProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
} 