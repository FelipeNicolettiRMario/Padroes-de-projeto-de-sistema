package main;

import controller.Controller;
import model.Model;
import model.User;

import static spark.Spark.*;


public class MainServer {

    final static Model model = new Model();

    public static void main(String[] args) {

        // Get port config of heroku on environment variable
        ProcessBuilder process = new ProcessBuilder();
        int port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 1234;
        }
        port(port);

        //Serve html js css files
        staticFileLocation("/static");

        Controller controller = new Controller(model);

        controller.login();
    }

}
