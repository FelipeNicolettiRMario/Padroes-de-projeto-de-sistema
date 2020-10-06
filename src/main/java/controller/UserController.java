package controller;

//Importing the library to develop REST Architecture

import com.google.gson.Gson;
import model.User;
import org.bson.Document;
import org.json.JSONObject;

import static spark.Spark.*;
import static spark.Spark.delete;

public class UserController {

    private final User user;

    public UserController(User user) {
        this.user = user;
    }

    public void add() {
        post("/user", (request, response) -> {

            try {
                response.header("Access-Control-Allow-Origin", "*");
                JSONObject json = new JSONObject(request.body());

                String username = json.getString("username");
                String name = json.getString("name");
                String password = json.getString("password");
                Document newUser = this.user.addUser(username, name, password);

                if (newUser != null) return new Gson().toJson(newUser);
                else {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("status", 0);
                    return jsonObj;
                }
            } catch (Exception e) {
                throw e;
            }

        });
    }

    public void update() {
        put("/user", (request, response) -> {

            JSONObject json = new JSONObject(request.body());
            if (user != null) return new Gson().toJson(user);
            else { //if not, return a status 0

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("status", 0);

                return jsonObj;
            }

        });
    }

    public void fetch() {
        get("/user", (request, response) -> {

            JSONObject json = new JSONObject(request.body());
            if (user != null) return new Gson().toJson(user);
            else { //if not, return a status 0

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("status", 0);

                return jsonObj;
            }

        });
    }

    public void remove() {
        delete("/user", (request, response) -> {

            JSONObject json = new JSONObject(request.body());
            if (user != null) return new Gson().toJson(user);
            else { //if not, return a status 0

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("status", 0);

                return jsonObj;
            }

        });
    }
}
