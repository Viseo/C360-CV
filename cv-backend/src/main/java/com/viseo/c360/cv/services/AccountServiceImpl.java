package com.viseo.c360.cv.services;

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

    @Override
    public UsersEntity exist(String mail, String password) {

        return accountDAO.findByCredential(mail, password);
    }

    @Override
    public UsersEntity add(UserDto user) {
        return accountDAO.save(userToEntityConverter.convert(user));
    }

    public UsersEntity mailExist(String mail) {

        return accountDAO.findByMail(mail);
    }
}
