package org.example.service;

import io.netty.channel.Channel;
import org.example.payload.ConnectCommand;
import org.example.payload.DisconnectCommand;
import org.example.payload.JoinRoomCommand;
import org.example.payload.LeaveRoomCommand;
import org.example.payload.SendCommand;

public interface ChatService {

  void connect(ConnectCommand connectCommand, Channel channel);

  void disconnect(DisconnectCommand disConnectCommand, Channel channel);

  void joinRoom(JoinRoomCommand joinRoomCommand, Channel channel);

  void leaveRoom(LeaveRoomCommand leaveRoomCommand, Channel channel);

  void send(SendCommand sendCommand);
}
