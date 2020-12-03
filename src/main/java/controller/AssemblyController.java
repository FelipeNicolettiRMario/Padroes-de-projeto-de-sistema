package controller;

import com.google.gson.Gson;
import model.Assembly;
import model.Piece;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class AssemblyController {

	private final Assembly entity;
	public AssemblyController(Assembly entity) {
		this.entity = entity;
	}
	public void getById() {
		get("/assembly", (request, response) -> {
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
		get("/assemblies", (request, response) -> {
			var assemblies = entity.fetch();

			if (assemblies != null)
				return new Gson().toJson(assemblies);
			else {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("status", 0);
				return jsonObj;
			}
		});
	}

	public void add() {
		post("/assembly", (request, response) -> {
			JSONObject json = new JSONObject(request.body());

			var pieces = (List<Piece>) json.getJSONArray("pieces");

			var name = json.getString("name");

			var user = entity.create(new Assembly(pieces, name));

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
