package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.TypeMissionsDto;
import com.viseo.c360.cv.models.entities.TypeMissionsEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YGU3747 on 08/12/2017
 */

public class TypeMissionEntityToDtoConverter implements Converter<TypeMissionsEntity,TypeMissionsDto> {
    @Nullable
    @Override
    public TypeMissionsDto convert(TypeMissionsEntity typeMissionsEntity) {
        TypeMissionsDto typeMissionsDto = new TypeMissionsDto();
        typeMissionsDto.setId(typeMissionsEntity.getId());
        typeMissionsDto.setLabel(typeMissionsEntity.getLabel());
        typeMissionsDto.setVersion(typeMissionsEntity.getVersion());
        return typeMissionsDto;
    }

    public List<TypeMissionsDto> convert(List<TypeMissionsEntity> typeMissionsEntityList){
        List<TypeMissionsDto> typeMissionsDtoList = new ArrayList<>();
        for (TypeMissionsEntity typeMissionsEntity:typeMissionsEntityList){
            typeMissionsDtoList.add(this.convert(typeMissionsEntity));
        }
        return typeMissionsDtoList;
    }
}
