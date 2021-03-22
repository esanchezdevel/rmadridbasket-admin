package es.basket.rmadrid.service;

import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import es.basket.rmadrid.dao.entity.PlayerStats;

@Component
public class FillPlayerStatsListService implements Services {

	private int team;
	private Elements tables;
	private List<PlayerStats> playerStatsList;
	private boolean isLocal;

	@Override
	public void execute() {
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
				playerStats
						.setTotalRebounds((tds.get(10).text().equals("")) ? 0 : Integer.parseInt(tds.get(10).text()));

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
				playerStats
						.setBlocksReceived((tds.get(17).text().equals("")) ? 0 : Integer.parseInt(tds.get(17).text()));
				playerStats.setSlams((tds.get(18).text().equals("")) ? 0 : Integer.parseInt(tds.get(18).text()));
				playerStats.setFouls((tds.get(19).text().equals("")) ? 0 : Integer.parseInt(tds.get(19).text()));
				playerStats
						.setFoulsReceived((tds.get(20).text().equals("")) ? 0 : Integer.parseInt(tds.get(20).text()));
				playerStats.setDifference(tds.get(21).text());
				playerStats.setRate(tds.get(22).text());
				playerStats.setUpdated(new Date());

				this.playerStatsList.add(playerStats);
			}
		}
	}

	private boolean isPlayerRow(Elements tds) {
		return tds != null && tds.size() >= 1 && !tds.get(0).text().isEmpty()
				&& !tds.get(0).text().equalsIgnoreCase("E") && !tds.get(0).text().equalsIgnoreCase("5f");
	}

	public void setParameters(int team, Elements tables, boolean isLocal, List<PlayerStats> playerStatsList) {
		
		this.team = team;
		this.tables = tables;
		this.isLocal = isLocal;
		this.playerStatsList = playerStatsList;
	}
	
	public List<PlayerStats> getPlayerStatsList() {
		return this.playerStatsList;
	}
}