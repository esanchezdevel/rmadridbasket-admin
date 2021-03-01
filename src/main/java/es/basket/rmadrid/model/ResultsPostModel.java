package es.basket.rmadrid.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import es.basket.rmadrid.entity.PlayerStats;

@Component
public class ResultsPostModel implements Models {

	@Override
	public void execute(Model model) {

		String statsUrl = (String) model.getAttribute("resultUrl");
		System.out.println("Processing URL: " + statsUrl);

		try {
			Document document = Jsoup.connect(statsUrl).get();

			Elements tables = document.select("table");

			List<PlayerStats> localTeam = getTeamStats(1, tables);
			List<PlayerStats> visitorTeam = getTeamStats(2, tables);

			System.out.println("Local:");
			for (PlayerStats player : localTeam) {
				System.out.println(player);
			}
			
			System.out.println("Visitor:");
			for (PlayerStats player : visitorTeam) {
				System.out.println(player);
			}
			
			System.out.println();
		} catch (IOException e) {
			System.out.println("Error downloading page");
		}
	}

	private boolean isPlayerRow(Elements tds) {
		return tds != null && tds.size() >= 1 && !tds.get(0).text().isEmpty() && !tds.get(0).text().equalsIgnoreCase("E") && !tds.get(0).text().equalsIgnoreCase("5f");
	}
	
	private List<PlayerStats> getTeamStats(int team, Elements tables) {
		
		List<PlayerStats> teamStats = new ArrayList<PlayerStats>();
	
		for (Element row : tables.get(team).select("tr")) {
			Elements tds = row.select("td:not([rowspan])");
			if (isPlayerRow(tds)) {
				
				PlayerStats playerStats = new PlayerStats();
				playerStats.setNumber(tds.get(0).text());
				playerStats.setName(tds.get(1).text());
				playerStats.setMinutes(tds.get(2).text());
				playerStats.setPoints(tds.get(3).text());
				playerStats.setFg2(tds.get(4).text());
				playerStats.setFg2Rate(tds.get(5).text());
				playerStats.setFg3(tds.get(6).text());
				playerStats.setFg3Rate(tds.get(7).text());
				playerStats.setFg1(tds.get(8).text());
				playerStats.setFg1Rate(tds.get(9).text());
				playerStats.setTotalRebounds(tds.get(10).text());
				
				
				if (!tds.get(11).text().isEmpty() && tds.get(11).text().contains("+")) {
					String[] rebounds = tds.get(11).text().split("\\+");
				
					playerStats.setDefensiveRebounds(rebounds[0]);
					playerStats.setOffensiveRebounds(rebounds[1]);
				} else {
					playerStats.setDefensiveRebounds("");
					playerStats.setOffensiveRebounds("");
				}
				
				playerStats.setAssists(tds.get(12).text());
				playerStats.setSteals(tds.get(13).text());
				playerStats.setLoses(tds.get(14).text());
				playerStats.setTransitions(tds.get(15).text());
				playerStats.setBlocks(tds.get(16).text());
				playerStats.setBlocksReceived(tds.get(17).text());
				playerStats.setSlams(tds.get(18).text());
				playerStats.setFouls(tds.get(19).text());
				playerStats.setFoulsReceived(tds.get(20).text());
				playerStats.setDifference(tds.get(21).text());
				playerStats.setRate(tds.get(22).text());
				
				teamStats.add(playerStats);
			}
		}
		return teamStats;
	}
}
