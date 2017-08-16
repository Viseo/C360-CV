package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * Created by ELE3653 on 07/08/2017.
 */
public class UserToDtoConverter implements Converter <UsersEntity,UserDto>{

    @Nullable
    @Override
    public UserDto convert(UsersEntity usersEntity) {

        UserDto userDto = new UserDto();
        userDto.setLogin(usersEntity.getLogin());
        userDto.setBirth_date(usersEntity.getBirth_date());
        userDto.setFirstName(usersEntity.getFirstName());
        userDto.setHobbies(usersEntity.getHobbies());
        userDto.setLastName(usersEntity.getLastName());
        userDto.setMail(usersEntity.getMail());
        userDto.setPassword(usersEntity.getPassword());
        userDto.setTelephone(usersEntity.getTelephone());
        userDto.setAdmin(usersEntity.getAdmin());
        userDto.setPosition(usersEntity.getPosition());
        userDto.setPicture(usersEntity.getPicture());
        userDto.setExperience(usersEntity.getExperience());
        userDto.setMissions(usersEntity.getMissions());
//        userDto.setLanguages(usersEntity.getLanguages());

        return userDto;
    }
}
