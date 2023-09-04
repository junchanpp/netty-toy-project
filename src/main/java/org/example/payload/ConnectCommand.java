package org.example.payload;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("connectCommand")
public class ConnectCommand implements Command, Serializable {

  private int userId;
  private List<Integer> roomIds;
}
