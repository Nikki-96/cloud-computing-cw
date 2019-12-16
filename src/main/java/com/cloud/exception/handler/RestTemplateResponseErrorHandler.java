package com.cloud.exception.handler;

import com.cloud.exception.InvalidRequestException;
import com.cloud.exception.NotFoundException;
import com.cloud.exception.TechnicalErrorException;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

/**
 * This is a common runtime exception Handler class to handle the response errors
 * Accepts error code and error message
 */
@Component
@Getter
@ToString
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (httpResponse.getStatusCode().series() == CLIENT_ERROR || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {

        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            throw new TechnicalErrorException(httpResponse.getStatusText(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            // TODO - Handle more client errors
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException( httpResponse.getStatusText(), String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
            throw new InvalidRequestException(String.valueOf(HttpStatus.BAD_REQUEST.value()), httpResponse.getStatusText());
        }
    }
}
