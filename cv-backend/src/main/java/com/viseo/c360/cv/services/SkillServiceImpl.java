package com.viseo.c360.cv.services;

import com.viseo.c360.cv.models.entities.SkillEntity;
import com.viseo.c360.cv.repositories.SkillDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by YGU3747 on 09/01/2018
 */
@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillDAO skillDAO;

    @Override
    public Set<SkillEntity> getAll() {
        return skillDAO.getAll();
    }
}
