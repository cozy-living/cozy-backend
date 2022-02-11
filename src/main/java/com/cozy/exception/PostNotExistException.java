package com.cozy.exception;

public class PostNotExistException extends RuntimeException{
    public PostNotExistException(String message) {
        super(message);
    }
}
