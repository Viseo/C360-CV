package com.viseo.c360.cv.repositories;


import com.viseo.c360.cv.models.entities.LanguageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * Created by YGU3747 on 15/01/2018
 */
@Transactional
public interface LanguageDAO extends CrudRepository<LanguageEntity,Long>{
    @Query("SELECT l FROM LanguageEntity l")
    Set<LanguageEntity> getAll();
}
