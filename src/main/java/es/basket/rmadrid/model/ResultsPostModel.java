package es.basket.rmadrid.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import es.basket.rmadrid.jsoup.ProcessACBGame;
import es.basket.rmadrid.jsoup.ProcessEuroleagueGame;

@Component
public class ResultsPostModel implements Models {

	@Autowired
	private ProcessACBGame processACBGame;
	
	@Autowired
	private ProcessEuroleagueGame processEuroleagueGame;
	
	@Override
	public void execute(Model model) {

		String statsUrl = (String) model.getAttribute("resultUrl");
		System.out.println("Processing URL: " + statsUrl);

		if (isACBUrl(statsUrl)) {
			processACBGame.process(statsUrl);
		} else if (isEuroleagueUrl(statsUrl)) {
			processEuroleagueGame.process(statsUrl);
		} else {
			model.addAttribute("result", "error");
		}
	}

	private boolean isEuroleagueUrl(String statsUrl) {
		return statsUrl.contains("euroleague.net");
	}

	private boolean isACBUrl(String statsUrl) {
		return statsUrl.contains("acb.com");
	}
}
