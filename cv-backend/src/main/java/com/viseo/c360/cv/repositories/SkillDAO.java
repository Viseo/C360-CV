package com.viseo.c360.cv.repositories;

import com.viseo.c360.cv.models.entities.SkillEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by YGU3747 on 09/01/2018
 */
@Transactional
public interface SkillDAO extends CrudRepository<SkillEntity, Long>{

    @Query("SELECT S FROM SkillEntity S")
    List<SkillEntity> getAll();
}
