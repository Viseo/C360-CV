package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.MissionDto;
import com.viseo.c360.cv.models.entities.MissionsEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class MissionToDtoConverter implements Converter <MissionsEntity,MissionDto>{

    @Nullable
    @Override
    public MissionDto convert(MissionsEntity missionsentity){

        MissionDto missiondto=new MissionDto();
        missiondto.setBeginDate(missionsentity.getBeginDate());
        missiondto.setDescription(missionsentity.getDescription());
        missiondto.setEndDate(missionsentity.getEndDate());
        missiondto.setTitle(missionsentity.getTitle());

        return missiondto;
    }
}
