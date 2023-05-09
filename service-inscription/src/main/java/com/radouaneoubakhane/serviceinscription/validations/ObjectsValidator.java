package com.radouaneoubakhane.serviceinscription.validations;

import com.radouaneoubakhane.serviceinscription.exception.ObjectNotValidException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class ObjectsValidator<T> {
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    public void validate(T objectToValidate) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(objectToValidate);

        if (!constraintViolations.isEmpty()) {
            Map<String, String> errorsMessages = constraintViolations.stream()
                    .collect(Collectors.toMap(
                            constraintViolation -> constraintViolation.getPropertyPath().toString(),
                            ConstraintViolation::getMessage
                    ));

            throw new ObjectNotValidException(errorsMessages);
        }
    }
}
