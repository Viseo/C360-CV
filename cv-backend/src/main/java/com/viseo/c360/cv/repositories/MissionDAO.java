package com.viseo.c360.cv.repositories;


import com.viseo.c360.cv.models.entities.MissionsEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by ELE3653 on 07/08/2017.
 */

@Transactional
public interface MissionDAO extends CrudRepository <MissionsEntity,Long>{

}
