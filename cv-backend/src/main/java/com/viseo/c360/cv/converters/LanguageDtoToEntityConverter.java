package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.LanguageDto;
import com.viseo.c360.cv.models.entities.LanguageEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.Set;

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

    public Set<LanguageEntity> convert(Set<LanguageDto> languageDtoSet){
        Set<LanguageEntity> languageEntitySet = new HashSet<>();
        if (languageDtoSet.size()>0){
            for(LanguageDto languageDto : languageDtoSet){
                languageEntitySet.add(this.convert(languageDto));
            }
        }
        return languageEntitySet;
    }
}
