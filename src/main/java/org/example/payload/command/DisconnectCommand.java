package org.example.payload.command;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("disconnectCommand")
public class DisconnectCommand implements Command {

  private int userId;
  private List<Integer> roomId;
}
