package com.viseo.c360.cv.amqp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.viseo.c360.cv.models.dto.UserDto;

import javax.inject.Inject;
import java.util.Date;
import java.util.UUID;

/**
 * Created by YGU3747 on 28/11/2017
 */

public class ConnectionMessage extends RabbitMsg{

    @Inject
    private UserDto userDto;

    private UUID sequence;

    private String nameFileResponse;

    private Date messageDate;

    private String token;

    public ConnectionMessage() {
        super(MessageType.CONNECTION);
    }

    @JsonProperty("collaboratorDescription")
    public UserDto getUserDto() {
        return userDto;
    }

    @JsonProperty("collaboratorDescription")
    public ConnectionMessage setUserDto(UserDto userDto) {
        this.userDto = userDto;
        return this;
    }

    public UUID getSequence() {
        return sequence;
    }

    public ConnectionMessage setSequence(UUID sequence) {
        this.sequence = sequence;
        return this;
    }

    public String getNameFileResponse() {
        return nameFileResponse;
    }

    public ConnectionMessage setNameFileResponse(String nameFileResponse) {
        this.nameFileResponse = nameFileResponse;
        return this;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public ConnectionMessage setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
        return this;
    }

    public String getToken() {
        return token;
    }

    public ConnectionMessage setToken(String token) {
        this.token = token;
        return this;
    }
}
