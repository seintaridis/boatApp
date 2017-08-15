package com.openwt.boats.exception;

import com.openwt.boats.error.UserError;

public class ValidationException extends Exception {


    public ValidationException(UserError error) {
        super(error.getDescription());
    }


}