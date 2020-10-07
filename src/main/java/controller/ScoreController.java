package controller;

import model.Score;

public class ScoreController extends GeneralController<Score>{

	public ScoreController(Score entity) {
		super("/score", entity);
		
	}


}
