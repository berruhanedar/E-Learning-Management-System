package com.berru.app.elearningmanagementsystem.exception;

public class NotFoundException extends BadRequestException {

    public NotFoundException(String message) {
        super(message);
    }
}