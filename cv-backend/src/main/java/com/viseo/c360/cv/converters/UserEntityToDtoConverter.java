package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.LanguageDto;
import com.viseo.c360.cv.models.dto.MissionDto;
import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;
import org.hibernate.Hibernate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ELE3653 on 07/08/2017.
 */
public class UserEntityToDtoConverter implements Converter <UsersEntity,UserDto>{

    @Nullable
    @Override
    public UserDto convert(UsersEntity usersEntity) {

       // Hibernate.initialize(usersEntity);//avoid famous LazyInitializationException
        LanguageEntityToDtoConverter languageEntityToDtoConverter = new LanguageEntityToDtoConverter();
        MissionEntityToDtoConverter missionEntityToDtoConverter = new MissionEntityToDtoConverter();
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
        userDto.setMissions(missionEntityToDtoConverter.convert(usersEntity.getMissions()));
        userDto.setId(usersEntity.getId());
        userDto.setLastUpdateDate(usersEntity.getLastUpdateDate());
        userDto.setLanguages(languageEntityToDtoConverter.convert(usersEntity.getLanguages()));
        userDto.setVersion(usersEntity.getVersion());
        return userDto;
    }
}
