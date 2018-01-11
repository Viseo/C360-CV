package com.viseo.c360.cv.repositories;

import com.viseo.c360.cv.models.entities.SkillDomainEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by YGU3747 on 10/01/2018
 */
@Transactional
public interface SkillDomainDAO extends CrudRepository<SkillDomainEntity, Long> {
    @Query("SELECT D FROM SkillDomainEntity D")
    List<SkillDomainEntity> getAllDomains();
}
