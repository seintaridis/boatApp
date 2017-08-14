package com.openwt.boats.error;


public enum UserError {
    USER_DOES_NOT_EXIST("User does not exist."),
    INVALID_DATA("Invalid data."),
    MISSING_DATA("Missing data."),
    USER_ALREADY_VERIFIED("User is already verified."),
    UNAUTHORIZED("Unauthorized user."),
    NOT_AUTHENTICATED("User is not authenticated."),
    EMAIL_ALREADY_IN_USE("Email is already in use."),
    NEW_PASSWORD_DO_NOT_DIFFER("New password must differ with the old password."),
    PASSWORD_MISSMATCH("Password is invalid.");


    private final String description;

    UserError(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

