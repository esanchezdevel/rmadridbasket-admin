package es.basket.rmadrid.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
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
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private IndexModel indexModel;
	
	@Autowired
	private ResultsPostModel gameStatsModel;
	
	@GetMapping("/Admin")
	public String admin(Model model) {		
		logger.debug("Handling /Admin request");
		
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
