package com.openwt.boats.controller;

import com.openwt.boats.authentication.Authenticator;
import com.openwt.boats.dao.UserRepository;
import com.openwt.boats.dto.user.UserLogInRequestDto;
import com.openwt.boats.dto.user.UserLogInResponseDto;
import com.openwt.boats.dto.user.UserSignUpRequestDto;
import com.openwt.boats.dto.user.GeneralResponseDto;
import com.openwt.boats.entity.User;
import com.openwt.boats.error.UserError;
import com.openwt.boats.exception.NotAuthenticatedException;
import com.openwt.boats.exception.NotAuthorizedException;
import com.openwt.boats.exception.ValidationException;
import com.openwt.boats.session.SessionInfo;
import com.openwt.boats.validation.user.UserRequestValidator;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
public class UserControllerImpl implements UserController {
    @Autowired
    UserRepository repository;

    @Autowired
    private Authenticator authenticator;

    @Autowired
    private UserRequestValidator userRequestValidator;

    public UserLogInResponseDto login(@RequestBody UserLogInRequestDto userLogInRequestDto) throws ValidationException, NotAuthenticatedException {
        userRequestValidator.validate(userLogInRequestDto);
        User user= repository.findUsersByUserNameAndPassword(userLogInRequestDto.getUsername(),userLogInRequestDto.getPassword());
        Optional.ofNullable(user).orElseThrow(() -> new NotAuthenticatedException(UserError.INVALID_CREDENTIALS));
        SessionInfo session = new SessionInfo(user.getId(), DateTime.now().plusMinutes(Authenticator.SESSION_TIME_OUT_MINUTES));
        UUID authToken = authenticator.createSession(session);
        UserLogInResponseDto userLogInResponseDto = new UserLogInResponseDto();
        userLogInResponseDto.setUserId(user.getId());
        userLogInResponseDto.setAuthToken(authToken);
        userLogInResponseDto.setFirstName(user.getFirstName());
        userLogInResponseDto.setLastName(user.getLastName());
        return userLogInResponseDto;
    }

    @Override
    public GeneralResponseDto signUp(@RequestBody UserSignUpRequestDto userSignUpRequestDto) throws Exception {
        userRequestValidator.validate(userSignUpRequestDto);
        User userEntity= new User(userSignUpRequestDto.getFirstname(),
                                   userSignUpRequestDto.getLastname(),
                                    userSignUpRequestDto.getUsername(),
                                    userSignUpRequestDto.getPassword());
        repository.save(userEntity);

        return new GeneralResponseDto(HttpStatus.OK,"User is registered succesfully");
    }

    @Override
    public GeneralResponseDto validateUser(@RequestHeader UUID authToken, @PathVariable Long userId) throws Exception {
        //Get Active Session
        SessionInfo sessionInfo = authenticator.checkUpdateSession(authToken);
        User user = repository.findUsersById(userId);
        //Validate Authorization
        if (!userId.equals(sessionInfo.getUserId()))
            throw new NotAuthorizedException(UserError.UNAUTHORIZED);

        GeneralResponseDto response = new GeneralResponseDto(HttpStatus.OK,"User is authorized");
        return  response;
    }


}
