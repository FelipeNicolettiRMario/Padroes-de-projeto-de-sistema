package main;

import controller.AssemblyController;
import model.Assembly;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;


public class MainServer {

    //final static Model model = new Model();

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

        AssemblyController assemblyController = new AssemblyController(new Assembly());

        assemblyController.add();
        assemblyController.update();
        assemblyController.fetch();
        assemblyController.remove();
    }

}
