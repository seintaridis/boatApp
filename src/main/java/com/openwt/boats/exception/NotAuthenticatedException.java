package com.openwt.boats.exception;

import com.openwt.boats.error.UserError;

public class NotAuthenticatedException extends Exception {

    public NotAuthenticatedException(UserError error) {
        super(error.getDescription());
    }



}

