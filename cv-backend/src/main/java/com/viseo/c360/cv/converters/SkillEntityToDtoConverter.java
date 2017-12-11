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

public class SkillEntityToDtoConverter implements Converter<SkillEntity, SkillDto> {
    @Nullable
    @Override
    public SkillDto convert(SkillEntity skillEntity) {
        SkillDto skillDto = new SkillDto();
        skillDto.setDomain(skillEntity.getDomain());
        skillDto.setId(skillEntity.getId());
        skillDto.setLabel(skillEntity.getLabel());
        return skillDto;
    }

    public Set<SkillDto> convert(Set<SkillEntity> skillEntitySet) {
        Set<SkillDto> skillDtoSet = new HashSet<>();
        for(SkillEntity skillEntity : skillEntitySet){
            skillDtoSet.add(this.convert(skillEntity));
        }
        return skillDtoSet;
    }
}
