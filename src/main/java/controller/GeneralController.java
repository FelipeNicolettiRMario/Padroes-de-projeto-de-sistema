package controller;

import com.google.gson.Gson;
import org.bson.Document;
import org.json.JSONObject;

import static spark.Spark.*;

public abstract class GeneralController<T extends Document> implements IController {
	
	protected final String path;
	protected T entity;
	

	public GeneralController(String path, T entity) {
		this.path = path;
		this.entity = entity;
	}
	
	@Override
	public final void add() {
		post(path, (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            JSONObject json = new JSONObject(request.body());
            T addedEntity = addEntity(json);
            return getEntityOrDefault(addedEntity);
        });
	}

	@Override
	public final void update() {
        put(path, (request, response) -> {
            JSONObject json = new JSONObject(request.body());
            T updatedEntity = updateEntity(json);
            return getEntityOrDefault(updatedEntity);
        });
	}

	@Override
	public final void fetch() {
		get(path, (request, response) -> {
            JSONObject json = new JSONObject(request.body());
            T fetchedEntity = fetchEntity(json);
            return getEntityOrDefault(fetchedEntity);
        });
	}

	@Override
	public final void remove() {
        delete(path, (request, response) -> {
            JSONObject json = new JSONObject(request.body());
            T removedEntity = removeEntity(json);
            return getEntityOrDefault(removedEntity);
        });

	}

	private Object getEntityOrDefault(T entity) {
		if (entity != null) return new Gson().toJson(entity);
		else {
		    JSONObject jsonObj = new JSONObject();
		    jsonObj.put("status", 0);
		    return jsonObj;
		}
	}
	
	protected T addEntity(JSONObject jsonObj) {
		return this.entity;
	}
	
	protected T updateEntity(JSONObject jsonObj) {
		return this.entity;
	}
	
	protected T fetchEntity(JSONObject jsonObj) {
		return this.entity;
	}
	
	protected T removeEntity(JSONObject jsonObj) {
		return this.entity;
	}

}
