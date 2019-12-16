package com.cloud.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * This is a common runtime exception class for internal server errors (500)
 * Accepts error code, error message and reason for exception
 *
 */
@Getter
@ToString
public class TechnicalErrorException extends RuntimeException {

    private final String code;

    public TechnicalErrorException(String msg, String code) {
        super(msg);
        this.code = code;
    }

}
