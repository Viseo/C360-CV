package com.viseo.c360.cv.controllers;

import com.viseo.c360.cv.converters.MissionEntityToDtoConverter;
import com.viseo.c360.cv.models.dto.MissionDto;
import com.viseo.c360.cv.models.entities.MissionEntity;
import com.viseo.c360.cv.services.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @RequestMapping(method = POST)
    public MissionDto add(@RequestBody MissionEntity missionEntity) {
        return new MissionEntityToDtoConverter().convert(this.missionService.add(missionEntity));
    }

    @RequestMapping(method = PUT)
    public MissionDto update(@RequestBody MissionEntity missionEntity) {
        return new MissionEntityToDtoConverter().convert(this.missionService.update(missionEntity));
    }

    @RequestMapping( method = DELETE)
    public boolean delete(@RequestParam int userId, @RequestParam int missionId) {
        return this.missionService.delete(userId,missionId);
    }

    @RequestMapping( method = GET)
    public List<MissionDto> getAll(int userId) {
        return new MissionEntityToDtoConverter().convert(this.missionService.getAll(userId));
    }

    @RequestMapping( path= "/{missionId}", method = GET)
    public MissionDto getById(@PathParam("missionId") int missionId, @RequestParam int userId) {
        return new MissionEntityToDtoConverter().convert(this.missionService.getById(userId, missionId));
    }
}

