package controller;

import com.google.gson.Gson;
import model.Assembly;
import model.Piece;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import java.util.LinkedList;

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

			var name = json.getString("name");


			var pieces = json.getJSONArray("pieces");
			var listPieces = new LinkedList<Piece>();
			for (int i = 0; i < pieces.length(); i++) {
				var piecesObj = pieces.getJSONObject(i);
				var pieceName = piecesObj.getString("name");
				var src = piecesObj.getString("src");
				var src_img = piecesObj.getString("src_img");
				var positionX = piecesObj.getString("positionX");
				var positionY = piecesObj.getString("positionY");
				var positionZ = piecesObj.getString("positionZ");
				var piece = new Piece(pieceName, src, src_img,positionX, positionY, positionZ);
				listPieces.add(piece);
			}

			var assembly = entity.create(new Assembly(listPieces, name));


			if (assembly != null)
				return new Gson().toJson(assembly);
			else {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("status", 0);
				return jsonObj;
			}
		});
	}
}
