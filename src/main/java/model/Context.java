package model;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Context {
    public MongoCollection<Document> users;
    public MongoCollection<Document> assemblies;
    public MongoCollection<Document> pieces;
    public MongoCollection<Document> scores;

    public Context(MongoConnection db) {
        this.users = db.connect().getCollection("user");
        this.assemblies = db.connect().getCollection("assembly");
        this.pieces = db.connect().getCollection("piece");
        this.scores = db.connect().getCollection("score");
    }
}
