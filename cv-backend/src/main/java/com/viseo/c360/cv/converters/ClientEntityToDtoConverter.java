package com.viseo.c360.cv.converters;

import com.viseo.c360.cv.models.dto.ClientDto;
import com.viseo.c360.cv.models.entities.ClientEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YGU3747 on 08/12/2017
 */

public class ClientEntityToDtoConverter  implements Converter<ClientEntity, ClientDto> {
    @Nullable
    @Override
    public ClientDto convert(ClientEntity clientsEntity) {
        ClientDto clientDto = new ClientDto();
        clientDto.setDescription(clientsEntity.getDescription());
        clientDto.setDomain(clientsEntity.getDomain());
        clientDto.setLabel(clientsEntity.getLabel());
        clientDto.setId(clientsEntity.getId());
        return clientDto;
    }

    @Nullable
    public List<ClientDto> convert(List<ClientEntity> clientEntityList){
        List<ClientDto> clientDtoList = new ArrayList<>();
        for (ClientEntity clientEntity:clientEntityList){
            clientDtoList.add(this.convert(clientEntity));
        }
        return clientDtoList;
    }
}
