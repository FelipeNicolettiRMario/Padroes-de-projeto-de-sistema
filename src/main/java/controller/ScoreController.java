package controller;

//Importing the library to develop REST Architecture

import com.google.gson.Gson;
import model.Score;
import org.json.JSONObject;

import static spark.Spark.*;
import static spark.Spark.delete;

public class ScoreController {

    private final Score score;

    public ScoreController(Score score) {
        this.score = score;
    }

    public void add() {
        post("/score", (request, response) -> {

            //request is an architectural element with email and passaword
            //response is the json that this function returns
            response.header("Access-Control-Allow-Origin", "*");

            JSONObject json = new JSONObject(request.body());

            //if user exists, return it
            if (score != null) return new Gson().toJson(score);
            else { //if not, return a status 0

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("status", 0);

                return jsonObj;
            }

        });
    }

    public void update() {
        put("/score", (request, response) -> {

            JSONObject json = new JSONObject(request.body());
            if (score != null) return new Gson().toJson(score);
            else { //if not, return a status 0

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("status", 0);

                return jsonObj;
            }

        });
    }

    public void fetch() {
        get("/score", (request, response) -> {

            JSONObject json = new JSONObject(request.body());
            if (score != null) return new Gson().toJson(score);
            else { //if not, return a status 0

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("status", 0);

                return jsonObj;
            }

        });
    }

    public void remove() {
        delete("/score", (request, response) -> {

            JSONObject json = new JSONObject(request.body());
            if (score != null) return new Gson().toJson(score);
            else { //if not, return a status 0

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("status", 0);

                return jsonObj;
            }

        });
    }
}
