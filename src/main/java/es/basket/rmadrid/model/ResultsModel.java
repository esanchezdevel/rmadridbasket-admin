package es.basket.rmadrid.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import es.basket.rmadrid.jpa.entity.Tournaments;
import es.basket.rmadrid.jpa.repository.TournamentsRepository;

@Component
public class ResultsModel extends BaseModel implements Models<Model> {

	@Autowired
	private TournamentsRepository tournamentsRepository;
	
	public void execute(Model model) {
		super.execute(model);
		
		List<Tournaments> tournaments = tournamentsRepository.findAll();
	
		model.addAttribute("tournaments", tournaments);
	}
}
