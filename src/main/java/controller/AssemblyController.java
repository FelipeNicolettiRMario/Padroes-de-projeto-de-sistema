package controller;

//Importing the library to develop REST Architecture

import com.google.gson.Gson;
import model.Assembly;
import org.json.JSONObject;

import static spark.Spark.*;

public class AssemblyController {

    private final Assembly assembly;

    public AssemblyController(Assembly assembly) {
        this.assembly = assembly;
    }

    public void add() {
        post("/assembly", (request, response) -> {

            //request is an architectural element with email and passaword
            //response is the json that this function returns
            response.header("Access-Control-Allow-Origin", "*");

            JSONObject json = new JSONObject(request.body());

//            String email = json.getString("email");
//
//            String password = json.getString("password");
//
//            User user = assembly.login(email, password);

            //if user exists, return it
            if (assembly != null) return new Gson().toJson(assembly);
            else { //if not, return a status 0

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("status", 0);

                return jsonObj;
            }

        });
    }

    public void update() {
        put("/assembly", (request, response) -> {

            JSONObject json = new JSONObject(request.body());
            if (assembly != null) return new Gson().toJson(assembly);
            else { //if not, return a status 0

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("status", 0);

                return jsonObj;
            }

        });
    }

    public void fetch() {
        get("/assembly", (request, response) -> {

            JSONObject json = new JSONObject(request.body());
            if (assembly != null) return new Gson().toJson(assembly);
            else { //if not, return a status 0

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("status", 0);

                return jsonObj;
            }

        });
    }

    public void remove() {
        delete("/assembly", (request, response) -> {

            JSONObject json = new JSONObject(request.body());
            if (assembly != null) return new Gson().toJson(assembly);
            else { //if not, return a status 0

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("status", 0);

                return jsonObj;
            }

        });
    }
}
