package controller;

//Importing the library to develop REST Architecture

import com.google.gson.Gson;
import model.Assembly;
import model.Model;
import model.User;
import org.json.JSONObject;

import static spark.Spark.post;

public class AssemblyController {

    private final Assembly assembly;

    public AssemblyController(Assembly assembly) {
        this.assembly = assembly;
    }

    public void add() {

    }

    public void put() {

    }
    public void get() {

    }
    public void remove() {

    }
}
