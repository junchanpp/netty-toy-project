package org.example.service;

import io.netty.channel.Channel;
import java.util.List;
import org.example.payload.command.ConnectCommand;
import org.example.payload.command.DisconnectCommand;
import org.example.payload.command.JoinRoomCommand;
import org.example.payload.command.LeaveRoomCommand;
import org.example.payload.command.ReceiveCommand;
import org.example.payload.command.SendCommand;

public interface ChatService {

  void connect(ConnectCommand connectCommand, Channel channel);

  void disconnect(DisconnectCommand disConnectCommand, Channel channel);

  void joinRoom(JoinRoomCommand joinRoomCommand, Channel channel);

  void leaveRoom(LeaveRoomCommand leaveRoomCommand, Channel channel);

  void getMessageHistory(Integer roomId, Channel channel);

  void send(SendCommand sendCommand);
}
