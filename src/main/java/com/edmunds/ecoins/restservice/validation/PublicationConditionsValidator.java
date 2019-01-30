package com.edmunds.ecoins.restservice.validation;

import com.edmunds.ecoins.restservice.model.Publication;
import com.edmunds.ecoins.restservice.model.Role;
import com.edmunds.ecoins.restservice.model.User;
import com.edmunds.ecoins.restservice.service.PublicationService;
import com.edmunds.ecoins.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.security.Principal;
import java.util.NoSuchElementException;

@Component
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class PublicationConditionsValidator implements ConstraintValidator<PublicationValid, Object[]> {
    @Autowired
    private UserService userService;
    @Autowired
    private PublicationService publicationService;

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        String id = (String) value[0];
        Principal principal = (Principal) value[1];

        Publication processedPublication = publicationService.findById(id);
        if (processedPublication == null) {
            throw new NoSuchElementException("{\"error\":\"There is no publication with such ID.\"}");
        }
        User currentUser = userService.findByUsername(principal.getName());
        if (currentUser.getRole() != Role.ADMIN && !processedPublication.getUserId().equals(currentUser.getId())) {
            throw new UserPermissionException();
        }
        return true;
    }
}
