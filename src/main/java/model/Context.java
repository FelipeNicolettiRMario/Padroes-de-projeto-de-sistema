package model;

import com.mongodb.client.MongoCollection;

public class Context {
    public MongoCollection<User> users;
    public MongoCollection<Assembly> assemblies;
    public MongoCollection<Piece> pieces;
    public MongoCollection<Score> scores;

    public Context(MongoConnection mongoConnection){
        this.users = mongoConnection.getDatabase().getCollection("user", User.class);
        this.assemblies = mongoConnection.getDatabase().getCollection("assembly", Assembly.class);
        this.pieces = mongoConnection.getDatabase().getCollection("piece", Piece.class);
        this.scores = mongoConnection.getDatabase().getCollection("score", Score.class);
    }
}
