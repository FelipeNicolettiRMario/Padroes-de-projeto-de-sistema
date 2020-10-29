package controller;

import com.google.gson.Gson;
import model.User;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import static spark.Spark.post;

public class UserController extends GeneralController<User> {

    public UserController(User entity) {
        super("/user", entity);
    }

    @Override
    protected User addEntity(JSONObject jsonObj) {
        String username = jsonObj.getString("username");
        String name = jsonObj.getString("name");
        String password = jsonObj.getString("password");
        return entity.create(new User(username, name, password));
    }

    public void login() {
        post("/login", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            JSONObject json = new JSONObject(request.body());

            var username = json.get("username").toString();
            var password = json.get("password").toString();

            var user = entity.login(username, password);

            if (user != null)
                return new Gson().toJson(user);
            else {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("status", 0);
                return jsonObj;
            }
        });
    }

    public void setAdmin() {
        post("/user/admin", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            JSONObject json = new JSONObject(request.body());
            var id = (ObjectId) json.get("id");
            var admin = (boolean) json.get("admin");
            entity.setAdmin(admin, id);
            return null;
        });
    }

}
