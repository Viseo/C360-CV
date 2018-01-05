package com.viseo.c360.cv.services;

import com.viseo.c360.cv.models.entities.MissionEntity;
import com.viseo.c360.cv.repositories.MissionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MissionServiceImpl implements MissionService {
    @Autowired
    private MissionDAO missionDAO;

//    @PersistenceContext
//    private EntityManager em;

    @Override
//    @Transactional
    public MissionEntity add(MissionEntity missionEntity) {
        return missionDAO.save(missionEntity);
    }

    @Override
    public MissionEntity getById(int userId, int id) {
        return missionDAO.findById(id);
    }

    @Override
    public void delete(int missionId) {
        missionDAO.deleteById((long)missionId);
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
