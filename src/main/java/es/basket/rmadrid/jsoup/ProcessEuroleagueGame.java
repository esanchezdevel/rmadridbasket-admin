package es.basket.rmadrid.jsoup;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.basket.rmadrid.dao.entity.Games;
import es.basket.rmadrid.dao.entity.PlayerStats;
import es.basket.rmadrid.dao.entity.Tournaments;
import es.basket.rmadrid.dao.repository.PlayerStatsRepository;

@Component
public class ProcessEuroleagueGame implements ProcessJsoup {

	@Autowired
	private PlayerStatsRepository playerStatsRepository;

	@Override
	public void process(String url) {
		try {
			Document document = Jsoup.connect(url).get();

			Elements tables = document.select("table");

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

				if (count == 1)
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

			Elements spans = document.select(".gc-title").select("span");

			for (Element e : spans) {

				System.out.println("->" + e.text());
			}

			String round = spans.get(2).text();
			round.replace("Round", "Jornada");

			String date = document.select(".dates").get(0).select(".date").text().split(" CET: ")[0];
			String time = document.select(".dates").get(0).select(".date").text().split(" CET: ")[1];

			String court = document.select(".stadium").text();

			Games game = new Games();
			game.setLocal(localTeamName);
			game.setVisitor(visitorTeamName);
			game.setScoreLocal(localScore);
			game.setScoreVisitor(visitorScore);

			Tournaments tournament = new Tournaments();
			tournament.setId(2);
			game.setTournament(tournament);
			game.setRound(round);
			game.setDate(createDate(date, time));
			game.setCourt(court);
			game.setUpdated(new Date());

			List<PlayerStats> playerStatsList = new ArrayList<PlayerStats>();
			getEuroTeamStats(2, tables, playerStatsList, true);
			getEuroTeamStats(3, tables, playerStatsList, false);

			for (PlayerStats player : playerStatsList) {
				player.setGame(game);
				playerStatsRepository.save(player);
			}
		} catch (IOException e) {
			System.out.println("Error downloading page");
		}
	}

	private Date createDate(String date, String time) {

		date = date + " " + time;

		SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm", Locale.US);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			System.out.println("Error parsing date: " + e.getMessage());
			return new Date();
		}
	}

	private void getEuroTeamStats(int team, Elements tables, List<PlayerStats> playerStatsList, boolean isLocal) {

		for (Element row : tables.get(team).select("tr")) {
			Elements tds = row.select("td:not([rowspan])");
			if (isPlayerRow(tds)) {

				PlayerStats playerStats = new PlayerStats();
				playerStats.setLocal_team(isLocal);
				playerStats.setNumber(tds.get(0).text());
				playerStats.setName(tds.get(1).text());
				playerStats.setMinutes(tds.get(2).text());
				playerStats.setPoints((tds.get(3).text().equals("") | tds.get(3).text().equals("-")) ? 0
						: Integer.parseInt(tds.get(3).text()));
				playerStats.setFg2(tds.get(4).text());
				playerStats.setFg2Rate("");
				playerStats.setFg3(tds.get(5).text());
				playerStats.setFg3Rate("");
				playerStats.setFg1(tds.get(6).text());
				playerStats.setFg1Rate("");
				playerStats.setTotalRebounds((tds.get(9).text().equals("") | tds.get(9).text().equals("-")) ? 0
						: Integer.parseInt(tds.get(9).text()));

				playerStats.setDefensiveRebounds((tds.get(8).text().equals("") | tds.get(8).text().equals("-")) ? 0
						: Integer.parseInt(tds.get(8).text()));
				playerStats.setOffensiveRebounds((tds.get(7).text().equals("") | tds.get(7).text().equals("-")) ? 0
						: Integer.parseInt(tds.get(7).text()));

				playerStats.setAssists((tds.get(10).text().equals("") | tds.get(10).text().equals("-")) ? 0
						: Integer.parseInt(tds.get(10).text()));
				playerStats.setSteals((tds.get(11).text().equals("") | tds.get(11).text().equals("-")) ? 0
						: Integer.parseInt(tds.get(11).text()));
				playerStats.setLoses((tds.get(12).text().equals("") | tds.get(12).text().equals("-")) ? 0
						: Integer.parseInt(tds.get(12).text()));
				playerStats.setTransitions(0);
				playerStats.setBlocks((tds.get(13).text().equals("") | tds.get(13).text().equals("-")) ? 0
						: Integer.parseInt(tds.get(13).text()));
				playerStats.setBlocksReceived((tds.get(14).text().equals("") | tds.get(14).text().equals("-")) ? 0
						: Integer.parseInt(tds.get(14).text()));
				playerStats.setSlams(0);
				playerStats.setFouls((tds.get(15).text().equals("") | tds.get(15).text().equals("-")) ? 0
						: Integer.parseInt(tds.get(15).text()));
				playerStats.setFoulsReceived((tds.get(16).text().equals("") | tds.get(16).text().equals("-")) ? 0
						: Integer.parseInt(tds.get(16).text()));
				playerStats.setDifference("");
				playerStats.setRate(tds.get(17).text());
				playerStats.setUpdated(new Date());

				playerStatsList.add(playerStats);
			}
		}
	}

	private boolean isPlayerRow(Elements tds) {
		return tds != null && tds.size() >= 1 && !tds.get(0).text().isEmpty()
				&& !tds.get(0).text().equalsIgnoreCase("E") && !tds.get(0).text().equalsIgnoreCase("5f");
	}
}
