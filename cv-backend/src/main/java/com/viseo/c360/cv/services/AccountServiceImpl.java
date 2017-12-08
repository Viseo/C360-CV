package com.viseo.c360.cv.services;

import com.viseo.c360.cv.converters.UserEntityToDtoConverter;
import com.viseo.c360.cv.converters.UserDtoToEntityConverter;
import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;
import com.viseo.c360.cv.repositories.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private UserDtoToEntityConverter userDtoToEntityConverter;

    @Autowired
    private UserEntityToDtoConverter userEntityToDtoConverter;

    @Override
    public UsersEntity exist(String mail, String password) {

        return accountDAO.findByCredential(mail, password);
    }

    @Override
    public UsersEntity add(UserDto user) {
        user.setLastUpdateDate(new Date());
        UsersEntity convertedUser = userDtoToEntityConverter.convert(user);
        return accountDAO.save(convertedUser);
    }

    public UsersEntity mailExist(String mail) {
        return accountDAO.findByMail(mail);
    }

    public UsersEntity getUserById(int id) {
        return accountDAO.findById(id);
    }

    @Override
    public List<UsersEntity> getAll() {
        return accountDAO.getAll();
    }

    public UsersEntity updateUser(UserDto user){
        user.setLastUpdateDate(new Date());
        UsersEntity convertedUser = userDtoToEntityConverter.convert(user);

        return accountDAO.save(convertedUser);
    }
}
