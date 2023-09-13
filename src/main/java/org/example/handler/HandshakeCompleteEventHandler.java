package org.example.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleUserEventChannelHandler;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler.HandshakeComplete;
import lombok.extern.slf4j.Slf4j;
import org.example.codec.PayloadDecoder;
import org.example.service.ChatServiceImpl;

@Slf4j
public class HandshakeCompleteEventHandler extends
    SimpleUserEventChannelHandler<HandshakeComplete> {

  @Override
  protected void eventReceived(ChannelHandlerContext ctx, HandshakeComplete evt) {
    log.info("WebSocket handshake complete");
    //handshake할 때는 payload를 보내지 않음.
    ctx.pipeline()
        .addLast(new PayloadDecoder())
        .addLast(new CommandHandler(ChatServiceImpl.INSTANCE))
    ;
  }

}
