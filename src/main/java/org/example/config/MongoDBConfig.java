package org.example.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.Document;

public enum MongoDBConfig {
  INSTANCE(MongoClients.create("mongodb://localhost:27017"));

  private final MongoClient mongoClient;
  private final MongoDatabase mongoDatabase;

  MongoDBConfig(MongoClient mongoClient) {
    this.mongoClient = mongoClient;
    this.mongoDatabase = mongoClient.getDatabase("chat");
  }

  public MongoCollection<Document> getCollection(String collectionName) {
    return mongoDatabase.getCollection(collectionName);
  }
}
