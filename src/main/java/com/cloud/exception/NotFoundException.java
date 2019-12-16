package com.cloud.exception;

import lombok.Getter;

/**
 * This is a common runtime exception class for Not Found Errord (400)
 * Accepts error code and error message
 *
 */
@Getter
public class NotFoundException extends RuntimeException {
    private final String code;

    public NotFoundException(String msg, String code) {
        super(msg);
        this.code = code;
    }
}
