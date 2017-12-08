package com.viseo.c360.cv.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.viseo.c360.cv.controllers.AccountController;
import com.viseo.c360.cv.converters.UserEntityToDtoConverter;
import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;
import com.viseo.c360.cv.services.AccountService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.inject.Inject;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by YGU3747 on 24/11/2017
 */
@Component
public class ConsumerMessageHandler {

    @Inject
    RabbitTemplate rabbitTemplate;

    @Inject
    Queue responseFormation;

    @Inject
    Queue responseCompetence;

    @Inject
    Queue responseCV;

    @Inject
    AccountController ws;

    @Autowired
    private AccountService accountService;

    private Map<String, Function<JSONObject, RabbitMsg>> factory = ResolveMsgFactory.getFactory();

    public void handleMessage(String request) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        System.out.println("RECEIVE MESSAGE : " + request);
        try{
            JSONObject jo = (JSONObject) new JSONParser().parse(request);
            RabbitMsg rabbitMsgResponse = factory.get(jo.get("type")).apply(jo);
            if (rabbitMsgResponse instanceof ConnectionMessage){
                ConnectionMessage connectionMessageResponse = (ConnectionMessage) rabbitMsgResponse;
                UserDto collaborator = connectionMessageResponse.getUserDto();
                System.out.println("Halelujah j'ai reçu ça   : " + request);
                if (connectionMessageResponse.getToken() != null) {
                    ws.checkIfAlreadyConnected(connectionMessageResponse);
                } else  {
                    UsersEntity c = this.accountService.mailExist(collaborator.getMail());
                    if (c != null){
                        connectionMessageResponse.setUserDto(new UserEntityToDtoConverter().convert(c));
                        if (!connectionMessageResponse.getNameFileResponse().equals(responseCV.getName())) {
                            ObjectMapper mapper = new ObjectMapper();
                            mapper.registerModule(new Hibernate5Module());
                            try{
                                rabbitTemplate.convertAndSend(connectionMessageResponse.getNameFileResponse(), mapper.writeValueAsString(connectionMessageResponse));
                                System.out.println("Collaborateur envoyé : " + mapper.writeValueAsString(connectionMessageResponse));
                            }catch (JsonProcessingException e){
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }

        }catch (ParseException pe){
            pe.printStackTrace();
        }
    }

}
