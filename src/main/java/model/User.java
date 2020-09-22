package model;

import lombok.Data;

@Data

public class User {

    private String username;
    private String name;
    private String password;
    private boolean admin;
    private MongoConnection mongoConnection;

    public User(MongoConnection mongoConnection) {
        this.mongoConnection = mongoConnection;
    }

    public User(String username, String name, String password, boolean admin) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.admin = admin;
    }
}
