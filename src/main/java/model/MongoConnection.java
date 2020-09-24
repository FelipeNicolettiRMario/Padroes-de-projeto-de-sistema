package model;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import lombok.Data;

@Data
public class MongoConnection {
    private final MongoClient client;
    private final MongoClientURI uri;
    private final String database;

    public MongoConnection(String connectionString, String database) {
        {
            this.uri = new MongoClientURI(connectionString);
            MongoClientURI uri = new MongoClientURI(connectionString);
            this.client = new MongoClient(uri);
            this.database = database;
        }
    }

    public MongoDatabase connect(){
        return this.client.getDatabase(this.database);
    }

    public void close() {
        client.close();
    }
}