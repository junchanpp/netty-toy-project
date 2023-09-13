package org.example.payload.command;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("leaveRoomCommand")
public class LeaveRoomCommand implements Command {

  private int userId;
  private int roomId;
}
