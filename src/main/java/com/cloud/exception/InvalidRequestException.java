package com.cloud.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * This is a common runtime exception class for bad requests (400)
 * Accepts error code and error message
 *
 */
@Getter
@ToString
public class InvalidRequestException extends RuntimeException {

    private final String code;

    public InvalidRequestException(String errorMessage, String code) {
        super(errorMessage);
        this.code = code;
    }
}
