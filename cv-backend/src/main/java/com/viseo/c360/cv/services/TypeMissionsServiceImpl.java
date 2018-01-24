package com.viseo.c360.cv.services;

import com.viseo.c360.cv.models.entities.TypeMissionsEntity;
import com.viseo.c360.cv.repositories.TypeMissionsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YGU3747 on 05/01/2018
 */
@Service
public class TypeMissionsServiceImpl implements TypeMissionsService {

    @Autowired
    private TypeMissionsDAO typeMissionsDAO;

    @Override
    public List<TypeMissionsEntity> getAll() {
        return typeMissionsDAO.getAll();
    }
}
