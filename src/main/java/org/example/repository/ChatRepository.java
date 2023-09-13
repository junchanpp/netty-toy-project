package org.example.repository;

import com.mongodb.reactivestreams.client.FindPublisher;
import org.bson.Document;

public interface ChatRepository {
  public void joinRoom(Integer roomId, Integer userId);
  public void leaveRoom(Integer roomId, Integer userId);
  public FindPublisher<Document> getChatHistory(Integer roomId);
  public void sendMessage(Integer roomId, Integer userId, String message);
}
