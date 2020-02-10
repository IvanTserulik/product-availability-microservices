package edu.itserulik.product.controller;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import edu.itserulik.product.exception.NotAvailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler
    protected void handleFeedbackException(HystrixRuntimeException e) {
        log.error("hystrix : {}", e.getMessage());
        throw new NotAvailableException();
    }

}
