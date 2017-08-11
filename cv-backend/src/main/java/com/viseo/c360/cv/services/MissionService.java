package com.viseo.c360.cv.services;

import com.viseo.c360.cv.models.dto.MissionDto;
import com.viseo.c360.cv.models.entities.MissionsEntity;

import java.util.List;

/**
 * Created by ELE3653 on 07/08/2017.
 */
public interface MissionService {

    MissionDto add(MissionDto missionDto);

    MissionDto getById(int userId, int missionId);

    Boolean delete(int userId, int missionId);

    MissionDto update(MissionDto missionDto);

}
