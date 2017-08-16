package com.openwt.boats.controller;


import com.openwt.boats.dto.boat.BoatRegisterRequestDto;
import com.openwt.boats.dto.boat.BoatRegisterResponseDto;
import com.openwt.boats.dto.boat.BoatResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;


@RestController
@RequestMapping(path = "/api")
public interface BoatController {

    @RequestMapping(path = "/update_boat", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    BoatRegisterResponseDto updateBoat(@RequestHeader UUID authToken,BoatRegisterRequestDto boatRegisterRequestDto) throws Exception;

    @RequestMapping(path = "/get_boats/{userId}", method = RequestMethod.GET,  produces = "application/json")
    ArrayList<BoatResponseDto> getBoats(@RequestHeader UUID authToken,@PathVariable Long userId) throws Exception;


    @RequestMapping(path = "/get_boat/{boatId}", method = RequestMethod.GET, produces = "application/json")
    BoatResponseDto getBoat(@PathVariable Long boatId) throws Exception;

    @RequestMapping(path = "/delete_boat/{boatId}/{userId}", method = RequestMethod.DELETE, produces = "application/json")
    BoatRegisterResponseDto deleteBoat(@RequestHeader UUID authToken,@PathVariable Long boatId,@PathVariable Long userId) throws Exception;

}
