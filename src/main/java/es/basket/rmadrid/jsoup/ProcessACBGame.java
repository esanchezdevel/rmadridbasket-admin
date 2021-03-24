package es.basket.rmadrid.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.basket.rmadrid.jpa.entity.Games;
import es.basket.rmadrid.jpa.entity.PlayerStats;
import es.basket.rmadrid.jpa.entity.Tournaments;
import es.basket.rmadrid.jpa.repository.PlayerStatsRepository;
import es.basket.rmadrid.service.FillPlayerStatsListService;
import es.basket.rmadrid.util.DateUtils;

@Component
public class ProcessACBGame implements ProcessJsoup {
	
	@Autowired
	private PlayerStatsRepository playerStatsRepository;
	
	@Autowired
	private FillPlayerStatsListService fillPlayerStatsListService;

	@Override
	public void process(String url) {
		try {
			Document document = Jsoup.connect(url).get();

			Elements data = document.select(".datos_fecha");
			String[] dataArray = data.text().split(" \\| ");
			String round = dataArray[0];			
			String date = dataArray[1] + " " + dataArray[2];
			
			String court = data.select(".clase_mostrar1280").text();

			Elements header = document.select(".cabecera_partido");

			String localTeamName = header.get(0).select("h4").get(0).text();
			String visitorTeamName = header.get(0).select("h4").get(1).text();

			Elements tables = document.select("table");

			int localScore = 0;
			int visitorScore = 0;
			int count = 0;
			for (Element row : tables.get(0).select("tr")) {

				if (count == 0) {
					count++;
					continue;
				}

				Elements tds = row.select("td:not([rowspan])");

				int quarter1 = Integer.parseInt(tds.get(2).text());
				int quarter2 = Integer.parseInt(tds.get(3).text());
				int quarter3 = Integer.parseInt(tds.get(4).text());
				int quarter4 = Integer.parseInt(tds.get(5).text());

				if (count == 1)
					localScore = quarter1 + quarter2 + quarter3 + quarter4;
				else
					visitorScore = quarter1 + quarter2 + quarter3 + quarter4;

				count++;
			}

			Games game = new Games();
			game.setLocal(localTeamName);
			game.setVisitor(visitorTeamName);
			game.setScoreLocal(localScore);
			game.setScoreVisitor(visitorScore);

			Tournaments tournament = new Tournaments();
			tournament.setId(1);
			game.setTournament(tournament);
			game.setRound(round);
			game.setDate(DateUtils.createDate(date, "dd/MM/yyyy HH:mm"));
			game.setCourt(court);
			game.setUpdated(new Date());

			List<PlayerStats> playerStatsList = new ArrayList<PlayerStats>();
			
			fillPlayerStatsListService.setParameters(FillPlayerStatsListService.TYPE_ACB, 1, tables, true, playerStatsList);
			fillPlayerStatsListService.execute();
			
			//TODO check that the playerStatsList is updated without get it.
			
			fillPlayerStatsListService.setParameters(FillPlayerStatsListService.TYPE_ACB, 2, tables, false, playerStatsList);
			fillPlayerStatsListService.execute();
			

			for (PlayerStats player : playerStatsList) {
				player.setGame(game);
				playerStatsRepository.save(player);
			}

			System.out.println();
		} catch (IOException e) {
			System.out.println("Error downloading page");
		}
	}
}
