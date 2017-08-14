package com.openwt.boats.controller;

import com.openwt.boats.dao.BoatRepository;
import com.openwt.boats.dto.boat.BoatRegisterRequestDto;
import com.openwt.boats.dto.boat.BoatRegisterResponseDto;
import com.openwt.boats.dto.boat.BoatResponseDto;
import com.openwt.boats.entity.Boat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BoatControllerImpl implements BoatController{
    @Autowired
    BoatRepository repository;

    public BoatRegisterResponseDto registerBoat(@RequestHeader UUID authToken,@RequestBody BoatRegisterRequestDto boatRegisterRequestDto) throws Exception {

        Boat boatEntity= new Boat(boatRegisterRequestDto.getName(),
                boatRegisterRequestDto.getDescription(),
                boatRegisterRequestDto.getWeight(),
                boatRegisterRequestDto.getCreateDate());
        repository.save(boatEntity);
        BoatRegisterResponseDto response = new BoatRegisterResponseDto(HttpStatus.OK,"Boat is registered succesfully");
        return  response;
    }

    @Override
    public BoatRegisterResponseDto updateBoat(@RequestBody BoatRegisterRequestDto boatRegisterRequestDto) throws Exception {

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
    public ArrayList<BoatResponseDto> getBoats(@RequestBody BoatRegisterRequestDto boatRegisterRequestDto) throws Exception {
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
      return boatDto;
    }

    @Override
    public BoatRegisterResponseDto deleteBoat(@PathVariable Long boatId) throws Exception {
        repository.delete(boatId);
        BoatRegisterResponseDto response = new BoatRegisterResponseDto(HttpStatus.OK,"Boat is deleted succesfully");
        return  response;
    }


}
