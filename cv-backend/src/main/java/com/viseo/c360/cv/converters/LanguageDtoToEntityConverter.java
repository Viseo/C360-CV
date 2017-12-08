package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.LanguageDto;
import com.viseo.c360.cv.models.entities.LanguagesEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * Created by YGU3747 on 08/12/2017
 */

public class LanguageDtoToEntityConverter implements Converter<LanguagesEntity,LanguageDto> {
    @Nullable
    @Override
    public LanguageDto convert(LanguagesEntity languagesEntity) {
        LanguageDto languageDto = new LanguageDto();
        languageDto.setLabel(languagesEntity.getLabel());
        return languageDto;
    }
}
