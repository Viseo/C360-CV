package com.viseo.c360.cv.controllers;

import com.viseo.c360.cv.converters.LanguageDtoToEntityConverter;
import com.viseo.c360.cv.converters.LanguageEntityToDtoConverter;
import com.viseo.c360.cv.models.dto.LanguageDto;
import com.viseo.c360.cv.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by YGU3747 on 15/01/2018
 */
@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(method = GET)
    public Set<LanguageDto> getAllLanguages(){
        return new LanguageEntityToDtoConverter().convert(languageService.getAll());
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(method = POST)
    public List<LanguageDto> addLanguage(@RequestBody List<LanguageDto> languages){
        List<LanguageDto> result = new ArrayList<>();
        for (LanguageDto language:languages) {
            result.add(new LanguageEntityToDtoConverter()
                    .convert(languageService.addLanguage(new LanguageDtoToEntityConverter().convert(language)))) ;
        }
        return result;

    }
}
