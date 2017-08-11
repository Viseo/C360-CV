package com.viseo.c360.cv.repositories;

import com.viseo.c360.cv.models.dto.MissionDto;
import com.viseo.c360.cv.models.entities.MissionsEntity;
import com.viseo.c360.cv.models.entities.SkillsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface MissionDAO extends CrudRepository<MissionsEntity, Long> {

    @Query("SELECT U FROM MissionsEntity U WHERE id=?1 AND userId=?2")
    MissionsEntity getById(int missionId,int userId);

    @Query("DELETE FROM MissionsEntity WHERE id=?2 AND userId=?1")
    Boolean delete(int userId, int missionId);

}