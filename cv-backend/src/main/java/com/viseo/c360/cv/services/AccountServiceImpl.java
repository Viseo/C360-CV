package com.viseo.c360.cv.services;

import com.viseo.c360.cv.converters.UserToDtoConverter;
import com.viseo.c360.cv.converters.UserToEntityConverter;
import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;
import com.viseo.c360.cv.repositories.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private UserToEntityConverter userToEntityConverter;

    @Autowired
    private UserToDtoConverter userToDtoConverter;

    @Override
    public UsersEntity exist(String mail, String password) {

        return accountDAO.findByCredential(mail, password);
    }

    @Override
    public UsersEntity add(UserDto user) {
        UsersEntity convertedUser = userToEntityConverter.convert(user);
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
        UsersEntity convertedUser = userToEntityConverter.convert(user);

        return accountDAO.save(convertedUser);
    }
}
