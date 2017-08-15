package com.openwt.boats.validation.user;

import com.openwt.boats.dto.user.UserLogInRequestDto;
import com.openwt.boats.dto.user.UserSignUpRequestDto;
import com.openwt.boats.exception.ValidationException;
import com.openwt.boats.validation.validatorWrapper.UserRequestValidatorWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRequestValidator implements UserRequestValidatorWrapper {

    @Autowired
    private UserLogInValidator userLogInValidator;

    @Autowired
    private UserSignUpValidator userSignUpValidator;

    @Override
    public void validate(UserLogInRequestDto userLogInRequestDto) throws ValidationException {
        userLogInValidator.validate(userLogInRequestDto);
    }

    @Override
    public void validate(UserSignUpRequestDto userSignUpRequestDto) throws ValidationException {
        userSignUpValidator.validate(userSignUpRequestDto);
    }
}
