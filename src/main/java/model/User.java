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
public class User extends Document implements IEntity<User> {

	/**
	 *
	 */
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

	public User(String username, String name, String password) {
		this.password = password;
		this.name = name;
		this.username = username;
		this.admin = false;
	}

	public User create(User user) {
		User existUser = context.users.find(eq("username", username), User.class).first();

		if (existUser == null) {
			user.setId(new ObjectId());
			context.users.insertOne(user);
			return context.users.find(eq("_id", user.getId()), User.class).first();
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

	public User read(ObjectId id) {
		return context.users.find(eq("_id", id), User.class).first();
	}

	public void delete(ObjectId id) {
		context.users.deleteOne(eq("_id", id));
	}

	public void setAdmin(boolean admin, ObjectId id) {
		context.users.findOneAndUpdate(eq("_id", id), new Document().append("admin", admin));
	}

	public User login(String username, String password) {
		return context.users.find((ClientSession) eq("username", username), eq("password", password), User.class).first();
	}
}
