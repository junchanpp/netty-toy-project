package org.example.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.payload.command.ConnectCommand;
import org.example.payload.command.DisconnectCommand;
import org.example.payload.command.JoinRoomCommand;
import org.example.payload.command.LeaveRoomCommand;
import org.example.payload.Payload;
import org.example.payload.command.SendCommand;
import org.example.service.ChatService;
import org.example.util.MapperUtil;

@AllArgsConstructor
@Sharable
@Slf4j
public class CommandHandler extends SimpleChannelInboundHandler<Payload> {

  private final ChatService chatService;


  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Payload payload) throws Exception {
    switch (payload.getCommandType()) {
      case CONNECT -> {
        var connectCommand = MapperUtil.readValueOrThrow(payload.getBody(), ConnectCommand.class);
        chatService.connect(connectCommand, ctx.channel());
        log.info("CONNECT: {}", connectCommand);
      }
      case DISCONNECT -> {
        var disconnectCommand = MapperUtil.readValueOrThrow(payload.getBody(),
            DisconnectCommand.class);
        chatService.disconnect(disconnectCommand, ctx.channel());
      }
      case SEND_MESSAGE -> {
        var sendCommand = MapperUtil.readValueOrThrow(payload.getBody(), SendCommand.class);
        chatService.send(sendCommand);
      }
      case JOIN_ROOM -> {
        var joinRoomCommand = MapperUtil.readValueOrThrow(payload.getBody(), JoinRoomCommand.class);
        chatService.joinRoom(joinRoomCommand, ctx.channel());
        chatService.getMessageHistory(joinRoomCommand.getRoomId(), ctx.channel());
      }
      case LEAVE_ROOM -> {
        var leaveRoomCommand = MapperUtil.readValueOrThrow(payload.getBody(),
            LeaveRoomCommand.class);
        chatService.leaveRoom(leaveRoomCommand, ctx.channel());
      }
      default -> {
        System.out.println("Unknown command type: " + payload.getCommandType());
      }
    }
  }
}
