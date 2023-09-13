package org.example.repository;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoCollection;
import org.bson.Document;
import org.example.config.MongoDBConfig;
import org.example.util.SubscriberHelpers.PrintSubscriber;

public enum ChatRepositoryImpl implements ChatRepository {
  INSTANCE();

  private final MongoCollection<Document> chatCollection = MongoDBConfig.INSTANCE.getCollection("chat");

  @Override
  public void joinRoom(Integer roomId, Integer userId) {
    chatCollection.insertOne(new Document("roomId", roomId)
        .append("userId", userId)
        .append("type", "JOIN")
        .append("createdAt", System.currentTimeMillis()))
        .subscribe(new PrintSubscriber<>("joinRoom"));
  }

  @Override
  public void leaveRoom(Integer roomId, Integer userId) {
    chatCollection.insertOne(new Document("roomId", roomId)
        .append("userId", userId)
        .append("type", "LEAVE")
        .append("createdAt", System.currentTimeMillis()))
        .subscribe(new PrintSubscriber<>("leaveRoom"));
  }

  @Override
  public FindPublisher<Document> getChatHistory(Integer roomId) {
     return chatCollection.find(eq("roomId", roomId));
  }

  @Override
  public void sendMessage(Integer roomId, Integer userId, String message) {
    chatCollection.insertOne(new Document("roomId", roomId)
        .append("userId", userId)
        .append("type", "MESSAGE")
        .append("message", message)
        .append("createdAt", System.currentTimeMillis()))
        .subscribe(new PrintSubscriber<>("sendMessage"));
  }
}
