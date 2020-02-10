package edu.itserulik.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class NotAvailableException extends RuntimeException {

    public NotAvailableException() {
        super("Service is not available");
    }
}
