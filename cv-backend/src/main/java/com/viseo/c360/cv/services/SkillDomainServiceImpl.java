package com.viseo.c360.cv.services;

import com.viseo.c360.cv.models.dto.SkillDomainDto;
import com.viseo.c360.cv.models.entities.SkillDomainEntity;
import com.viseo.c360.cv.models.entities.SkillEntity;
import com.viseo.c360.cv.repositories.SkillDomainDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YGU3747 on 10/01/2018
 */
@Service
public class SkillDomainServiceImpl implements SkillDomainService{

    @Autowired
    private SkillDomainDAO skillDomainDAO;

    @Override
    public List<SkillDomainEntity> getAll() {
        return skillDomainDAO.getAllDomains();
    }
}
