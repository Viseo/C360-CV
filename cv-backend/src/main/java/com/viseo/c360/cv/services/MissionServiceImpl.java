package com.viseo.c360.cv.services;

import com.viseo.c360.cv.models.dto.MissionDto;
import com.viseo.c360.cv.models.entities.MissionEntity;
import com.viseo.c360.cv.repositories.MissionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionServiceImpl implements MissionService {
    @Autowired
    private MissionDAO missionDAO;

    @Override
    public MissionEntity add(MissionEntity missionEntity) {
        return missionDAO.save(missionEntity);
    }

    @Override
    public MissionEntity getById(int userId, int id) {
        return missionDAO.findById(id);
    }

    @Override
    public Boolean delete(int userId, int missionId) {
        return null;
    }

    @Override
    public MissionEntity update(MissionEntity missionEntity) {
        return missionDAO.save(missionEntity);
    }

    @Override
    public List<MissionEntity> getAll(int userId) {
        return missionDAO.getAll();
    }
}
