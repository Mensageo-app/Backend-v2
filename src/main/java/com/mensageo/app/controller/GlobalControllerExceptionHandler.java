package com.mensageo.app.controller;

import com.mensageo.app.controller.errortypes.CustomErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    ResponseEntity<?> createJSONInformationOnErrorResponse(HttpServletRequest request, ResponseStatusException ex) {
        HttpStatus status = ex.getStatus();
        return new ResponseEntity<>(new CustomErrorType(status.value(), ex.getReason()), status);
    }

}
