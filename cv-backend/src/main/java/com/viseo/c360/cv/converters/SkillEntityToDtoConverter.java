package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.SkillDto;
import com.viseo.c360.cv.models.entities.SkillEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

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
}
