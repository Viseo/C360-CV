package com.viseo.c360.cv.controllers;

import com.viseo.c360.cv.converters.ClientDtoToEntityConverter;
import com.viseo.c360.cv.converters.ClientEntityToDtoConverter;
import com.viseo.c360.cv.models.dto.ClientDto;
import com.viseo.c360.cv.models.entities.ClientEntity;
import com.viseo.c360.cv.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by YGU3747 on 02/01/2018
 */

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(method = GET)
    public List<ClientDto> getAllClients(){
        return new ClientEntityToDtoConverter().convert(clientService.getAll());
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(path="/{clientId}", method = GET)
    public ClientDto getClientById(@PathVariable Long clientId){
        return new ClientEntityToDtoConverter().convert(clientService.get(clientId));
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(method = POST)
    public ClientDto addClient(@RequestBody ClientDto clientDto){
        return new ClientEntityToDtoConverter().convert(clientService.add(new ClientDtoToEntityConverter().convert(clientDto)));
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(method = PUT)
    public ClientDto updateClient(@RequestBody ClientDto clientDto){
        return new ClientEntityToDtoConverter().convert(clientService.update(new ClientDtoToEntityConverter().convert(clientDto)));
    }

}
