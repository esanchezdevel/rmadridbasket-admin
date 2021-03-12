package es.basket.rmadrid.model;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import es.basket.rmadrid.dao.entity.Games;
import es.basket.rmadrid.dao.entity.PlayerStats;
import es.basket.rmadrid.dao.entity.Tournaments;
import es.basket.rmadrid.dao.repository.GamesRepository;
import es.basket.rmadrid.dao.repository.PlayerStatsRepository;

@Component
public class ResultsPostModel implements Models {

	@Autowired
	private PlayerStatsRepository playerStatsRepository;
	
	@Override
	public void execute(Model model) {

		String statsUrl = (String) model.getAttribute("resultUrl");
		System.out.println("Processing URL: " + statsUrl);

		if (isACBUrl(statsUrl)) {
			try {
				Document document = Jsoup.connect(statsUrl).get();

				Elements data = document.select(".datos_fecha");
				System.out.println("data=" + data.text());
				String[] dataArray = data.text().split(" \\| ");
				String round = dataArray[0];
				String date = dataArray[1];
				String time = dataArray[2];
				String court = data.select(".clase_mostrar1280").text();
				
				System.out.println("round=" + round + " date=" + date + " time=" + time + " court=" + court);
				
				Elements header = document.select(".cabecera_partido");
				
				String localTeamName = header.get(0).select("h4").get(0).text();
				String visitorTeamName = header.get(0).select("h4").get(1).text();
				
				System.out.println("LocalTeam=" + localTeamName + " - VisitorTeam=" + visitorTeamName + " Round=" + round);
				
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
				
				System.out.println("LocalScore=" + localScore + " VisitScore=" + visitorScore);
				
				
				Games game = new Games();
				game.setLocal(localTeamName);
				game.setVisitor(visitorTeamName);
				game.setScoreLocal(localScore);
				game.setScoreVisitor(visitorScore);
				
				Tournaments tournament = new Tournaments();
				tournament.setId(1);
				game.setTournament(tournament); 
				game.setRound(round);
				game.setDate(createDate(date, time));
				game.setCourt(court);
				game.setUpdated(new Date());
				
				List<PlayerStats> playerStatsList = new ArrayList<PlayerStats>();
				getTeamStats(1, tables, playerStatsList, true);
				getTeamStats(2, tables, playerStatsList, false);
				
				for (PlayerStats player : playerStatsList) {					
					player.setGame(game);
					playerStatsRepository.save(player);
				}
				
				System.out.println();
			} catch (IOException e) {
				System.out.println("Error downloading page");
			}
		} else if (isEuroleagueUrl(statsUrl)) {
			
			try {
				//TODO =======================================
				Document document = Jsoup.connect(statsUrl).get();
				
				Elements tables = document.select("table");

				//Table 0 is the quarters
				System.out.println("==========================");
				int count = 0;
				int localScore = 0;
				int visitorScore = 0;
				String localTeamName = null;
				String visitorTeamName = null;
				for (Element row : tables.get(0).select("tr")) {
					Elements tds = row.select("td:not([rowspan])");
					
					if (count == 0) {
						count++;
						continue;
					}
					
					if(count == 1)
						localTeamName = tds.get(0).text();
					else
						visitorTeamName = tds.get(0).text();
					
					int quarter1 = Integer.parseInt(tds.get(1).text());
					int quarter2 = Integer.parseInt(tds.get(2).text());
					int quarter3 = Integer.parseInt(tds.get(3).text());
					int quarter4 = Integer.parseInt(tds.get(4).text());
					
					if (count == 1) 
						localScore = quarter1 + quarter2 + quarter3 + quarter4;
					else
						visitorScore = quarter1 + quarter2 + quarter3 + quarter4;
					
					count++;
				}
				
				System.out.println(localTeamName + ": " + localScore + " " + visitorTeamName + ": " + visitorScore);
				
				//Table 2 is the Local team
				System.out.println("==========================");
				for (Element row : tables.get(2).select("tr")) {
					Elements tds = row.select("td:not([rowspan])");
					
					for (Element td : tds) {
						System.out.println("-> " + td.text());
					}
				}
				
				//Table 3 is the visitor team
				System.out.println("==========================");
				for (Element row : tables.get(3).select("tr")) {
					Elements tds = row.select("td:not([rowspan])");
					
					for (Element td : tds) {
						System.out.println("-> " + td.text());
					}
				}
				
				//=============================================
			} catch (IOException e) {
				System.out.println("Error downloading page");
			}
			
		} else {
			model.addAttribute("result", "error");
		}
	}

	private Date createDate(String date, String time) {
		
		date = date + " " + time;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			System.out.println("Error parsing date: " + e.getMessage());
			return new Date();
		}
	}

	private boolean isEuroleagueUrl(String statsUrl) {
		return statsUrl.contains("euroleague.net");
	}

	private boolean isACBUrl(String statsUrl) {
		return statsUrl.contains("acb.com");
	}

	private boolean isPlayerRow(Elements tds) {
		return tds != null && tds.size() >= 1 && !tds.get(0).text().isEmpty()
				&& !tds.get(0).text().equalsIgnoreCase("E") && !tds.get(0).text().equalsIgnoreCase("5f");
	}

	private void getTeamStats(int team, Elements tables, List<PlayerStats> playerStatsList, boolean isLocal) {

		for (Element row : tables.get(team).select("tr")) {
			Elements tds = row.select("td:not([rowspan])");
			if (isPlayerRow(tds)) {

				PlayerStats playerStats = new PlayerStats();
				playerStats.setLocal_team(isLocal);
				playerStats.setNumber(tds.get(0).text());
				playerStats.setName(tds.get(1).text());
				playerStats.setMinutes(tds.get(2).text());
				playerStats.setPoints((tds.get(3).text().equals("")) ? 0 : Integer.parseInt(tds.get(3).text()));
				playerStats.setFg2(tds.get(4).text());
				playerStats.setFg2Rate(tds.get(5).text());
				playerStats.setFg3(tds.get(6).text());
				playerStats.setFg3Rate(tds.get(7).text());
				playerStats.setFg1(tds.get(8).text());
				playerStats.setFg1Rate(tds.get(9).text());
				playerStats.setTotalRebounds((tds.get(10).text().equals("")) ? 0 : Integer.parseInt(tds.get(10).text()));

				if (!tds.get(11).text().isEmpty() && tds.get(11).text().contains("+")) {
					String[] rebounds = tds.get(11).text().split("\\+");

					playerStats.setDefensiveRebounds(Integer.parseInt(rebounds[0]));
					playerStats.setOffensiveRebounds(Integer.parseInt(rebounds[1]));
				} else {
					playerStats.setDefensiveRebounds(0);
					playerStats.setOffensiveRebounds(0);
				}

				playerStats.setAssists((tds.get(12).text().equals("")) ? 0 : Integer.parseInt(tds.get(12).text()));
				playerStats.setSteals((tds.get(13).text().equals("")) ? 0 : Integer.parseInt(tds.get(13).text()));
				playerStats.setLoses((tds.get(14).text().equals("")) ? 0 : Integer.parseInt(tds.get(14).text()));
				playerStats.setTransitions((tds.get(15).text().equals("")) ? 0 : Integer.parseInt(tds.get(15).text()));
				playerStats.setBlocks((tds.get(16).text().equals("")) ? 0 : Integer.parseInt(tds.get(16).text()));
				playerStats.setBlocksReceived((tds.get(17).text().equals("")) ? 0 : Integer.parseInt(tds.get(17).text()));
				playerStats.setSlams((tds.get(18).text().equals("")) ? 0 : Integer.parseInt(tds.get(18).text()));
				playerStats.setFouls((tds.get(19).text().equals("")) ? 0 : Integer.parseInt(tds.get(19).text()));
				playerStats.setFoulsReceived((tds.get(20).text().equals("")) ? 0 : Integer.parseInt(tds.get(20).text()));
				playerStats.setDifference(tds.get(21).text());
				playerStats.setRate(tds.get(22).text());
				playerStats.setUpdated(new Date());
				
				playerStatsList.add(playerStats);
			}
		}
	}
}
