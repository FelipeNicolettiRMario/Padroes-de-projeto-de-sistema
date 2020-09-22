package model;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.Data;

@Data
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
    public void close(){
        mongoClient.close();
    }
}