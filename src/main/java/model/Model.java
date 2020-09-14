package model;


import java.util.List;
import java.util.LinkedList;


public class Model {

    private final List<User> users = new LinkedList<User>();


    public void addUser(User user) {
		users.add(user);
    }

    //View is observing the Model
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) return user;
        }
        return null;
    }
    

}
