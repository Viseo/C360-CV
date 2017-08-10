package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.ClientDto;
import com.viseo.c360.cv.models.entities.ClientsEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * Created by ELE3653 on 07/08/2017.
 */
public class ClientToEntityConverter implements Converter<ClientDto,ClientsEntity> {

    @Nullable
    @Override
    public ClientsEntity convert(ClientDto clientDto) {

        ClientsEntity clientsEntity = new ClientsEntity();
        clientsEntity.setDescription(clientDto.getDescription());
        clientsEntity.setDomain(clientDto.getDomain());
        clientsEntity.setLabel(clientDto.getLabel());
//        clientsEntity.setMission(clientDto.getMission());
        return clientsEntity;
    }
}
