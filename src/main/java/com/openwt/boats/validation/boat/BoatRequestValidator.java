package com.openwt.boats.validation.boat;

import com.openwt.boats.dto.boat.BoatRegisterRequestDto;
import com.openwt.boats.exception.ValidationException;
import com.openwt.boats.validation.validatorWrapper.BoatRequestValidatorWrapper;
import org.springframework.stereotype.Component;

@Component
public class BoatRequestValidator implements BoatRequestValidatorWrapper{
    @Override
    public void validate(BoatRegisterRequestDto boatRegisterRequestDto) throws ValidationException {

    }

  
}
