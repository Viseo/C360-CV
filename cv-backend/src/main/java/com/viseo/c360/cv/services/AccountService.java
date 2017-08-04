package com.viseo.c360.cv.services;

import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;

public interface AccountService {

    UserDto exist(String mail, String password);

    UserDto add(UserDto user);
}
