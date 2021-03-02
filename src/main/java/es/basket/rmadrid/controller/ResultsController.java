package es.basket.rmadrid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.basket.rmadrid.model.ResultsModel;
import es.basket.rmadrid.model.ResultsPostModel;

@Controller
public class ResultsController {

	@Autowired
	private ResultsModel resultsModel;
	
	@Autowired
	private ResultsPostModel resultsPostModel;
	
	@GetMapping("/Admin/Results")
	public String results(Model model) {
		
		resultsModel.execute(model);
		
		return "results";
	}
	
	
	@PostMapping("/Admin/Results")
	@ResponseBody
	public ResponseEntity<HttpStatus> resultsPost(@RequestParam("resultUrl") String statsUrl, Model model) {
				
		model.addAttribute("resultUrl", statsUrl);
		
		resultsPostModel.execute(model);
		
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
}
