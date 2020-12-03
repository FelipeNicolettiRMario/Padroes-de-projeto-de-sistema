package controller;

import com.google.gson.Gson;
import model.User;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import static spark.Spark.*;

public class UserController{

    private final User entity;
    public UserController(User entity) {
        this.entity = entity;
    }

    public void getById() {
        get("/user", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            var id = new ObjectId(request.queryParams("id"));

            var user = entity.read(id);

            if (user != null)
                return new Gson().toJson(user);
            else {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("status", 0);
                return jsonObj;
            }
        });
    }

    public void fetch() {
        get("/users", (request, response) -> {
            var users = entity.fetch();

            if (users != null)
                return new Gson().toJson(users);
            else {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("status", 0);
                return jsonObj;
            }
        });
    }

    public void add() {
        post("/user", (request, response) -> {
            JSONObject json = new JSONObject(request.body());

            var username = json.get("username").toString();
            var name = json.get("name").toString();
            var password = json.get("password").toString();

            var user = entity.create(username, name, password);

            if (user != null)
                return new Gson().toJson(user);
            else {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("status", 0);
                return jsonObj;
            }
        });
    }


    public void login() {
        post("/login", (request, response) -> {
            JSONObject json = new JSONObject(request.body());

            var username = json.get("username").toString();
            var password = json.get("password").toString();

            var user = entity.login(username, password);

            if (user != null)
                return new Gson().toJson(user);
            else {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("status", 404);
                return jsonObj;
            }
        });
    }

    public void setAdmin() {
        post("/user/admin", (request, response) -> {
            JSONObject json = new JSONObject(request.body());
            var id = (ObjectId) json.get("id");
            var admin = (boolean) json.get("admin");
            entity.setAdmin(admin, id);
            return null;
        });
    }

    public void update() {
        put("/user", (request, response) -> {
            JSONObject json = new JSONObject(request.body());
            var id = json.getJSONObject("_id");
            var timestamp = id.getInt("timestamp");
            var counter = id.getInt("counter");
            var objectId = new ObjectId(timestamp,counter);
            var admin = json.getBoolean("admin");
            entity.setAdmin(admin, objectId);
            return new Gson().toJson(json);
        });
    }

    public void remove() {
        delete("/user", (request, response) -> {
            JSONObject json = new JSONObject(request.body());
            var id = json.getJSONObject("_id");
            var timestamp = id.getInt("timestamp");
            var counter = id.getInt("counter");
            var objectId = new ObjectId(timestamp,counter);
            entity.delete(objectId);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("status", 200);
            return jsonObj;
        });
    }
}
