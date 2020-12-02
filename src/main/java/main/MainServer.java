package main;

import controller.UserController;
import io.github.cdimascio.dotenv.Dotenv;
import model.Context;
import model.MongoConnection;
import model.User;

import static spark.Spark.after;
import static spark.Spark.port;

public class MainServer {

    public static void main(String[] args) {

        ProcessBuilder process = new ProcessBuilder();
        Dotenv dotenv = Dotenv.configure().load();
        String connectionString = dotenv.get("CONNECTION_STRING");
        String database = dotenv.get("DB");
        int port = process.environment().get("PORT") != null ? Integer.parseInt(process.environment().get("PORT")) : 1234;
        port(port);
        after((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });
        //mongo connection
        MongoConnection mongoConnection = new MongoConnection(connectionString, database);
        Context context = new Context(mongoConnection);
        UserController userController = new UserController(new User(context));
        userController.add();
        userController.fetch();
        userController.getById();
        userController.login();
        userController.setAdmin();

//        AssemblyController assemblyController = new AssemblyController(new Assembly(context));
//        assemblyController.add();
//        assemblyController.fetch();
//        assemblyController.remove();
//        assemblyController.update();
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
