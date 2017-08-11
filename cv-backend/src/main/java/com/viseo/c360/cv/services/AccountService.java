package com.viseo.c360.cv.services;

import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;

import java.util.List;

public interface AccountService {

    UsersEntity exist(String mail, String password);

    UsersEntity add(UserDto user);

    UsersEntity mailExist(String mail);

    UsersEntity getUserById(int id);

    List<UsersEntity> getAll();

    UsersEntity updateUser(UserDto user);

}
