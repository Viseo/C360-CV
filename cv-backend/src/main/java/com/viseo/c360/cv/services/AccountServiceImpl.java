package com.viseo.c360.cv.services;

import com.viseo.c360.cv.converters.UserToDtoConverter;
import com.viseo.c360.cv.converters.UserToEntityConverter;
import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;
import com.viseo.c360.cv.repositories.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private UserToEntityConverter userToEntityConverter;

    @Autowired
    private UserToDtoConverter userToDtoConverter;

    @Override
    public UserDto exist(String mail, String password) {

        UsersEntity usersEntity = accountDAO.findByCredential(mail, password);

        if (null != usersEntity){
            return userToDtoConverter.convert(usersEntity);
        }


        return new UserDto(mail,password);
    }

    @Override
    public UserDto add(UserDto user) {

        UsersEntity usersEntity = userToEntityConverter.convert(user);

        return userToDtoConverter.convert(accountDAO.save(usersEntity));
    }
}
