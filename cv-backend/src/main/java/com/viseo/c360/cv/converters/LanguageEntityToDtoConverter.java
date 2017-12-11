package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.LanguageDto;
import com.viseo.c360.cv.models.entities.LanguageEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by YGU3747 on 08/12/2017
 */

public class LanguageEntityToDtoConverter implements Converter<LanguageEntity,LanguageDto> {
    @Nullable
    @Override
    public LanguageDto convert(LanguageEntity languageEntity) {
        LanguageDto languageDto = new LanguageDto();
        languageDto.setLabel(languageEntity.getLabel());
        return languageDto;
    }

    public Set<LanguageDto> convert(Set<LanguageEntity> languageEntitySet){
        Set<LanguageDto> languageDtoSet = new HashSet<>();
        for(LanguageEntity languageEntity : languageEntitySet){
            languageDtoSet.add(this.convert(languageEntity));
        }
        return languageDtoSet;
    }
}
