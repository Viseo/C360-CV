package com.viseo.c360.cv.services;

import com.viseo.c360.cv.models.entities.LanguageEntity;

import java.util.Set;

/**
 * Created by YGU3747 on 15/01/2018
 */

public interface LanguageService {

    Set<LanguageEntity> getAll();

    LanguageEntity addLanguage(LanguageEntity language);
}
