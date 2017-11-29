package com.viseo.c360.cv.amqp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viseo.c360.cv.models.dto.UserDto;
import org.apache.commons.collections4.map.HashedMap;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

/**
 * Created by YGU3747 on 18/10/2017
 */

public class ResolveMsgFactory {

    private static Map<String, Function<JSONObject, RabbitMsg>> factory = new HashedMap();

    private static Boolean initialization = false;

    private ResolveMsgFactory(){

    }

    public static Map<String, Function<JSONObject, RabbitMsg>> getFactory() {
        if (!initialization){
            //initialiser factory
            factory.put(MessageType.CONNECTION.toString(), json->{
                ObjectMapper objectMapper = new ObjectMapper();
                ConnectionMessage connectionMessage = new ConnectionMessage();
                try{
                    connectionMessage.setToken((String)json.get("token"))
                            .setSequence(UUID.fromString((String)json.get("sequence")))
                            .setNameFileResponse((String)json.get("nameFileResponse"))
                            .setMessageDate(new Date((long)json.get("messageDate")));
                    if(json.get("collaboratorDescription") != null){
                        connectionMessage.setUserDto(objectMapper.readValue(json.get("collaboratorDescription").toString(), UserDto.class));
                    }
                    return connectionMessage;
                }catch (IOException ioe){
                    throw new RuntimeException(ioe);
                }
            });
            initialization = true;
        }
        return factory;
    }
}
