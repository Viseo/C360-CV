package com.viseo.c360.cv.services;

import com.viseo.c360.cv.models.entities.MissionEntity;

import java.util.List;

/**
 * Created by ELE3653 on 07/08/2017.
 */
public interface MissionService {

    MissionEntity add(MissionEntity missionEntity);

    MissionEntity getById(int userId, int missionId);

    void delete(int missionId);

    MissionEntity update(MissionEntity missionEntity);

    List <MissionEntity> getAll(int userId);
}
