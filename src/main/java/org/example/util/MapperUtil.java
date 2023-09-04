package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtil {

  private static final ObjectMapper mapper = new ObjectMapper();

  public static <T> T readValueOrThrow(String msg, Class<T> clazz) throws JsonProcessingException {
    return mapper.readValue(msg, clazz);
  }

  public static <T> T readValueOrThrow(Object object, Class<T> clazz) {
    return mapper.convertValue(object, clazz);
  }
}
