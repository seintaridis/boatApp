package com.openwt.boats.validation.validatorWrapper;

import com.openwt.boats.dto.user.UserLogInRequestDto;
import com.openwt.boats.dto.user.UserSignUpRequestDto;
import com.openwt.boats.exception.ValidationException;

public interface UserRequestValidatorWrapper {

    void validate(UserLogInRequestDto userLogInRequestDto) throws ValidationException;

    void validate(UserSignUpRequestDto userSignUpRequestDto) throws ValidationException;

}
