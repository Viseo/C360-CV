package com.viseo.c360.cv.repositories;

import com.viseo.c360.cv.models.entities.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface AccountDAO extends CrudRepository<UsersEntity, Long> {

    @Query("SELECT U FROM UsersEntity U WHERE U.mail = ?1 AND U.password = ?2")
    UsersEntity findByCredential(String mail, String password);
}
