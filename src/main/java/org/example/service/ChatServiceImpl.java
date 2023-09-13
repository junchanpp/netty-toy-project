package org.example.service;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import java.util.List;
import javax.swing.text.Document;
import org.example.payload.command.ConnectCommand;
import org.example.payload.command.DisconnectCommand;
import org.example.payload.command.JoinRoomCommand;
import org.example.payload.command.LeaveRoomCommand;
import org.example.payload.command.ReceiveCommand;
import org.example.payload.command.SendCommand;
import org.example.repository.ChannelRepository;
import org.example.repository.ChannelRepositoryImpl;
import org.example.repository.ChatRepository;
import org.example.repository.ChatRepositoryImpl;
import org.example.util.SubscriberHelpers.ObservableSubscriber;
import org.example.util.SubscriberHelpers.PrintDocumentSubscriber;
import org.reactivestreams.Subscriber;


public enum ChatServiceImpl implements ChatService {
  INSTANCE();
  private final ChannelRepository channelRepository = ChannelRepositoryImpl.INSTANCE;
  private final ChatRepository chatRepository = ChatRepositoryImpl.INSTANCE;

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
    this.channelRepository.send(sendCommand.getRoomId(), sendCommand.getMessage());
    this.chatRepository.sendMessage(sendCommand.getRoomId(), sendCommand.getUserId(), sendCommand.getMessage());
  }

  @Override
  public void joinRoom(JoinRoomCommand joinRoomCommand, Channel channel) {
    var roomId = joinRoomCommand.getRoomId();
    this.channelRepository.put(roomId, channel);
    this.chatRepository.joinRoom(roomId, joinRoomCommand.getUserId());

    channel.closeFuture().addListener((ChannelFutureListener) future -> {
      this.channelRepository.remove(roomId, channel);
    });
  }

  @Override
  public void leaveRoom(LeaveRoomCommand leaveRoomCommand, Channel channel) {
    var roomId = leaveRoomCommand.getRoomId();

    this.channelRepository.remove(roomId, channel);
    this.chatRepository.leaveRoom(roomId, leaveRoomCommand.getUserId());
  }

  @Override
  public void getMessageHistory(Integer roomId, Channel channel) {
    this.chatRepository.getChatHistory(roomId).subscribe(new PrintDocumentSubscriber());
  }
}
