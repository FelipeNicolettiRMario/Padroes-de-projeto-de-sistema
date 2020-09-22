package model;

import com.mongodb.client.ClientSession;
import lombok.Data;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

@Data
public class User extends Document {

    private ObjectId id;
    private String username;
    private String name;
    private String password;
    private boolean admin;
    private Context context;

    public User(Context context) {
        this.context = context;
    }

    public User addUser(String username, String name, String password) {
        this.password = password;
        this.name = name;
        this.username = username;
        this.admin = false;
        context.users.insertOne(this);
        return context.users.find((ClientSession) eq("username", this.username), eq("password", this.password)).first();
    }

    public User login(String username, String password) {
        return context.users.find((ClientSession) eq("username", username), eq("password", password)).first();
    }

}
