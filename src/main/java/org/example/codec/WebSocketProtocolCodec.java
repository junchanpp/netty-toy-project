package org.example.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.example.handler.CommandHandler;

@Slf4j
public class WebSocketProtocolCodec extends MessageToMessageCodec<String, String> {

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    log.info("userEventTriggered: {}", evt);
    if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
      log.info("WebSocket handshake complete");
      ctx.pipeline()
          .addLast(new PayloadDecoder())
          .addLast(new CommandHandler())
      ;
    } else {
      super.userEventTriggered(ctx, evt);
    }
  }

  @Override
  protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {

  }

  @Override
  protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {

  }
}
