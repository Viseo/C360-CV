package com.viseo.c360.cv.services;

import com.viseo.c360.cv.models.entities.LanguageEntity;
import com.viseo.c360.cv.repositories.LanguageDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by YGU3747 on 15/01/2018
 */

public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageDAO languageDAO;

    @Override
    public Set<LanguageEntity> getAll() {
        return languageDAO.getAll();
    }

    @Override
    public void addLanguage(LanguageEntity language) {
        languageDAO.save(language);
    }
}
