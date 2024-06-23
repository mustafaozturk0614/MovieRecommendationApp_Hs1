package com.bilgeadam.validation;

import com.bilgeadam.repository.AuthRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final AuthRepository authRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return !authRepository.existsByEmail(value);
    }
}
