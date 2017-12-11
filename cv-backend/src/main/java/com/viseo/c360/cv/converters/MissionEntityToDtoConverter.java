package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.MissionDto;
import com.viseo.c360.cv.models.dto.SkillDto;
import com.viseo.c360.cv.models.entities.MissionEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YGU3747 on 08/12/2017
 */

public class MissionEntityToDtoConverter implements Converter<MissionEntity,MissionDto> {
    @Nullable
    @Override
    public MissionDto convert(MissionEntity missionEntity) {
        SkillEntityToDtoConverter skillEntityToDtoConverter = new SkillEntityToDtoConverter();
        MissionDto missionDto = new MissionDto();
        missionDto.setBeginDate(missionEntity.getBeginDate());
        missionDto.setEndDate(missionEntity.getEndDate());
        missionDto.setClient(new ClientEntityToDtoConverter().convert(missionEntity.getClient()));
        missionDto.setId(missionEntity.getId());
        missionDto.setTitle(missionEntity.getTitle());
        missionDto.setDescription(missionEntity.getDescription());
        missionDto.setTypeMissions(new TypeMissionEntityToDtoConverter().convert(missionEntity.getTypeMissions()));
        missionDto.setSkills(skillEntityToDtoConverter.convert(missionEntity.getSkills()));
        return missionDto;
    }

    @Nullable
    public List<MissionDto> convert(List<MissionEntity> missionEntityList){
        List<MissionDto> missionDtoList = new ArrayList<>();
        for (MissionEntity missionEntity:missionEntityList){
            missionDtoList.add(this.convert(missionEntity));
        }
        return missionDtoList;
    }
}
