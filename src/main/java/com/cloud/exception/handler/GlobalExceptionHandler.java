package com.cloud.exception.handler;

import com.cloud.exception.InvalidRequestException;
import com.cloud.exception.NotFoundException;
import com.cloud.model.ExceptionResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Global Exception Handler class for all Rest Controller requests
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String ERROR_LOG_FORMAT = "incident Id : {}, exception : {}";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        String uuid = UUID.randomUUID().toString();
        log.error("System error - " + ERROR_LOG_FORMAT, uuid, ExceptionUtils.getMessage(exception), exception);

        ExceptionResponse apiError = ExceptionResponse.builder()
                .errorMessage(exception.getMessage())
                .errorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .timeStamp(LocalDateTime.now())
                .incidentId(uuid)
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        String uuid = UUID.randomUUID().toString();
        log.error(ERROR_LOG_FORMAT, uuid, ExceptionUtils.getMessage(e), e);
        ExceptionResponse apiError = ExceptionResponse.builder()
                .errorMessage(e.getMessage())
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .timeStamp(LocalDateTime.now())
                .incidentId(uuid)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String uuid = UUID.randomUUID().toString();
        String message = ex.getName() + " - " + ex.getMessage();
        log.error(ERROR_LOG_FORMAT, uuid, ex.getName() + " - " + ExceptionUtils.getMessage(ex), ex);
        ExceptionResponse apiError = ExceptionResponse.builder()
                .errorMessage(message)
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .timeStamp(LocalDateTime.now())
                .incidentId(uuid)
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex) {
        String uuid = UUID.randomUUID().toString();
        log.error(ERROR_LOG_FORMAT, uuid, ExceptionUtils.getMessage(ex), ex);

        ExceptionResponse apiError = ExceptionResponse.builder()
                .errorMessage(ex.getMessage())
                .errorCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .timeStamp(LocalDateTime.now())
                .incidentId(uuid)
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles RuntimeException.
     * @param ex -ParseException
     * @return ResponseEntity is returned
     */
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequestException(InvalidRequestException ex) {
        String uuid = UUID.randomUUID().toString();
        log.error(ERROR_LOG_FORMAT, uuid, ExceptionUtils.getMessage(ex), ex);

        ExceptionResponse apiError = ExceptionResponse.builder()
                .errorMessage(ex.getMessage())
                .errorCode(ex.getCode())
                .timeStamp(LocalDateTime.now())
                .incidentId(uuid)
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String uuid = UUID.randomUUID().toString();
        log.error(ERROR_LOG_FORMAT, uuid, ExceptionUtils.getMessage(ex), ex);

        ExceptionResponse apiError = ExceptionResponse.builder()
                .errorMessage(ex.getMessage())
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .timeStamp(LocalDateTime.now())
                .incidentId(uuid)
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {

        String uuid = UUID.randomUUID().toString();
        log.error(ERROR_LOG_FORMAT, uuid, ExceptionUtils.getMessage(ex), ex);

        ExceptionResponse apiError = ExceptionResponse.builder()
                .errorMessage(ex.getMessage())
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .timeStamp(LocalDateTime.now())
                .incidentId(uuid)
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        String uuid = UUID.randomUUID().toString();
        log.error(ERROR_LOG_FORMAT, uuid, ExceptionUtils.getMessage(ex), ex);

        ExceptionResponse apiError = ExceptionResponse.builder()
                .errorMessage(ex.getMessage())
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .timeStamp(LocalDateTime.now())
                .incidentId(uuid)
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
