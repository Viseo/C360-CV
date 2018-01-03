package com.viseo.c360.cv.repositories;

import com.viseo.c360.cv.models.entities.ClientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by YGU3747 on 02/01/2018
 */

@Transactional
public interface ClientDAO extends CrudRepository<ClientEntity,Long> {

    @Query("SELECT C FROM ClientEntity C WHERE C.id = ?1")
    ClientEntity findById (long id);

    @Query("SELECT C FROM ClientEntity C")
    List<ClientEntity> getAll();
}
