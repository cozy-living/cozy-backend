package com.cozy.exception;

public class CommentNotExistException extends RuntimeException{
    public CommentNotExistException(String message) {
        super(message);
    }
}
