package com.berru.app.elearningmanagementsystem.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategorySaveFailedException extends BaseException {

    private static final long serialVersionUID = 1L;

    public CategorySaveFailedException(String message) {
        super(message);
    }

    public CategorySaveFailedException(String message, List<ErrorDetailDto> errors) {
        super(message, errors);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}