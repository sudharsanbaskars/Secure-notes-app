package com.project.secure.notes.exceptions;

import com.project.secure.notes.dtos.APIResponse;
import com.project.secure.notes.models.exception.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<APIResponse> handleAllExceptions(Exception ex, WebRequest request) {
        APIResponse errorResponse = buildErrorResponse(ex, request);
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<APIResponse> handleNotFoundExceptions(Exception ex, WebRequest request) {
        APIResponse errorResponse = buildErrorResponse(ex, request);
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<APIResponse> handleIllegalArgumentExceptions(Exception ex, WebRequest request) {
        APIResponse errorResponse = buildErrorResponse(ex, request);
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private APIResponse buildErrorResponse(Exception ex, WebRequest request) {
        return new APIResponse.APIResponseBuilder()
                .setDetails(new ErrorDetails(ex.getMessage()))
                .setSuccess(false)
                .setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setPath(request.getDescription(false))
                .build();
    }

}
