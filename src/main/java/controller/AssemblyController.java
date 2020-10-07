package controller;

import model.Assembly;

public class AssemblyController extends GeneralController<Assembly> {

	public AssemblyController(Assembly entity) {
		super("/assembly", entity);
	}

	
	
}
