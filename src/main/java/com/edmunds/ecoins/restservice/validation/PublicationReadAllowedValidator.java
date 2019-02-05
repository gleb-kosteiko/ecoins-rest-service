package com.edmunds.ecoins.restservice.validation;

import com.edmunds.ecoins.restservice.model.Publication;
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

import static com.edmunds.ecoins.restservice.model.Role.ADMIN;

@Component
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class PublicationReadAllowedValidator implements ConstraintValidator<PublicationReadAllowed, Object[]> {

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
        if (processedPublication.getIsPublished() != null
                && processedPublication.getIsPublished()) {
            return true;
        } else {
            if (principal == null) {
                throw new UserPermissionException();
            }
            User currentUser = userService.findByUsername(principal.getName());
            if ((!currentUser.getId().equals(processedPublication.getUserId())
                    || !currentUser.getRole().equals(ADMIN))) {
                throw new UserPermissionException();
            }
        }
        return true;
    }
}
