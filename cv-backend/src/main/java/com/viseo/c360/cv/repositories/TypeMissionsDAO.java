package com.viseo.c360.cv.repositories;

import com.viseo.c360.cv.models.entities.TypeMissionsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by YGU3747 on 05/01/2018
 */
@Transactional
public interface TypeMissionsDAO extends CrudRepository<TypeMissionsEntity,Long> {

    @Query("SELECT T FROM TypeMissionsEntity T")
    List<TypeMissionsEntity> getAll();
}
