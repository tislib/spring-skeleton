package com.timesoft.hr.employeedata.exception.handler;

import com.timesoft.hr.employeedata.exception.DisallowedUpdateException;
import com.timesoft.hr.employeedata.exception.ErrorMessageResponse;
import com.timesoft.hr.employeedata.exception.ErrorMessageResponse.FieldError;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorMessageResponse errorMessageResponse = getErrorMessageResponseFromThrowable(ex);
        errorMessageResponse.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());

        errorMessageResponse.setErrors(
                ex.getConstraintViolations().stream()
                        .map(this::constraintViolationToFieldError)
                        .sorted(Comparator.comparing(FieldError::getFieldPath))
                        .collect(Collectors.toList())
        );

        return processErrorMessage(errorMessageResponse);
    }

    @ExceptionHandler(value = DisallowedUpdateException.class)
    protected ResponseEntity<Object> handleUnallowedUpdateException(DisallowedUpdateException ex) {
        ErrorMessageResponse errorMessageResponse = getErrorMessageResponseFromThrowable(ex);
        errorMessageResponse.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());

        errorMessageResponse.setErrors(
                ex.getFields().stream()
                        .map(field -> new FieldError(field, "update not allowed on this field"))
                        .collect(Collectors.toList())
        );

        return processErrorMessage(errorMessageResponse);
    }

    private FieldError constraintViolationToFieldError(ConstraintViolation<?> item) {
        FieldError fieldError = new FieldError();
        fieldError.setFieldPath(item.getPropertyPath().toString());
        fieldError.setMessage(item.getMessage());
        return fieldError;
    }


    @ExceptionHandler(value = Throwable.class)
    protected ResponseEntity<Object> handleGenericErrors(Throwable throwable) {
        return processException(throwable);
    }

    private ResponseEntity<Object> processException(Throwable throwable) {
        log.warn(throwable);

        ErrorMessageResponse errorMessageResponse = getErrorMessageResponseFromThrowable(throwable);
        errorMessageResponse.setStatusCode(500);

        return processErrorMessage(errorMessageResponse);
    }

    private ErrorMessageResponse getErrorMessageResponseFromThrowable(Throwable throwable) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
        errorMessageResponse.setException(throwable.getClass().getSimpleName());
        errorMessageResponse.setMessage(throwable.getMessage());
        errorMessageResponse.setTimestamp(new Date());
        return errorMessageResponse;
    }

    private ResponseEntity<Object> processErrorMessage(ErrorMessageResponse errorMessageResponse) {
        return ResponseEntity.status(errorMessageResponse.getStatusCode())
                .body(errorMessageResponse);
    }


}