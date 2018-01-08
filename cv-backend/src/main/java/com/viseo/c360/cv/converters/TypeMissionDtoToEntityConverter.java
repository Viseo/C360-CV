package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.TypeMissionsDto;
import com.viseo.c360.cv.models.entities.TypeMissionsEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * Created by YGU3747 on 08/12/2017
 */

public class TypeMissionDtoToEntityConverter implements Converter<TypeMissionsDto, TypeMissionsEntity> {
    @Nullable
    @Override
    public TypeMissionsEntity convert(TypeMissionsDto typeMissionsDto) {
        TypeMissionsEntity typeMissionsEntity = new TypeMissionsEntity();
        typeMissionsEntity.setLabel(typeMissionsDto.getLabel());
        typeMissionsEntity.setId(typeMissionsDto.getId());
        typeMissionsEntity.setVersion(typeMissionsDto.getVersion());
        return typeMissionsEntity;
    }
}
