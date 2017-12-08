package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.LanguageDto;
import com.viseo.c360.cv.models.entities.LanguageEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * Created by ELE3653 on 07/08/2017.
 */
public class LanguageDtoToEntityConverter implements Converter <LanguageDto,LanguageEntity>{

    @Nullable
    @Override
    public LanguageEntity convert(LanguageDto languageDto) {

        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setLabel(languageDto.getLabel());

        return languageEntity;
    }
}
