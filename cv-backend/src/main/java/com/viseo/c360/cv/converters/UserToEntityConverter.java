package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class UserToEntityConverter implements Converter<UserDto, UsersEntity> {

    @Nullable
    @Override
    public UsersEntity convert(UserDto userDto) {

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(userDto.getId());
        usersEntity.setPassword(userDto.getPassword());
        usersEntity.setFirstName(userDto.getFirstName());
        usersEntity.setLastName(userDto.getLastName());
        usersEntity.setMail(userDto.getMail());
        usersEntity.setBirth_date(userDto.getBirth_date());
        usersEntity.setHobbies(userDto.getHobbies());
        usersEntity.setLogin(userDto.getLogin());
        usersEntity.setTelephone(userDto.getTelephone());
        usersEntity.setAdmin(userDto.getAdmin());
        usersEntity.setExperience(userDto.getExperience());
        usersEntity.setPicture(userDto.getPicture());
        usersEntity.setPosition(userDto.getPosition());
        usersEntity.setMissions(userDto.getMissions());
//        usersEntity.setLanguages(userDto.getLanguages());


        return usersEntity;
    }
}
