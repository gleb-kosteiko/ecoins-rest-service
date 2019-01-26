package com.edmunds.ecoins.restservice.validation;

import com.edmunds.ecoins.restservice.model.Role;
import com.edmunds.ecoins.restservice.model.User;
import com.edmunds.ecoins.restservice.model.UserConfig;
import com.edmunds.ecoins.restservice.service.UserConfigService;
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
public class UserConfigConditionsValidator implements ConstraintValidator<UserConfigValid, Object[]> {
    @Autowired
    private UserService userService;
    @Autowired
    private UserConfigService userConfigService;

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        String id = (String) value[0];
        Principal principal = (Principal) value[1];

        UserConfig oldUserConfig = userConfigService.findById(id);
        if (oldUserConfig == null) {
            throw new NoSuchElementException("{\"error\":\"There is no userConfig with such ID.\"}");
        }
        User currentUser = userService.findOne(principal.getName());
        if (currentUser.getRole() != Role.ADMIN && !oldUserConfig.getUserId().equals(currentUser.getId())) {
            throw new UserPermissionException();
        }
        return true;
    }
}
