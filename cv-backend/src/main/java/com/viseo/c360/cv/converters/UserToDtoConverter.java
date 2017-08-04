package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class UserToDtoConverter implements Converter<UsersEntity, UserDto> {

    @Nullable
    @Override
    public UserDto convert(UsersEntity usersEntity) {

        UserDto userDto = new UserDto(usersEntity.getMail(),usersEntity.getPassword());

//        userDto.setPassword(usersEntity.getPassword());
        userDto.setFirstName(usersEntity.getFirstName());
        userDto.setLastName(usersEntity.getLastName());
//        userDto.setMail(usersEntity.getMail());
        userDto.setHobbies(usersEntity.getHobbies());
        userDto.setLogin(usersEntity.getLogin());
        userDto.setTelephone(usersEntity.getTelephone());

        return userDto;
    }
}
