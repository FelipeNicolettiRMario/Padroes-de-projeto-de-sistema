package controller;

import com.google.gson.Gson;
import model.Score;
import org.json.JSONObject;

import static spark.Spark.post;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.patch;

public class ScoreController {

    private Score entity;

    public ScoreController(Score entity) {
        this.entity = entity;
    }

    public void add() {
        post("/score", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            JSONObject json = new JSONObject(request.body());
            Gson gson = new Gson();

            var user = json.getString("user");
            var assembly = json.getString("assembly");
            var tentativas = json.getInt("tentativas");
            var aproveitamento = json.getDouble("aproveitamento");
            var erros = json.getInt("erros");
            var ordem = json.getJSONArray("ordem");
            var acertos = json.getInt("acertos");
            var date = json.getString("date");

           if (user != null)
               return new Gson().toJson(user);
           else {
               JSONObject jsonObj = new JSONObject();
               jsonObj.put("status", 0);
               return jsonObj;
           }
        });
    }

    public void remove() {
        delete("/score", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            JSONObject json = new JSONObject(request.body());
            Gson gson = new Gson();

            var user = json.getString("user");
            var assembly = json.getString("assembly");
            var tentativas = json.getInt("tentativas");
            var aproveitamento = json.getDouble("aproveitamento");
            var erros = json.getInt("erros");
            var ordem = json.getJSONArray("ordem");
            var acertos = json.getInt("acertos");
            var date = json.getString("date");

           if (user != null)
               return new Gson().toJson(user);
           else {
               JSONObject jsonObj = new JSONObject();
               jsonObj.put("status", 0);
               return jsonObj;
           }
        });
    }

    public void update() {
        patch("/score", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            JSONObject json = new JSONObject(request.body());
            Gson gson = new Gson();

            var user = json.getString("user");
            var assembly = json.getString("assembly");
            var tentativas = json.getInt("tentativas");
            var aproveitamento = json.getDouble("aproveitamento");
            var erros = json.getInt("erros");
            var ordem = json.getJSONArray("ordem");
            var acertos = json.getInt("acertos");
            var date = json.getString("date");

           if (user != null)
               return new Gson().toJson(user);
           else {
               JSONObject jsonObj = new JSONObject();
               jsonObj.put("status", 0);
               return jsonObj;
           }
        });
    }

    public void all() {
        get("/score", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            JSONObject json = new JSONObject(request.body());
            Gson gson = new Gson();

            var user = json.getString("user");
            var assembly = json.getString("assembly");
            var tentativas = json.getInt("tentativas");
            var aproveitamento = json.getDouble("aproveitamento");
            var erros = json.getInt("erros");
            var ordem = json.getJSONArray("ordem");
            var acertos = json.getInt("acertos");
            var date = json.getString("date");

           if (user != null)
               return new Gson().toJson(user);
           else {
               JSONObject jsonObj = new JSONObject();
               jsonObj.put("status", 0);
               return jsonObj;
           }
        });
    }
}
