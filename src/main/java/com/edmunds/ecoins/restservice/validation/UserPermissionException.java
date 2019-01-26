package com.edmunds.ecoins.restservice.validation;

public class UserPermissionException extends RuntimeException {
    private static final String ERROR_RESPONSE = "{\"error\":\"You don't have enough permissions to do that.\"}";

    public UserPermissionException() {
        super(ERROR_RESPONSE);
    }
}