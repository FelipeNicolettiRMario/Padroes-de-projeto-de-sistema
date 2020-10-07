package controller;

import model.Piece;

public class PieceController extends GeneralController<Piece>{

	public PieceController(Piece entity) {
		super("/piece", entity);
		
	}


}
