package com.viseo.c360.cv.repositories;

import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface AccountDAO extends CrudRepository<UsersEntity, Long> {

    @Query("SELECT U FROM UsersEntity U WHERE U.mail = ?1 AND U.password = ?2")
    UsersEntity findByCredential(String mail, String password);

    @Query("SELECT U FROM UsersEntity U WHERE U.mail = ?1")
    UsersEntity findByMail(String mail);

    @Query("SELECT U FROM UsersEntity U WHERE U.id = ?1")
    UsersEntity findById(int id);

    @Query("SELECT U FROM UsersEntity U")
    List<UsersEntity> getAll();

}
