package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.ClientDto;
import com.viseo.c360.cv.models.entities.ClientEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * Created by ELE3653 on 07/08/2017.
 */
public class ClientDtoToEntityConverter implements Converter<ClientDto,ClientEntity> {

    @Nullable
    @Override
    public ClientEntity convert(ClientDto clientDto) {

        ClientEntity clientsEntity = new ClientEntity();
        clientsEntity.setDescription(clientDto.getDescription());
        clientsEntity.setDomain(clientDto.getDomain());
        clientsEntity.setLabel(clientDto.getLabel());
        return clientsEntity;
    }
}
