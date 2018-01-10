package com.viseo.c360.cv.controllers;

import com.viseo.c360.cv.converters.SkillDomainEntityToDtoConverter;
import com.viseo.c360.cv.converters.SkillEntityToDtoConverter;
import com.viseo.c360.cv.models.dto.SkillDomainDto;
import com.viseo.c360.cv.models.dto.SkillDto;
import com.viseo.c360.cv.models.entities.SkillDomainEntity;
import com.viseo.c360.cv.models.entities.SkillEntity;
import com.viseo.c360.cv.services.SkillDomainService;
import com.viseo.c360.cv.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by YGU3747 on 09/01/2018
 */
@RestController
public class SkillController {
    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillDomainService skillDomainService;

    @CrossOrigin(origins =  "${server.front}")
    @RequestMapping(path = "/api/skills", method = GET)
    public Set<SkillDto> getAll(){
        // to be improved after connecting C360 Competence
        return new SkillEntityToDtoConverter().convert(skillService.getAll());
    }

    @CrossOrigin(origins =  "${server.front}")
    @RequestMapping(path = "/api/skillDomains", method = GET)
    public List<SkillDomainDto> getAllSkillDomains(){
        return new SkillDomainEntityToDtoConverter().convert(skillDomainService.getAll());
    }
}
