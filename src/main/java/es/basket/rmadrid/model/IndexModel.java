package es.basket.rmadrid.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import es.basket.rmadrid.controller.AdminController;

@Component
public class IndexModel extends BaseModel implements Models {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Override
	public void execute(Model model) {
		
		logger.debug("Executing Index Model");
		
		super.execute(model);
	}

}
