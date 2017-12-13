package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.LanguageEntity;
import com.viseo.c360.cv.models.entities.MissionEntity;
import com.viseo.c360.cv.models.entities.UsersEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDtoToEntityConverter implements Converter<UserDto, UsersEntity> {

    @Nullable
    @Override
    public UsersEntity convert(UserDto userDto) {
        LanguageDtoToEntityConverter languageDtoToEntityConverter = new LanguageDtoToEntityConverter();
        MissionDtoToEntityConverter missionDtoToEntityConverter = new MissionDtoToEntityConverter();
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
        usersEntity.setId(userDto.getId());
        usersEntity.setLastUpdateDate(userDto.getLastUpdateDate());
        if (userDto.getLanguages() != null){
            usersEntity.setLanguages(languageDtoToEntityConverter.convert(userDto.getLanguages()));
        }
        if(userDto.getMissions() != null){
            usersEntity.setMissions(missionDtoToEntityConverter.convert(userDto.getMissions()));
        }
        return usersEntity;
    }
}
