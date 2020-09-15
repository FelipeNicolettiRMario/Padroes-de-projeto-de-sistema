package model;

import java.util.LinkedList;

public class Assembly {
    private LinkedList<Piece> pieces;
    private String name;
    private int id;

    public Assembly(){

    }

    public int getId() {
        return id;
    }

    public LinkedList<Piece> getPieces() {
        return pieces;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPiece(Piece piece){
        this.pieces.add(piece);
    }

    public void setPieces(LinkedList<Piece> pieces) {
        this.pieces = pieces;
    }
}
