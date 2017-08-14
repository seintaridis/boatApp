package com.openwt.boats.controller;

import com.openwt.boats.authentication.Authenticator;
import com.openwt.boats.dao.UserRepository;
import com.openwt.boats.dto.user.UserLogInRequestDto;
import com.openwt.boats.dto.user.UserLogInResponseDto;
import com.openwt.boats.dto.user.UserSignUpRequestDto;
import com.openwt.boats.dto.user.UserSignUpResponseDto;
import com.openwt.boats.entity.User;
import com.openwt.boats.error.UserError;
import com.openwt.boats.exception.NotAuthorizedException;
import com.openwt.boats.session.SessionInfo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.UUID;


@Component
public class UserControllerImpl implements UserController {
    @Autowired
    UserRepository repository;

    @Autowired
    private Authenticator authenticator;

    public UserLogInResponseDto login(@RequestBody UserLogInRequestDto userLogInRequestDto){
        List<User> a= repository.findUsersByUserNameAndPassword(userLogInRequestDto.getUsername(),userLogInRequestDto.getPassword());
        User user = a.get(0);
        SessionInfo session = new SessionInfo(user.getId(), DateTime.now().plusMinutes(Authenticator.SESSION_TIME_OUT_MINUTES));
        UUID authToken = authenticator.createSession(session);
        UserLogInResponseDto userLogInResponseDto = new UserLogInResponseDto();
        userLogInResponseDto.setUserId(user.getId());
        userLogInResponseDto.setAuthToken(authToken);
        userLogInResponseDto.setFirstName(user.getFirstName());
        userLogInResponseDto.setLastName(user.getLastName());

        return userLogInResponseDto;
    }

    @Override  //to thelw
    public UserSignUpResponseDto signUp(@RequestBody UserSignUpRequestDto userSignUpRequestDto) throws Exception {

        User userEntity= new User(userSignUpRequestDto.getFirstname(),
                                   userSignUpRequestDto.getLastname(),
                                    userSignUpRequestDto.getUsername(),
                                    userSignUpRequestDto.getPassword());
        repository.save(userEntity);
        UserSignUpResponseDto response = new UserSignUpResponseDto(HttpStatus.OK,"User is registered succesfully");
        return  response;
    }

    @Override
    public UserSignUpResponseDto validateUser(@RequestHeader UUID authToken,@PathVariable Long userId) throws Exception {
        //Get Active Session
        SessionInfo sessionInfo = authenticator.checkUpdateSession(authToken);
        User user = repository.findUsersById(userId);
        //Validate Authorization
        if (!userId.equals(sessionInfo.getUserId()))
            throw new NotAuthorizedException(UserError.UNAUTHORIZED);

        UserSignUpResponseDto response = new UserSignUpResponseDto(HttpStatus.OK,"User is authorized");
        return  response;
    }


}
