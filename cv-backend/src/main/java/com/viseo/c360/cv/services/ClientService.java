package com.viseo.c360.cv.services;

import com.viseo.c360.cv.models.entities.ClientEntity;

import java.util.List;

/**
 * Created by YGU3747 on 02/01/2018
 */

public interface ClientService {
    ClientEntity add(ClientEntity clientEntity);

    ClientEntity get(long id);

    List<ClientEntity> getAll();

    void delete(long id);

    ClientEntity update(ClientEntity clientEntity);

}
