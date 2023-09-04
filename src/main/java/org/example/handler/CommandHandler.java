package org.example.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.payload.ConnectCommand;
import org.example.payload.Payload;
import org.example.service.ChatService;
import org.example.util.MapperUtil;

@NoArgsConstructor
@Sharable
@Slf4j
public class CommandHandler extends SimpleChannelInboundHandler<Payload> {

  private final ChatService chatService = ChatService.INSTANCE;

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Payload payload) throws Exception {
    switch (payload.getCommandType()) {
      case CONNECT:
        var connectCommand = MapperUtil.readValueOrThrow(payload.getBody(), ConnectCommand.class);
        chatService.connect(connectCommand, ctx.channel());
        log.info("CONNECT: {}", connectCommand);
        break;
      case DISCONNECT:
        break;
      case SEND_MESSAGE:
        break;
      default:
        break;
    }
  }
}
