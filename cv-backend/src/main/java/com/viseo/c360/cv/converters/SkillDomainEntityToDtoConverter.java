package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.SkillDomainDto;
import com.viseo.c360.cv.models.entities.SkillDomainEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YGU3747 on 10/01/2018
 */

public class SkillDomainEntityToDtoConverter implements Converter<SkillDomainEntity, SkillDomainDto> {
    @Nullable
    @Override
    public SkillDomainDto convert(SkillDomainEntity source) {
        SkillDomainDto skillDomainDto = new SkillDomainDto();
        skillDomainDto.setLabel(source.getLabel());
        skillDomainDto.setSkills(new SkillEntityToDtoConverter().convert(source.getSkills()));
        skillDomainDto.setId(source.getId());
        return skillDomainDto;
    }

    public List<SkillDomainDto> convert(List<SkillDomainEntity> skillDomainEntityList){
        List<SkillDomainDto> skillDomainDtoList = new ArrayList<>();
        for(SkillDomainEntity skillDomainEntity:skillDomainEntityList){
            skillDomainDtoList.add(this.convert(skillDomainEntity));
        }
        return skillDomainDtoList;
    }
}
