package com.openwt.boats.validation.user;

import com.openwt.boats.dto.user.UserSignUpRequestDto;
import com.openwt.boats.error.UserError;
import com.openwt.boats.exception.ValidationException;
import com.openwt.boats.validation.Validator;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class UserSignUpValidator implements Validator<UserSignUpRequestDto>{

    @Override
    public void validate(UserSignUpRequestDto request) throws ValidationException {
        Optional.ofNullable(request).orElseThrow(() -> new ValidationException(UserError.MISSING_DATA));
        Optional.ofNullable(request.getPassword()).orElseThrow(() -> new ValidationException(UserError.MISSING_FIRSTNAME));
        Optional.ofNullable(request.getUsername()).orElseThrow(() -> new ValidationException(UserError.MISSING_LASTNAME));
        Optional.ofNullable(request.getPassword()).orElseThrow(() -> new ValidationException(UserError.MISSING_PASSWORD));
        Optional.ofNullable(request.getUsername()).orElseThrow(() -> new ValidationException(UserError.MISSING_USERNAME));

        if (Stream.of(request.getUsername(), request.getPassword()).filter(Objects::nonNull).anyMatch(String::isEmpty))
            throw new ValidationException(UserError.INVALID_DATA);
    }
}
