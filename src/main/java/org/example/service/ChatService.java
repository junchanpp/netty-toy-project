package org.example.service;

import io.netty.channel.Channel;
import org.example.payload.ConnectCommand;
import org.example.payload.DisconnectCommand;
import org.example.payload.JoinRoomCommand;
import org.example.payload.LeaveRoomCommand;
import org.example.payload.SendCommand;

public interface ChatService {
  public void connect(ConnectCommand connectCommand, Channel channel);
  public void disconnect(DisconnectCommand disConnectCommand, Channel channel);

  public void joinRoom(JoinRoomCommand joinRoomCommand, Channel channel);
  public void leaveRoom(LeaveRoomCommand leaveRoomCommand, Channel channel);
  public void send(SendCommand sendCommand);
}
