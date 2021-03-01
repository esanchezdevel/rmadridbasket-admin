package es.basket.rmadrid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.basket.rmadrid.model.ResultsModel;

@Controller
public class ResultsController {

	@Autowired
	private ResultsModel resultsModel;
	
	@GetMapping("/Admin/Results")
	public String processStats(Model model) {
		
		return "processed";
	}
	
	
	@PostMapping("/Admin/Results")
	public String processStats(@RequestParam("stats_url") String statsUrl, Model model) {
				
		model.addAttribute("statsUrl", statsUrl);
		
		resultsModel.execute(model);
		
		return "processed";
	}
	
}
