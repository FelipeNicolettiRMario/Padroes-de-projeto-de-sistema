package controller;

//Importing the library to develop REST Architecture

import com.google.gson.Gson;
import model.Piece;
import org.json.JSONObject;

import static spark.Spark.*;
import static spark.Spark.delete;

public class PieceController {

    private final Piece piece;

    public PieceController(Piece piece) {
        this.piece = piece;
    }

    public void add() {
        post("/piece", (request, response) -> {

            //request is an architectural element with email and passaword
            //response is the json that this function returns
            response.header("Access-Control-Allow-Origin", "*");

            JSONObject json = new JSONObject(request.body());

            //if user exists, return it
            if (piece != null) return new Gson().toJson(piece);
            else { //if not, return a status 0

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("status", 0);

                return jsonObj;
            }

        });
    }

    public void update() {
        put("/piece", (request, response) -> {

            JSONObject json = new JSONObject(request.body());
            if (piece != null) return new Gson().toJson(piece);
            else { //if not, return a status 0

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("status", 0);

                return jsonObj;
            }

        });
    }

    public void fetch() {
        get("/piece", (request, response) -> {

            JSONObject json = new JSONObject(request.body());
            if (piece != null) return new Gson().toJson(piece);
            else { //if not, return a status 0

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("status", 0);

                return jsonObj;
            }

        });
    }

    public void remove() {
        delete("/piece", (request, response) -> {

            JSONObject json = new JSONObject(request.body());
            if (piece != null) return new Gson().toJson(piece);
            else { //if not, return a status 0

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("status", 0);

                return jsonObj;
            }

        });
    }
}
