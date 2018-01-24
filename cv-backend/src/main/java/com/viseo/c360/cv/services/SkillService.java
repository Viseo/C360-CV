package com.viseo.c360.cv.services;

import com.viseo.c360.cv.models.entities.SkillEntity;

import java.util.Set;


/**
 * Created by YGU3747 on 09/01/2018
 */

public interface SkillService {
    Set<SkillEntity> getAll();
}
