package model;

import com.mongodb.client.FindIterable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.LinkedList;

import static com.mongodb.client.model.Filters.eq;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends Document implements IEntity<User> {
    private static final long serialVersionUID = -6296747250484034222L;
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

    public User() {

    }

    public Document create(String username, String name, String password) {
        Document existUser = context.users.find(eq("username", username)).first();

        if (existUser == null) {
            Document user = new Document();
            user.append("_id", new ObjectId());
            user.append("username", username);
            user.append("name", name);
            user.append("password", password);
            user.append("isAdmin", false);
            context.users.insertOne(user);
            return context.users.find(eq("_id", user.getObjectId("_id"))).first();
        }

        return existUser;
    }

    public User update(User updatedUser) {
        User user = context.users.find(eq("_id", id), User.class).first();
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        updatedUser.setId(user.getId());
        context.users.updateOne(eq("_id", user.getId()), updatedUser);
        return context.users.find(eq("_id", user.getId()), User.class).first();
    }

    @Override
    public LinkedList<Document> fetch() {
        var cursor = context.users.find().cursor();
        var list = new LinkedList<Document>();
        while (cursor.hasNext()) {
            var document = cursor.next();
            list.add(document);
        }
        return list;
    }

    public Document read(ObjectId id) {
        return context.users.find(eq("_id", id)).first();
    }

    public void delete(ObjectId id) {
        context.users.deleteOne(eq("_id", id));
    }

    public void setAdmin(boolean admin, ObjectId id) {
        context.users.findOneAndUpdate(eq("_id", id), new Document().append("admin", admin));
    }

    public Document login(String username, String password) {
        Document user;
        user = context.users.find(eq("username", username))
                .filter(eq("password", password)).first();
        return user;
    }
}
