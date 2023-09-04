package org.example.codec;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.payload.Payload;
import org.example.util.MapperUtil;

@NoArgsConstructor
@Sharable
@Slf4j
public class PayloadDecoder extends MessageToMessageDecoder<WebSocketFrame> {

  @Override
  protected void decode(ChannelHandlerContext ctx, WebSocketFrame frame, List<Object> out)
      throws Exception {
    if (frame instanceof TextWebSocketFrame) {
      String msg = ((TextWebSocketFrame) frame).text();
      Payload payload = MapperUtil.readValueOrThrow(msg, Payload.class);
      out.add(payload);
    }

  }
}
