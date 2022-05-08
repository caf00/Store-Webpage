package com.alejandro.storewebpage.web.error;

import com.alejandro.storewebpage.app.exception.InvalidArgumentException;
import com.alejandro.storewebpage.app.exception.ResourceAlreadyExistException;
import com.alejandro.storewebpage.app.exception.ResourceNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class StoreWebPageApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleInvalidArgumentException(InvalidArgumentException ex, WebRequest webRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StoreWebPageApiError storeWebPageApiError = StoreWebPageApiError.createError(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage()
        );
        return super.handleExceptionInternal(ex, storeWebPageApiError.toMap(), new HttpHeaders(), status, webRequest);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StoreWebPageApiError storeWebPageApiError = StoreWebPageApiError.createError(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage()
        );
        return super.handleExceptionInternal(ex, storeWebPageApiError.toMap(), new HttpHeaders(), status, webRequest);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleResourceAlreadyExitException(ResourceAlreadyExistException ex, WebRequest webRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StoreWebPageApiError storeWebPageApiError = StoreWebPageApiError.createError(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage()
        );
        return super.handleExceptionInternal(ex, storeWebPageApiError.toMap(), new HttpHeaders(), status, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "Some parameters have incorrect format";
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        StoreWebPageApiError storeWebPageApiError = StoreWebPageApiError.createError(
                status.value(),
                status.getReasonPhrase(),
                message,
                errors
        );
        return super.handleExceptionInternal(ex, storeWebPageApiError.toMap(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StoreWebPageApiError storeWebPageApiError = StoreWebPageApiError.createError(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage()
        );
        return super.handleExceptionInternal(ex, storeWebPageApiError.toMap(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "The provided parameter or the collection element is of the wrong type";
        StoreWebPageApiError storeWebPageApiError = StoreWebPageApiError.createError(
                status.value(),
                status.getReasonPhrase(),
                message
        );
        return super.handleExceptionInternal(ex, storeWebPageApiError.toMap(), headers, status, request);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleUnexpectedException(Exception ex, WebRequest webRequest) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Unexpected exception";
        StoreWebPageApiError storeWebPageApiError = StoreWebPageApiError.createError(
                status.value(),
                status.getReasonPhrase(),
                message
        );
        return super.handleExceptionInternal(ex, storeWebPageApiError.toMap(), new HttpHeaders(), status, webRequest);
    }
}