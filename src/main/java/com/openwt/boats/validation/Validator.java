package com.openwt.boats.validation;

import com.openwt.boats.exception.ValidationException;

public interface Validator<T> {

    void validate(T request) throws ValidationException;

}
