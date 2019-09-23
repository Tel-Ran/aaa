package telran.security.dto.validators;

import org.springframework.beans.factory.annotation.Autowired;

import telran.security.services.AuthService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidatorImpl implements ConstraintValidator<UniqueUsernameValidator, String> {
    @Autowired
    private AuthService authService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !authService.isUsernameAlreadyInUse(value);
    }
}
