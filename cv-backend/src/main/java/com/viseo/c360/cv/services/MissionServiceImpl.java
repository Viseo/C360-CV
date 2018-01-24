package com.viseo.c360.cv.services;

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
    public void delete(int missionId) {
        missionDAO.deleteById((long)missionId);
    }

    @Override
    public MissionEntity update(MissionEntity missionEntity) {
//        missionDAO.findById(missionEntity.getId())
//                .ifPresent(updateMissionEntity -> {
//                    updateMissionEntity.setSkills(missionEntity.getSkills());
//                    updateMissionEntity.setVersion(missionEntity.getVersion());
//                    updateMissionEntity.setTypeMissions(missionEntity.getTypeMissions());
//                    updateMissionEntity.setClient(missionEntity.getClient());
//                    updateMissionEntity.setBeginDate(missionEntity.getBeginDate());
//                    updateMissionEntity.setDescription(missionEntity.getDescription());
//                    updateMissionEntity.setEndDate(missionEntity.getEndDate());
//                    updateMissionEntity.setTitle(missionEntity.getTitle());
//                });
//        return missionEntity;

        return missionDAO.save(missionEntity);

//        missionEntity = em.merge(missionEntity);//because of version of the front haven't been well saved
//        em.flush();
//        return missionEntity;

//        missionDAO.findById(missionEntity.getId())
//        .ifPresent(updateMissionEntity -> {
//            updateMissionEntity = missionEntity;
//        });
//        return missionEntity;
    }

    @Override
    public List<MissionEntity> getAll(int userId) {
        return missionDAO.getAll();
    }

    @Override
    public List<MissionEntity> getMissionsByUser(long userId){
        return missionDAO.getMissionsByUser(userId);
    }
}
