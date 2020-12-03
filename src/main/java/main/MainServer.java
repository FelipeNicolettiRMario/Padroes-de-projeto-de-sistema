package main;

import controller.AssemblyController;
import controller.UserController;
import io.github.cdimascio.dotenv.Dotenv;
import model.Assembly;
import model.Context;
import model.MongoConnection;
import model.User;

import static spark.Spark.*;

public class MainServer {

    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Dotenv dotenv = Dotenv.configure().load();
        String connectionString = dotenv.get("CONNECTION_STRING");
        String database = dotenv.get("DB");
        int port = process.environment().get("PORT") != null ? Integer.parseInt(process.environment().get("PORT")) : 1234;
        port(port);

        options("/*", (req, res) -> {
            String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Headers", "*");
            res.type("application/json");
        });

        //mongo connection
        MongoConnection mongoConnection = new MongoConnection(connectionString, database);
        Context context = new Context(mongoConnection);
        UserController userController = new UserController(new User(context));
        userController.add();
        userController.update();
        userController.fetch();
        userController.getById();
        userController.login();
        userController.setAdmin();
        userController.remove();

        AssemblyController assemblyController = new AssemblyController(new Assembly(context));
        assemblyController.add();
        assemblyController.fetch();
        assemblyController.getById();
//
//        PieceController pieceController = new PieceController(new Piece(context));
//        pieceController.add();
//        pieceController.fetch();
//        pieceController.remove();
//        pieceController.update();
//
//        ScoreController scoreController = new ScoreController(new Score(context));
//        scoreController.add();
        // scoreController.fetch();
        // scoreController.remove();
        // scoreController.update();
    }

}
