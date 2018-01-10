package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.SkillDomainDto;
import com.viseo.c360.cv.models.entities.SkillDomainEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * Created by YGU3747 on 10/01/2018
 */

public class SkillDomainDtoToEntityConverter implements Converter<SkillDomainDto, SkillDomainEntity> {
    @Nullable
    @Override
    public SkillDomainEntity convert(SkillDomainDto source) {
        SkillDomainEntity skillDomainEntity = new SkillDomainEntity();
        skillDomainEntity.setLabel(source.getLabel());
        skillDomainEntity.setId(source.getId());
        return skillDomainEntity;
    }
}
