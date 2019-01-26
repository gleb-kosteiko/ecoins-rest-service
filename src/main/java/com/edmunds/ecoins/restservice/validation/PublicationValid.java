package com.edmunds.ecoins.restservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PublicationConditionsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PublicationValid {

    String message() default "Some conditions of the action are not valid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
