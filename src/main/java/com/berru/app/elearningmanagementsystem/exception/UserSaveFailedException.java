package com.berru.app.elearningmanagementsystem.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class UserSaveFailedException extends BaseException {

    public UserSaveFailedException(String message) {
        super(message);
    }

    public UserSaveFailedException(String message, List<ErrorDetailDto> errors) {
        super(message, errors);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;  // HTTP Status 500
    }
}
