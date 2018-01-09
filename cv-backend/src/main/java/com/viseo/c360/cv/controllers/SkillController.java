package com.viseo.c360.cv.controllers;

import com.viseo.c360.cv.models.entities.SkillEntity;
import com.viseo.c360.cv.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by YGU3747 on 09/01/2018
 */
@RestController
public class SkillController {
    @Autowired
    private SkillService skillService;

    @CrossOrigin(origins =  "${server.front}")
    @RequestMapping(path = "/api/skills", method = GET)
    public List<SkillEntity> getAll(){
        // to be improved after connecting C360 Competence
        return skillService.getAll();
    }
}
