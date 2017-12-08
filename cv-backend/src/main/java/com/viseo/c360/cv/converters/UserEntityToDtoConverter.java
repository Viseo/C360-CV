package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.LanguageDto;
import com.viseo.c360.cv.models.dto.MissionDto;
import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;
import org.hibernate.Hibernate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

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
        List<MissionDto> missionDtoList= new ArrayList<>();
        for(int i=0;i<usersEntity.getMissions().size();i++){
            missionDtoList.add(missionEntityToDtoConverter.convert(usersEntity.getMissions().get(i)));
        }
        userDto.setMissions(missionDtoList);
        userDto.setId(usersEntity.getId());
        userDto.setLastUpdateDate(usersEntity.getLastUpdateDate());
        List<LanguageDto> languageDtoList = new ArrayList<>();
        for (int i=0;i<usersEntity.getLanguages().size();i++){
            languageDtoList.add(languageEntityToDtoConverter.convert(usersEntity.getLanguages().get(i)));
        }
        userDto.setLanguages(languageDtoList);

        return userDto;
    }
}
