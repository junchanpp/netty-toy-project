package org.example.payload;

public class SendMessageCommand implements Command {

  private int userId;
  private int roomId;
  private String message;
}
