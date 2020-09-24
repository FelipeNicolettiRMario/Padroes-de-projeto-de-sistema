package model;

import com.mongodb.client.ClientSession;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends Document {

    @BsonProperty(value = "id")
    private ObjectId id;
    @BsonProperty(value = "username")
    private String username;
    @BsonProperty(value = "name")
    private String name;
    @BsonProperty(value = "password")
    private String password;
    @BsonProperty(value = "admin")
    private boolean admin;

    private Context context;

    public User(Context context) {
        this.context = context;
    }

    public User(String username, String name, String password) {
        this.password = password;
        this.name = name;
        this.username = username;
        this.admin = false;
    }

    public Document addUser(String username, String name, String password) {
        Document user = new Document("_id", new ObjectId());
        user.append("username", username)
                .append("name", name)
                .append("password", password)
                .append("admin", false);
        context.users.insertOne(user);

        return context.users.find(eq("username", username)).first();
    }

    public Document login(String username, String password) {
        return context.users.find((ClientSession) eq("username", username), eq("password", password)).first();
    }

}
