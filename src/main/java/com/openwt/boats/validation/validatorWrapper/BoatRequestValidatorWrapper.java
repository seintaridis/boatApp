package com.openwt.boats.validation.validatorWrapper;

import com.openwt.boats.dto.boat.BoatRegisterRequestDto;
import com.openwt.boats.exception.ValidationException;

public interface BoatRequestValidatorWrapper {
    void validate(BoatRegisterRequestDto boatRegisterRequestDto) throws ValidationException;

}
