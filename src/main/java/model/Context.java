package model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Context {
    public MongoCollection<Document> users;
    public MongoCollection<Document> assemblies;
    public MongoCollection<Document> pieces;
    public MongoCollection<Document> scores;
    public MongoDatabase db;

    public Context(MongoConnection db) {
        this.db = db.connect();
        this.users = this.db.getCollection("user");
        this.assemblies = this.db.getCollection("assembly");
        this.pieces = this.db.getCollection("piece");
        this.scores = this.db.getCollection("score");
    }
}
