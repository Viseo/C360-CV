package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.SkillDto;
import com.viseo.c360.cv.models.entities.SkillEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by YGU3747 on 08/12/2017
 */

public class SkillDtoToEntityConverter implements Converter<SkillDto, SkillEntity> {
    @Nullable
    @Override
    public SkillEntity convert(SkillDto skillDto) {
        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setDomain(skillDto.getDomain());
        skillEntity.setLabel(skillDto.getLabel());
        skillEntity.setId(skillDto.getId());
        skillEntity.setVersion(skillDto.getVersion());
        return skillEntity;
    }

    public Set<SkillEntity> convert(Set<SkillDto> skillDtoSet){
        Set<SkillEntity> skillEntitySet = new HashSet<>();
        for(SkillDto skillDto : skillDtoSet){
            skillEntitySet.add(this.convert(skillDto));
        }
        return skillEntitySet;
    }
}
