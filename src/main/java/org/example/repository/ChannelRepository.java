package org.example.repository;

import io.netty.channel.Channel;

public interface ChannelRepository {

  void put(Integer roomId, Channel channel);

  void remove(Integer roomId, Channel channel);

  void send(Integer roomId, String message);
}
