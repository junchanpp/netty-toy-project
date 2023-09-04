package org.example.service;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.example.payload.ConnectCommand;


public enum ChatService {
  INSTANCE();
  private final ConcurrentMap<Integer, Set<Channel>> channels = new ConcurrentHashMap<Integer, Set<Channel>>();

  public void connect(ConnectCommand connectCommand, Channel channel) {
    var roomIds = connectCommand.getRoomIds();
    var userId = connectCommand.getUserId();
    roomIds.forEach(roomId -> {
      var channelSet = this.channels.get(roomId);

      if (channelSet == null) {
        channelSet = new HashSet<Channel>();
        Set<Channel> prevChannels = channels.putIfAbsent(roomId, channelSet);
        if (prevChannels != null) {
          channelSet = prevChannels;
        }
      }

      channelSet.add(channel);
    });
    channel.closeFuture().addListener((ChannelFutureListener) future -> roomIds.forEach(roomId -> {
      channels.get(roomId).remove(channel);
    }));
    roomIds.forEach(roomId -> {
      var channelSets = channels.get(roomId);
      channelSets.forEach(channelSet -> {
        channelSet.writeAndFlush(
            new TextWebSocketFrame("User " + userId + " joined room " + roomId));
      });
    });
  }

  public void disconnect(List<Integer> roomIds, Channel channel) {
    channel.close();
  }
}
