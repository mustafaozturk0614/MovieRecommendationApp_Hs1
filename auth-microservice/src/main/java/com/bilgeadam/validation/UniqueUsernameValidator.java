package com.bilgeadam.validation;

import com.bilgeadam.repository.AuthRepository;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final AuthRepository authRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return !authRepository.existsByUsername(value);
    }
}
