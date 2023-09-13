package org.example.repository;

import io.netty.channel.Channel;
import java.util.Collection;
import org.example.payload.Command;

public interface ChannelRepository {
  public void put(Integer roomId, Channel channel);
  public void remove(Integer roomId, Channel channel);

  public void send(Integer roomId, String message);
}
