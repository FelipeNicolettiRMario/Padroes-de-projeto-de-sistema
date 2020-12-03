package model;

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
    @BsonProperty(value = "_id")
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

    public Document create(String username, String name, String password) {
        Document existUser = context.users.find(eq("username", username)).first();

        if (existUser == null) {
            Document user = new Document();
            user.append("_id", new ObjectId());
            user.append("username", username);
            user.append("name", name);
            user.append("password", password);
            user.append("admin", false);
            context.users.insertOne(user);

            var id =  user.getObjectId("_id");
            return context.users.find(eq("_id",id)).first();
        }

        return existUser;
    }

    public Document update(User updatedUser) {
        Document user = context.users.find(eq("_id", updatedUser.getId())).first();
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        context.users.updateOne(eq("_id", updatedUser.getId()), updatedUser);
        return context.users.find(eq("_id", updatedUser.getId())).first();
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
        Document update = new Document();
        update.put("$set", new Document("admin",admin));

       context.users.updateOne(eq("_id", id), update);
    }

    public Document login(String username, String password) {
        Document user;
        user = context.users.find(eq("username", username))
                .filter(eq("password", password)).first();
        return user;
    }
}
