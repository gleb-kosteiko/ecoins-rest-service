package com.edmunds.ecoins.restservice.validation;

import com.edmunds.ecoins.restservice.model.User;
import com.edmunds.ecoins.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.security.Principal;

import static com.edmunds.ecoins.restservice.model.Role.ADMIN;

@Component
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class UserAccessAllowedValidator implements ConstraintValidator<UserAccessAllowed, Object[]> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        String userId = (String) value[0];
        Boolean isPublished = (Boolean) value[1];
        Principal principal = (Principal) value[2];
        if (isPublished != null && isPublished) {
            return true;
        } else if (principal != null) {
            User currentUser = userService.findByUsername(principal.getName());
            if (currentUser.getRole().equals(ADMIN) || currentUser.getId().equals(userId)) {
                return true;
            } else {
                throw new UserPermissionException();
            }
        } else {
            throw new UserPermissionException();
        }
    }
}
