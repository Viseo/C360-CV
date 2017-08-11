package com.viseo.c360.cv.services;

import com.viseo.c360.cv.converters.MissionToDtoConverter;
import com.viseo.c360.cv.converters.MissionToEntityConverter;
import com.viseo.c360.cv.models.dto.MissionDto;
import com.viseo.c360.cv.models.entities.MissionsEntity;
import com.viseo.c360.cv.repositories.MissionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissionServiceImpl implements MissionService {

    @Autowired
    private MissionDAO missionDAO;

    @Autowired
    private MissionToDtoConverter missionToDtoConverter;

    @Autowired
    private MissionToEntityConverter missionToEntityConverter;

    @Override
    public MissionDto add(MissionDto missionDto) {

        if (null != missionDto) {

            MissionsEntity missionsEntity = missionToEntityConverter.convert(missionDto);

            return missionToDtoConverter.convert(missionDAO.save(missionsEntity));
        }

        throw new IllegalArgumentException("Object Mision ne doit pas être null.");
    }

    @Override
    public MissionDto getById(int missionId, int userId) {
        return missionToDtoConverter.convert(missionDAO.getById(missionId, userId));
    }

    @Override
    public Boolean delete(int userId, int missionId) {
        return missionDAO.delete(userId, missionId);
    }

    @Override
    public MissionDto update(MissionDto missionDto) {
        if (null != missionDto) {

            MissionsEntity missionsEntity = missionToEntityConverter.convert(missionDto);

            return missionToDtoConverter.convert(missionDAO.save(missionsEntity));
        }

        throw new IllegalArgumentException("Object MissionDto ne doit pas être nul.");
    }

}
