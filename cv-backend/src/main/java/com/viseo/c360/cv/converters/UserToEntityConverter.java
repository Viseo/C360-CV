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
        usersEntity.setPassword(userDto.getPassword());
        usersEntity.setFirstName(userDto.getFirstName());
        usersEntity.setLastName(userDto.getLastName());
        usersEntity.setMail(userDto.getMail());
        usersEntity.setHobbies(userDto.getHobbies());
        usersEntity.setLogin(userDto.getLogin());
        usersEntity.setTelephone(userDto.getTelephone());

        return usersEntity;
    }
}
