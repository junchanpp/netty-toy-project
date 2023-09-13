package org.example.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.payload.command.CommandType;

@Builder
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Payload implements Serializable {

  private CommandType commandType; // [2]
  private Object body; // [3]

  @JsonCreator
  public Payload(@JsonProperty("commandType") CommandType commandType,
      @JsonProperty("body") Object body) {
    this.commandType = commandType;
    this.body = body;
  }

}
