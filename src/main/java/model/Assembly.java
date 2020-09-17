package model;

import lombok.Data;

import java.util.LinkedList;

@Data

public class Assembly {
    private LinkedList<Piece> pieces;
    private String name;
    private int id;

    public Assembly(){

    }
}
