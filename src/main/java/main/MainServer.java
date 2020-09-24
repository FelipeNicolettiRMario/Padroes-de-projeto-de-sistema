package main;

import controller.AssemblyController;
import controller.PieceController;
import controller.ScoreController;
import controller.UserController;
import io.github.cdimascio.dotenv.Dotenv;
import model.*;

import static spark.Spark.port;


public class MainServer {

    public static void main(String[] args) {

        // Get port config of heroku on environment variable
        ProcessBuilder process = new ProcessBuilder();
        Dotenv dotenv = Dotenv.configure().load();

        var connectionString = dotenv.get("CONNECTION_STRING");
        var database = dotenv.get("DB");

        int port = process.environment().get("PORT") != null ? Integer.parseInt(process.environment().get("PORT")) : 1234;
        port(port);

        //Serve html js css files
        //staticFileLocation("/static");

        //mongo connection
        MongoConnection mongoConnection = new MongoConnection(connectionString, database);
        Context context = new Context(mongoConnection);

        //bind mongodb to model of controllers and create routes
        var userController = new UserController(new User(context));
        userController.add();
        userController.fetch();
        userController.remove();
        userController.update();

        var assemblyController = new AssemblyController(new Assembly(context));
        assemblyController.add();
        assemblyController.fetch();
        assemblyController.remove();
        assemblyController.update();

        var pieceController = new PieceController(new Piece(context));
        pieceController.add();
        pieceController.fetch();
        pieceController.remove();
        pieceController.update();

        var scoreController = new ScoreController(new Score(context));
        scoreController.add();
        scoreController.fetch();
        scoreController.remove();
        scoreController.update();
    }

}
