package org.example.payload.command;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.payload.command.Command;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("sendCommand")
public class SendCommand implements Command {

  private int userId;
  private int roomId;
  private String message;
}
