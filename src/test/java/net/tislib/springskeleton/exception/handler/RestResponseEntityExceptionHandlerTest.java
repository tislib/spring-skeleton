package net.tislib.springskeleton.exception.handler;


import net.tislib.springskeleton.exception.ErrorMessageResponse;
import net.tislib.springskeleton.exception.ErrorMessageResponse.FieldError;
import net.tislib.springskeleton.resource.CountryResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RestResponseEntityExceptionHandlerTest {

    @InjectMocks
    RestResponseEntityExceptionHandler exceptionHandler;

    @Test
    public void shouldHandleConstraintViolationException() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        CountryResource countryResource = new CountryResource();
        countryResource.setName("test-name");
        ConstraintViolationException ex = new ConstraintViolationException(validator.validate(countryResource));

        ResponseEntity<Object> result = exceptionHandler.handleConstraintViolationException(ex);

        ErrorMessageResponse expectedBody = new ErrorMessageResponse();
        expectedBody.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());

        // timestamp is dynamic field, no need to check its value
        expectedBody.setTimestamp(
                ((ErrorMessageResponse) (Objects.requireNonNull(result.getBody()))).getTimestamp()
        );
        // message is provided from exception itself
        expectedBody.setMessage(
                ((ErrorMessageResponse) (Objects.requireNonNull(result.getBody()))).getMessage()
        );

        List<FieldError> errors = new ArrayList<>();
        errors.add(new FieldError("iso3Code", "must not be null"));
        errors.add(new FieldError("isoCode", "must not be null"));
        errors.add(new FieldError("nationality", "must not be null"));
        expectedBody.setErrors(errors);
        expectedBody.setException("ConstraintViolationException");

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, result.getStatusCode());
        assertEquals(expectedBody, result.getBody());
    }

}