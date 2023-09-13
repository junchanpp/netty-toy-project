package org.example.repository;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public enum ChannelRepositoryImpl implements ChannelRepository {
  INSTANCE();

  private final ConcurrentMap<Integer, Set<Channel>> channelCache = new ConcurrentHashMap<Integer, Set<Channel>>();

  @Override
  public void put(Integer roomId, Channel channel) {
    channelCache.putIfAbsent(roomId, new HashSet<Channel>());
    channelCache.get(roomId).add(channel);
  }

  @Override
  public void remove(Integer roomId, Channel channel) {
    channelCache.get(roomId).remove(channel);
  }

  @Override
  public void send(Integer roomId, String message) {
    System.out.println("Sending message: " + message);
    System.out.println("size: " + channelCache.get(roomId).size());
    channelCache.get(roomId).forEach(channel -> {
      channel.writeAndFlush(
          new TextWebSocketFrame(message));
    });
  }
}
