package es.basket.rmadrid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.basket.rmadrid.model.ResultsPostModel;
import es.basket.rmadrid.model.IndexModel;

@Controller
public class AdminController {

	@Autowired
	private IndexModel indexModel;
	
	@Autowired
	private ResultsPostModel gameStatsModel;
	
	@GetMapping("/Admin")
	public String admin(Model model) {
		
		indexModel.execute(model);
		
		return "index";
	}
	
	@PostMapping("/Admin/ProcessStats")
	public String processStats(@RequestParam("stats_url") String statsUrl, Model model) {
				
		model.addAttribute("statsUrl", statsUrl);
		
		gameStatsModel.execute(model);
		
		return "processed";
	}
}
