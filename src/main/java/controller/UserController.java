package controller;

import org.json.JSONObject;

import model.User;

public class UserController extends GeneralController<User> {

    public UserController(User entity) {
        super("/user", entity);
    }
    
    @Override
	protected User addEntity(JSONObject jsonObj) {
    	String username = jsonObj.getString("username");
        String name = jsonObj.getString("name");
        String password = jsonObj.getString("password");
        return entity.addUser(username, name, password);
	}
    
}
