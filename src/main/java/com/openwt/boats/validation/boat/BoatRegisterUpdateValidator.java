package com.openwt.boats.validation.boat;

import com.openwt.boats.dto.boat.BoatRegisterRequestDto;
import com.openwt.boats.error.BoatError;

import com.openwt.boats.error.UserError;
import com.openwt.boats.exception.ValidationException;
import com.openwt.boats.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BoatRegisterUpdateValidator implements Validator<BoatRegisterRequestDto> {
    @Override
    public void validate(BoatRegisterRequestDto request) throws ValidationException {
        Optional.ofNullable(request).orElseThrow(() -> new ValidationException(BoatError.MISSING_DATA));
        Optional.ofNullable(request.getName()).orElseThrow(() -> new ValidationException(BoatError.MISSING_NAME));
        Optional.ofNullable(request.getDescription()).orElseThrow(() -> new ValidationException(BoatError.MISSING_DESCRIPTION));
        Optional.ofNullable(request.getOwner()).orElseThrow(() -> new ValidationException(BoatError.MISSING_OWNER));
        Optional.ofNullable(request.getWeight()).orElseThrow(() -> new ValidationException(BoatError.MISSING_WEIGHT));
    }
}
