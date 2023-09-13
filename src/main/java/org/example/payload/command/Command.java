package org.example.payload.command;

import java.io.Serializable;

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
//@JsonSubTypes({
//    @JsonSubTypes.Type(value = ConnectCommand.class, name = "connectCommand"),
//    @JsonSubTypes.Type(value = SendMessageCommand.class, name = "sendMessageCommand"),
//})
public interface Command extends Serializable {

}
