package com.openwt.boats.exception;

import com.openwt.boats.error.UserError;

public class NotAuthorizedException extends Exception {

    public NotAuthorizedException(UserError error) {
        super(error.getDescription());
    }

}