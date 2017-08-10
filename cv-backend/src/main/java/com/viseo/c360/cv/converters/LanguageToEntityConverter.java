package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.LanguageDto;
import com.viseo.c360.cv.models.entities.LanguagesEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * Created by ELE3653 on 07/08/2017.
 */
public class LanguageToEntityConverter implements Converter <LanguageDto,LanguagesEntity>{

    @Nullable
    @Override
    public LanguagesEntity convert(LanguageDto languageDto) {

        LanguagesEntity languagesEntity = new LanguagesEntity();
        languagesEntity.setLabel(languageDto.getLabel());

        return languagesEntity;
    }
}
