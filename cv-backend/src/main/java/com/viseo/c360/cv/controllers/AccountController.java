package com.viseo.c360.cv.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import com.viseo.c360.cv.amqp.ConnectionMessage;
import com.viseo.c360.cv.amqp.MessageType;
import com.viseo.c360.cv.amqp.RabbitMsg;
import com.viseo.c360.cv.amqp.ResolveMsgFactory;
import com.viseo.c360.cv.converters.UserDtoToEntityConverter;
import com.viseo.c360.cv.converters.UserEntityToDtoConverter;
import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;
import com.viseo.c360.cv.services.AccountService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Value("$(jwt.secret)")
    private String secret;

    @Inject
    Queue responseCV;

    @Inject
    RabbitTemplate rabbitTemplate;

    @Inject
    FanoutExchange fanout;

    private String compactJws;
    private static final Map<String, UserDto> mapUserCache = new ConcurrentHashMap<>();
    private boolean compteExisteInOtherApp = false;

    private String createSecurityToken(UserDto user){
        return Jwts.builder()
                .setSubject(user.getMail())
                .claim("admin", user.getAdmin())
                .claim("id", user.getId())
                .claim("login", user.getLogin())
                .claim("mail", user.getMail())
                //.signWith(SignatureAlgorithm.HS512, generateKey())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam(value = "mail") @NotEmpty String mail,
                             @RequestParam(value = "password") @NotEmpty String password) {
        UserDto receivedUser = this.checkIfUserExistElsewhere(mail, password);
        UsersEntity myUser = this.accountService.exist(mail, password);
        UserDto userFound = this.handleReceivedUser(myUser, receivedUser, password);
        compactJws = createSecurityToken(userFound);
        this.mapUserCache.put(compactJws, userFound);
        return compactJws;
    }

    @CrossOrigin (origins = "${server.front}")
    @RequestMapping("/logout")
    @ResponseBody
    public void logout(@RequestParam(value="token") String token){
        mapUserCache.remove(token);
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(path = "/register", method = POST)
    public String register(@RequestBody UserDto user){
        ConnectionMessage checkIfUserExist = new ConnectionMessage();
        UUID personalMessageSequence = UUID.randomUUID();
        checkIfUserExist.setUserDto(user)
                .setNameFileResponse(responseCV.getName())
                .setSequence(personalMessageSequence)
                .setMessageDate(new Date());
        ObjectMapper mapper = new ObjectMapper();
        try{
            rabbitTemplate.convertAndSend(fanout.getName(),"",mapper.writeValueAsString(checkIfUserExist));
        }catch (Exception e){
            e.printStackTrace();
        }

        this.rabbitTemplate.execute(new ChannelCallback<ConnectionMessage>() {
            @Override
            public ConnectionMessage doInRabbit(Channel channel) throws Exception {
                long startTime = System.currentTimeMillis();
                long elapsedTime = 0;
                ConnectionMessage mostRecentConsumerResponse = null;
                GetResponse consumerResponse;
                long deliveryTag;
                sleep();
                do{
                    elapsedTime = (new Date()).getTime() - startTime;
                    consumerResponse = channel.basicGet(responseCV.getName(),false);
                    if(consumerResponse != null){
                        deliveryTag = consumerResponse.getEnvelope().getDeliveryTag();
                        ConnectionMessage rabbitMessageResponse = new ObjectMapper().readValue(consumerResponse.getBody(), ConnectionMessage.class);
                        channel.basicAck(deliveryTag, true);
                        if (rabbitMessageResponse.getSequence().equals(personalMessageSequence)) {
                            if (mostRecentConsumerResponse == null ||
                                    rabbitMessageResponse.getUserDto().getLastUpdateDate()
                                            .after(mostRecentConsumerResponse.getUserDto().getLastUpdateDate())) {
                                mostRecentConsumerResponse = rabbitMessageResponse;
                            }
                        } else {
                            channel.basicPublish("", responseCV.getName(), null, consumerResponse.getBody());
                        }
                        System.out.println("compte existe!");
                        compteExisteInOtherApp = true;
                        return null;
                    }
                    else{
                        System.out.println("compte existe pas!");
                        compteExisteInOtherApp = false;
                    }
                }while (consumerResponse != null && elapsedTime < 2000);
                return mostRecentConsumerResponse;
            }
        });
        if (compteExisteInOtherApp){
            //if the email is already exist in other microservice
            throw new RuntimeException("email");
        }
        String tokenToReturn = addUserDirectly(user);
        return tokenToReturn;
    }

    private String addUserDirectly(UserDto user){
        try{
            user.setAdmin(false);
            this.accountService.add(new UserDtoToEntityConverter().convert(user));
            compactJws = createSecurityToken(user);
            this.mapUserCache.put(compactJws, user);
            return compactJws;
        }
        catch (DataIntegrityViolationException e){
            throw new RuntimeException(e.getCause().getCause());
        }
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(path = "/getUser", method = GET)
    public UsersEntity getUser(@RequestParam(value = "id") @NotEmpty int id) {

        return this.accountService.getUserById(id);
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(path = "/identification", method = GET)
    @ResponseBody
    public UsersEntity checkIsAlreadyConnected(@RequestParam("token") String token) {
        System.out.println("Request successfully received! Received token : " + token);
        try{
            token = token.replace("=", "");
            UserDto user = mapUserCache.get(token);
            if (user != null){
                return new UserDtoToEntityConverter().convert(user);
            }
            else{
                return null;
            }
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(path = "/getUsers", method = GET)
    public List<UsersEntity> getAll() {
        return this.accountService.getAll();
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(path = "/updateUser", method = POST)
    public UsersEntity updateUser(@RequestBody @Valid UserDto user) {
        return this.accountService.updateUser(new UserDtoToEntityConverter().convert(user));
    }

    @CrossOrigin (origins = "${server.front}")
    @RequestMapping(path = "/updateOnlyUserProfile", method = PUT)
    public UsersEntity updateOnlyUserProfile(@RequestBody UserDto user){
        return this.accountService.updateOnlyUserProfile(new UserDtoToEntityConverter().convert(user));
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(path = "/getuserifalreadyconnectedelsewhere", method = GET)
    public String getUserIfAlreadyConnectedElseWhere(@RequestParam("token") String theToken){
        try{
            ConnectionMessage request = new ConnectionMessage();
            UUID personalMessageSequence = UUID.randomUUID();
            request.setSequence(personalMessageSequence)
                    .setToken(theToken.substring(0, theToken.length() - 1)) //why there's a '=' at the end?
                    .setMessageDate(new Date())
                    .setNameFileResponse(responseCV.getName());
            ObjectMapper mapper = new ObjectMapper();
            rabbitTemplate.convertAndSend(fanout.getName(), "", mapper.writeValueAsString(request));
            ConnectionMessage connectedUser = this.rabbitTemplate.execute(new ChannelCallback<ConnectionMessage>() {
                @Override
                public ConnectionMessage doInRabbit(Channel channel) throws Exception {
                    long startTime = System.currentTimeMillis();
                    long elapsedTime = 0;
                    ConnectionMessage mostRecentConsumerResponse = null;
                    GetResponse consumerResponse;
                    long deliveryTag;
                    sleep();
                    do{
                        elapsedTime = (new Date()).getTime() - startTime;
                        consumerResponse = channel.basicGet(responseCV.getName(), false);
                        if(consumerResponse != null){
                            deliveryTag = consumerResponse.getEnvelope().getDeliveryTag();
                            channel.basicAck(deliveryTag, true);
                            JSONObject jo = (JSONObject) new JSONParser().parse(new String(consumerResponse.getBody(), StandardCharsets.UTF_8));
                            RabbitMsg rbtMsg = ResolveMsgFactory.getFactory().get(jo.get("type")).apply(jo);
                            if(rbtMsg.getType() == MessageType.CONNECTION){
                                ConnectionMessage rabbitMessageResponse = new ObjectMapper().readValue(consumerResponse.getBody(), ConnectionMessage.class);
                                if (rabbitMessageResponse.getSequence().equals(personalMessageSequence)){
                                    if (mostRecentConsumerResponse == null ||
                                            rabbitMessageResponse.getUserDto().getLastUpdateDate()
                                                .after(mostRecentConsumerResponse.getUserDto().getLastUpdateDate())){
                                        mostRecentConsumerResponse = rabbitMessageResponse;
                                    }
                                }
                                else{
                                    channel.basicPublish("", responseCV.getName(), null, consumerResponse.getBody());
                                }
                            }
                            else{
                                channel.basicPublish("", responseCV.getName(), null, consumerResponse.getBody());
                            }
                        }
                    } while (consumerResponse != null && elapsedTime < 2000);
                    return mostRecentConsumerResponse;
                }
            });
            if (connectedUser != null)
                return login(connectedUser.getUserDto().getMail(),connectedUser.getUserDto().getPassword());
            return null;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }


    private UserDto checkIfUserExistElsewhere(String mail, String password){
        UserDto inputUserDto = new UserDto();
        inputUserDto.setMail(mail);
        inputUserDto.setPassword(password);
        ObjectMapper mapperObj = new ObjectMapper();
        UUID personalMessageSequence = UUID.randomUUID();
        ConnectionMessage connectionMessage = new ConnectionMessage()
                .setUserDto(inputUserDto)
                .setNameFileResponse(responseCV.getName())
                .setSequence(personalMessageSequence)
                .setMessageDate(new Date());
        try{
            this.rabbitTemplate.convertAndSend(fanout.getName(),"",mapperObj.writeValueAsString(connectionMessage));
            ConnectionMessage mostRecentRemoteUser = null;
            mostRecentRemoteUser = this.rabbitTemplate.execute(new ChannelCallback<ConnectionMessage>() {
                @Override
                public ConnectionMessage doInRabbit(Channel channel) throws Exception {
                    long startTime = System.currentTimeMillis();
                    long elapsedTime = 0;
                    ConnectionMessage mostRecentConsumerResponse = null;
                    GetResponse consumerResponse;
                    long deliveryTag;
                    sleep();
                    do{
                        elapsedTime = (new Date()).getTime() - startTime;
                        consumerResponse = channel.basicGet(responseCV.getName(),false);
                        if(consumerResponse != null){
                            deliveryTag = consumerResponse.getEnvelope().getDeliveryTag();
                            channel.basicAck(deliveryTag, true);
                            JSONObject jo = (JSONObject) new JSONParser()
                                    .parse(new String(consumerResponse.getBody(), StandardCharsets.UTF_8));
                            RabbitMsg rbtMsg = ResolveMsgFactory.getFactory().get(jo.get("type")).apply(jo);
                            if(rbtMsg.getType() == MessageType.CONNECTION){
                                ConnectionMessage rabbitMessageResponse = new ObjectMapper()
                                        .readValue(consumerResponse.getBody(), ConnectionMessage.class);
                                if ((new Date().getTime() - rabbitMessageResponse.getMessageDate().getTime()) < 5000) {
                                    if (rabbitMessageResponse.getSequence().equals(personalMessageSequence)) {
                                        if (mostRecentConsumerResponse == null ||
                                                rabbitMessageResponse.getUserDto().getLastUpdateDate()
                                                        .after(mostRecentConsumerResponse.getUserDto().getLastUpdateDate())) {
                                            mostRecentConsumerResponse = rabbitMessageResponse;
                                        }
                                    } else {
                                        channel.basicPublish("", responseCV.getName(), null, consumerResponse.getBody());
                                    }
                                }
                            }
                            else{
                                channel.basicPublish("", responseCV.getName(), null, consumerResponse.getBody());
                            }
                        }
                    } while (consumerResponse != null && elapsedTime < 2000);
                    return mostRecentConsumerResponse;
                }
            });
            if (mostRecentRemoteUser != null)
                return mostRecentRemoteUser.getUserDto();
            return null;
        }
        catch(JsonProcessingException jpe){
            throw new RuntimeException(jpe);
        }
    }

    private UserDto handleReceivedUser(UsersEntity myUser,UserDto receivedUser, String password){
        UserDto addedUser;
        if (myUser == null){
            if(receivedUser.getPassword().equals(password)){
                receivedUser.setId(0);
                addedUser  = new UserEntityToDtoConverter().convert(this.accountService.add(new UserDtoToEntityConverter().convert(receivedUser)));
                System.out.println("Adding User : " + addedUser.getMail());
                return addedUser;
            }
            else{
                return null;
            }
        }
        else {
            UserDto storedUserDto = new UserEntityToDtoConverter().convert(myUser);
            if(receivedUser == null
                    || receivedUser.getFirstName() == null
                    || (storedUserDto.getPassword().equals(receivedUser.getPassword())
                        && storedUserDto.getLastUpdateDate().after(receivedUser.getLastUpdateDate()))){
                System.out.println("MOT DE PASSE IDENTIQUE OU PLUS RECENT");
                return storedUserDto;
            }
            else if ((password.equals(receivedUser.getPassword()))
                    && storedUserDto.getLastUpdateDate().before(receivedUser.getLastUpdateDate())){
                storedUserDto.setPassword(password);
                return new UserEntityToDtoConverter().convert(updateUser(storedUserDto));
            }
            else{
                System.out.println("MOT DE PASSE MOINS RECENT");
                return null;
            }
        }
    }

    void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    public UserDto checkIfAlreadyConnected(RabbitMsg msg) {
        try {
            UserDto user = null;
            if (msg.getType() == MessageType.CONNECTION) {
                ConnectionMessage message = (ConnectionMessage) msg;
                user = mapUserCache.get(message.getToken());
                if (user != null) {
                    ConnectionMessage response = new ConnectionMessage().setNameFileResponse(message.getNameFileResponse())
                            .setUserDto(user)
                            .setMessageDate(new Date())
                            .setSequence(message.getSequence());
                    ObjectMapper mapper = new ObjectMapper();
                    System.out.println("Collaborateur envoyé : " + mapper.writeValueAsString(response));
                    rabbitTemplate.convertAndSend(message.getNameFileResponse(), mapper.writeValueAsString(response));
                }
            }/* else if (msg.getType() == MessageType.DISCONNECTION) {
                String token = null;
                DisconnectionMessage message = (DisconnectionMessage) msg;
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA : " + message.getCollaboratorDescription());
                if(!message.getNameFileResponse().equals(this.responseCompetence.getName())){
                    CollaboratorDescription collaborator = message.getCollaboratorDescription();
                    for (String t : mapUserCache.keySet()){
                        if (mapUserCache.get(t).getEmail().equals(collaborator.getEmail())){
                            token = t;
                            System.out.println("Reomving token : " + token);
                            mapUserCache.remove(token);
                            compactJws = null;
                            break;
                        }
                    }
                }
            }*/
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
