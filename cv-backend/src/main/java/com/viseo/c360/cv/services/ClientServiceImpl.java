package com.viseo.c360.cv.services;

import com.viseo.c360.cv.models.entities.ClientEntity;
import com.viseo.c360.cv.repositories.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YGU3747 on 02/01/2018
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Override
    public ClientEntity add(ClientEntity clientEntity) {
        return clientDAO.save(clientEntity);
    }

    @Override
    public ClientEntity get(long id) {
        return clientDAO.findById(id);
    }

    @Override
    public List<ClientEntity> getAll() {
        return clientDAO.getAll();
    }

    @Override
    public void delete(long id) {
        clientDAO.deleteById(id);
    }

    @Override
    public ClientEntity update(ClientEntity clientEntity) {
        return clientDAO.save(clientEntity);
    }
}
