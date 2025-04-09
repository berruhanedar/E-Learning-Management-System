package com.berru.app.elearningmanagementsystem.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, List<ErrorDetailDto> errors) {
        super(message, errors);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
