package com.openwt.boats.controller;

import com.openwt.boats.authentication.Authenticator;
import com.openwt.boats.dao.BoatRepository;
import com.openwt.boats.dao.UserRepository;
import com.openwt.boats.dto.boat.BoatRegisterRequestDto;
import com.openwt.boats.dto.boat.BoatRegisterResponseDto;
import com.openwt.boats.dto.boat.BoatResponseDto;
import com.openwt.boats.entity.Boat;
import com.openwt.boats.entity.User;
import com.openwt.boats.error.UserError;
import com.openwt.boats.exception.NotAuthorizedException;
import com.openwt.boats.session.SessionInfo;
import com.openwt.boats.validation.boat.BoatRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BoatControllerImpl implements BoatController{
    @Autowired
    BoatRepository repository;

    @Autowired
    private Authenticator authenticator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoatRequestValidator boatRequestValidator;


    @Override
    public BoatRegisterResponseDto updateBoat(@RequestHeader UUID authToken,@RequestBody BoatRegisterRequestDto boatRegisterRequestDto) throws Exception {

        //Get Active Session
        SessionInfo sessionInfo = authenticator.checkUpdateSession(authToken);
        User user = userRepository.findUsersById(boatRegisterRequestDto.getUserId());
        //Validate Authorization
        if (!user.getId().equals(sessionInfo.getUserId()))
            throw new NotAuthorizedException(UserError.UNAUTHORIZED);

        boatRequestValidator.validate(boatRegisterRequestDto);

        Boat boatEntity;
        if (boatRegisterRequestDto.getId()==null)
        {
            boatEntity=new Boat();
            boatEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        }
        else
        {
            boatEntity = repository.findBoatsById(boatRegisterRequestDto.getId()).get(0);
        }

        boatEntity.setOwner(boatRegisterRequestDto.getOwner());
        boatEntity.setName(boatRegisterRequestDto.getName());
        boatEntity.setDescription(boatRegisterRequestDto.getDescription());
        boatEntity.setWeight(boatRegisterRequestDto.getWeight());
        repository.save(boatEntity);
        BoatRegisterResponseDto response = new BoatRegisterResponseDto(HttpStatus.OK,"Boat is registered succesfully");
        return response;


    }

    @Override
    public ArrayList<BoatResponseDto> getBoats(@RequestHeader UUID authToken,@PathVariable Long userId) throws Exception {

        //Get Active Session
        SessionInfo sessionInfo = authenticator.checkUpdateSession(authToken);
        User user = userRepository.findUsersById(userId);
        //Validate Authorization
        if (!user.getId().equals(sessionInfo.getUserId()))
            throw new NotAuthorizedException(UserError.UNAUTHORIZED);

        ArrayList<BoatResponseDto> boatResponseDtos= new ArrayList<>();
        for(Boat boat : repository.findAll()){
            BoatResponseDto boatDto = new BoatResponseDto();
            boatDto.setName(boat.getName());
            boatDto.setDescription(boat.getDescription());
            boatDto.setCreateDate(boat.getCreateDate());
            boatDto.setWeight(boat.getWeight());
            boatDto.setId(boat.getId());
            boatDto.setOwner(boat.getOwner());
            boatResponseDtos.add(boatDto);
        }
        return boatResponseDtos;

    }

    @Override
    public BoatResponseDto getBoat(@PathVariable Long boatId) throws Exception {
      List<Boat> boats =repository.findBoatsById(boatId);
      Boat boatEntity = boats.get(0);
      BoatResponseDto boatDto = new BoatResponseDto();
      boatDto.setName(boatEntity.getName());
      boatDto.setDescription(boatEntity.getDescription());
      boatDto.setWeight(boatEntity.getWeight());
      boatDto.setId(boatEntity.getId());
      boatDto.setOwner(boatEntity.getOwner());
      return boatDto;
    }

    @Override
    public BoatRegisterResponseDto deleteBoat(@RequestHeader UUID authToken,@PathVariable Long boatId,@PathVariable Long userId) throws Exception {

        //Get Active Session
        SessionInfo sessionInfo = authenticator.checkUpdateSession(authToken);
        User user = userRepository.findUsersById(userId);
        //Validate Authorization
        if (!user.getId().equals(sessionInfo.getUserId()))
            throw new NotAuthorizedException(UserError.UNAUTHORIZED);

        repository.delete(boatId);
        BoatRegisterResponseDto response = new BoatRegisterResponseDto(HttpStatus.OK,"Boat is deleted succesfully");
        return  response;
    }


}
