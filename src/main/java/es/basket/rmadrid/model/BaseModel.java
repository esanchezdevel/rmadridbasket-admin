package es.basket.rmadrid.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import es.basket.rmadrid.common.CommonParameters;

@Component
public class BaseModel implements Models<Model> {

	@Autowired
	private CommonParameters commonParameters;
	
	@Override
	public void execute(Model model) {
		commonParameters.get(model);		
	}

}
