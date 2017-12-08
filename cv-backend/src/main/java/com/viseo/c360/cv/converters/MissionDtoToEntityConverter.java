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
        List<SkillEntity> skillEntityList = new ArrayList<>();
        for (int i=0;i<missionDto.getSkills().size();i++){
            skillEntityList.add(skillDtoToEntityConverter.convert(missionDto.getSkills().get(i)));
        }
        missionsEntity.setSkills(skillEntityList);
        missionsEntity.setTypeMissions(new TypeMissionDtoToEntityConverter().convert(missionDto.getTypeMissions()));

        return missionsEntity;

    }
}
