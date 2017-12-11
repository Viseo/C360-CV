package com.viseo.c360.cv.repositories;


import com.viseo.c360.cv.models.entities.MissionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ELE3653 on 07/08/2017.
 */

@Transactional
public interface MissionDAO extends CrudRepository <MissionEntity,Long>{

    @Query("SELECT M FROM MissionEntity M JOIN FETCH M.skills WHERE M.id = ?1")
    MissionEntity findById(int id);

    @Query("SELECT M FROM MissionEntity M JOIN FETCH M.skills")
    List<MissionEntity> getAll();
}
