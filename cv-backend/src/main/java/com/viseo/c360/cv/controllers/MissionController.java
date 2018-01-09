package com.viseo.c360.cv.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viseo.c360.cv.converters.MissionEntityToDtoConverter;
import com.viseo.c360.cv.converters.UserEntityToDtoConverter;
import com.viseo.c360.cv.models.dto.MissionDto;
import com.viseo.c360.cv.models.entities.MissionEntity;
import com.viseo.c360.cv.models.entities.UsersEntity;
import com.viseo.c360.cv.services.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @Autowired
    private AccountController accountController;

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(method = POST)
    public MissionDto add(@RequestBody MissionEntity missionEntity, @RequestParam("userId") int userId) {
        UsersEntity user = accountController.getUser((userId));
        MissionDto missionDto = new MissionEntityToDtoConverter().convert(this.missionService.add(missionEntity));
        List<MissionEntity> missions = user.getMissions();
        missions.add(missionEntity);
        user.setMissions(missions);
        accountController.updateUser(new UserEntityToDtoConverter().convert(user));
        return missionDto;
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(method = PUT)
    public MissionDto update(@RequestBody MissionEntity missionEntity) {
        return new MissionEntityToDtoConverter().convert(this.missionService.update(missionEntity));
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping( method = DELETE)
    public void delete(@RequestParam int missionId) {
        this.missionService.delete(missionId);
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping( method = GET)
    public List<MissionDto> getAll(int userId) {
        return new MissionEntityToDtoConverter().convert(this.missionService.getAll(userId));
    }
//
//    @CrossOrigin (origins =  "${server.front}")
//    @RequestMapping( path= "/{missionId}", method = GET)
//    public MissionDto getById(@PathParam("missionId") int missionId, @RequestParam int userId) {
//        return new MissionEntityToDtoConverter().convert(this.missionService.getById(userId, missionId));
//    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(path = "/getMissionByUser", method = GET)
    public List<MissionDto> getMissionByUser(@RequestParam("userId") long userId){
        return new MissionEntityToDtoConverter().convert(this.missionService.getMissionsByUser(userId));
    }
}

