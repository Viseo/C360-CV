package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.MissionDto;
import com.viseo.c360.cv.models.entities.MissionsEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class MissionToEntityConverter implements Converter<MissionDto,MissionsEntity>{

    @Nullable
    @Override
    public MissionsEntity convert(MissionDto missionDto) {

        MissionsEntity missionsEntity = new MissionsEntity();
        missionsEntity.setBeginDate(missionDto.getBeginDate());
        missionsEntity.setDescription(missionDto.getDescription());
        missionsEntity.setEndDate(missionDto.getEndDate());
        missionsEntity.setTitle(missionDto.getTitle());
//        missionsEntity.setClientId(missionDto.getClient().getId());

        return missionsEntity;
    }
}
