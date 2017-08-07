package com.viseo.c360.cv.services;

import com.viseo.c360.cv.converters.UserToDtoConverter;
import com.viseo.c360.cv.converters.UserToEntityConverter;
import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;
import com.viseo.c360.cv.repositories.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

        return accountDAO.save(userToEntityConverter.convert(user));
    }

    @Override
    public List<UserDto> getAll() {

        List<UsersEntity> usersEntities = this.accountDAO.getAll();

        if (isEmpty(usersEntities)) {
            return emptyList();
        }

        return usersEntities.stream().map(ite -> userToDtoConverter.convert(ite)).collect(toList());
    }
}
