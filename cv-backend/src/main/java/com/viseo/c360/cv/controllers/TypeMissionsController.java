package com.viseo.c360.cv.controllers;

import com.viseo.c360.cv.converters.TypeMissionEntityToDtoConverter;
import com.viseo.c360.cv.models.dto.TypeMissionsDto;
import com.viseo.c360.cv.services.TypeMissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by YGU3747 on 05/01/2018
 */
@RestController
@RequestMapping("/api/typeMissions")
public class TypeMissionsController {

    @Autowired
    private TypeMissionsService typeMissionsService;

    @CrossOrigin(origins =  "${server.front}")
    @RequestMapping( method = GET)
    public List<TypeMissionsDto> getAll() {
//        return new MissionEntityToDtoConverter().convert(this.missionService.getAll(userId));
        return new TypeMissionEntityToDtoConverter().convert(this.typeMissionsService.getAll());
    }
}
