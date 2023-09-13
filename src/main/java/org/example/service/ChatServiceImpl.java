package org.example.service;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import org.example.payload.ConnectCommand;
import org.example.payload.DisconnectCommand;
import org.example.payload.JoinRoomCommand;
import org.example.payload.LeaveRoomCommand;
import org.example.payload.SendCommand;
import org.example.repository.ChannelRepository;
import org.example.repository.ChannelRepositoryImpl;


public enum ChatServiceImpl implements ChatService {
  INSTANCE();
  private final ChannelRepository channelRepository = ChannelRepositoryImpl.INSTANCE;
  @Override
  public void connect(ConnectCommand connectCommand, Channel channel) {
    var roomIds = connectCommand.getRoomIds();

    System.out.println("connect roomIds: " + roomIds);
    roomIds.forEach(roomId -> {
      this.channelRepository.put(roomId, channel);
    });

    channel.closeFuture().addListener((ChannelFutureListener) future -> roomIds.forEach(roomId -> {
      this.channelRepository.remove(roomId, channel);
    }));
  }

  @Override
  public void disconnect(DisconnectCommand disconnectCommand, Channel channel) {
    System.out.println("disconnect");
    channel.close();
  }

  @Override
  public void send(SendCommand sendCommand) {
    System.out.println("send");
    this.channelRepository.send(sendCommand.getRoomId(), sendCommand.getMessage());
  }

  @Override
  public void joinRoom(JoinRoomCommand joinRoomCommand, Channel channel) {
    var roomId = joinRoomCommand.getRoomId();
    this.channelRepository.put(roomId, channel);
    System.out.println("joinRoom: " + roomId);

    channel.closeFuture().addListener((ChannelFutureListener) future -> {
      this.channelRepository.remove(roomId, channel);
    });
  }

  @Override
  public void leaveRoom(LeaveRoomCommand leaveRoomCommand, Channel channel) {
    var roomId = leaveRoomCommand.getRoomId();
    System.out.println("leaveRoom: " + roomId);
    this.channelRepository.remove(roomId, channel);
  }
}
