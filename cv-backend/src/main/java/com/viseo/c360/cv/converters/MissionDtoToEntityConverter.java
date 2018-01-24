package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.MissionDto;
import com.viseo.c360.cv.models.entities.MissionEntity;
import com.viseo.c360.cv.models.entities.SkillEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MissionDtoToEntityConverter implements Converter<MissionDto,MissionEntity>{

    @Nullable
    @Override
    public MissionEntity convert(MissionDto missionDto) {
        SkillDtoToEntityConverter skillDtoToEntityConverter = new SkillDtoToEntityConverter();
        MissionEntity missionsEntity = new MissionEntity();
        missionsEntity.setId(missionDto.getId());
        missionsEntity.setBeginDate(missionDto.getBeginDate());
        missionsEntity.setDescription(missionDto.getDescription());
        missionsEntity.setEndDate(missionDto.getEndDate());
        missionsEntity.setTitle(missionDto.getTitle());
        missionsEntity.setClient(new ClientDtoToEntityConverter().convert(missionDto.getClient()));
        missionsEntity.setSkills(skillDtoToEntityConverter.convert(missionDto.getSkills()));
        missionsEntity.setTypeMissions(new TypeMissionDtoToEntityConverter().convert(missionDto.getTypeMissions()));
        missionsEntity.setVersion(missionDto.getVersion());
        return missionsEntity;

    }

    public List<MissionEntity> convert(List<MissionDto> missionDtoList){
        List<MissionEntity> missionEntityList = new ArrayList<>();
        for(MissionDto missionDto : missionDtoList){
            missionEntityList.add(this.convert(missionDto));
        }
        return missionEntityList;
    }
}
