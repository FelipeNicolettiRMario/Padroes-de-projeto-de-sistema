package model;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class MongoConnection {
    private final MongoClient mongoClient;
    private final MongoDatabase database;


    public MongoConnection(String connectionString, String databaseName) {
        ConnectionString connectionString1 = new ConnectionString(connectionString);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString1)
                .retryWrites(true)
                .build();

        this.mongoClient = MongoClients.create(settings);
        this.database = mongoClient.getDatabase(databaseName);
    }

    public MongoCollection<Document> getCollection(String collection){
        return database.getCollection(collection);
    }
    public void close(){
        mongoClient.close();
    }
}