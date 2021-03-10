package es.basket.rmadrid.dao.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "player_stats")
@EntityListeners(AuditingEntityListener.class)
public class PlayerStats extends BaseEntity {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "game_id")
	private Games game;

	private boolean local_team;
	private String number;
	private String name;
	private String minutes;
	private int points;
	private String fg2;

	@Column(name = "fg2_rate")
	private String fg2Rate;

	private String fg3;

	@Column(name = "fg3_rate")
	private String fg3Rate;

	private String fg1;

	@Column(name = "fg1_rate")
	private String fg1Rate;

	@Column(name = "total_rebounds")
	private int totalRebounds;

	@Column(name = "defensive_rebounds")
	private int defensiveRebounds;

	@Column(name = "offensive_rebounds")
	private int offensiveRebounds;

	private int assists;
	private int steals;
	private int loses;
	private int transitions;
	private int blocks;
	private int blocksReceived;
	private int slams;
	private int fouls;

	@Column(name = "fouls_received")
	private int foulsReceived;

	private String difference;
	private String rate;

	public Games getGame() {
		return game;
	}

	public void setGame(Games game) {
		this.game = game;
	}

	public boolean isLocal_team() {
		return local_team;
	}

	public void setLocal_team(boolean local_team) {
		this.local_team = local_team;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getFg2() {
		return fg2;
	}

	public void setFg2(String fg2) {
		this.fg2 = fg2;
	}

	public String getFg2Rate() {
		return fg2Rate;
	}

	public void setFg2Rate(String fg2Rate) {
		this.fg2Rate = fg2Rate;
	}

	public String getFg3() {
		return fg3;
	}

	public void setFg3(String fg3) {
		this.fg3 = fg3;
	}

	public String getFg3Rate() {
		return fg3Rate;
	}

	public void setFg3Rate(String fg3Rate) {
		this.fg3Rate = fg3Rate;
	}

	public String getFg1() {
		return fg1;
	}

	public void setFg1(String fg1) {
		this.fg1 = fg1;
	}

	public String getFg1Rate() {
		return fg1Rate;
	}

	public void setFg1Rate(String fg1Rate) {
		this.fg1Rate = fg1Rate;
	}

	public int getTotalRebounds() {
		return totalRebounds;
	}

	public void setTotalRebounds(int totalRebounds) {
		this.totalRebounds = totalRebounds;
	}

	public int getDefensiveRebounds() {
		return defensiveRebounds;
	}

	public void setDefensiveRebounds(int defensiveRebounds) {
		this.defensiveRebounds = defensiveRebounds;
	}

	public int getOffensiveRebounds() {
		return offensiveRebounds;
	}

	public void setOffensiveRebounds(int offensiveRebounds) {
		this.offensiveRebounds = offensiveRebounds;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getSteals() {
		return steals;
	}

	public void setSteals(int steals) {
		this.steals = steals;
	}

	public int getLoses() {
		return loses;
	}

	public void setLoses(int loses) {
		this.loses = loses;
	}

	public int getTransitions() {
		return transitions;
	}

	public void setTransitions(int transitions) {
		this.transitions = transitions;
	}

	public int getBlocks() {
		return blocks;
	}

	public void setBlocks(int blocks) {
		this.blocks = blocks;
	}

	public int getBlocksReceived() {
		return blocksReceived;
	}

	public void setBlocksReceived(int blocksReceived) {
		this.blocksReceived = blocksReceived;
	}

	public int getSlams() {
		return slams;
	}

	public void setSlams(int slams) {
		this.slams = slams;
	}

	public int getFouls() {
		return fouls;
	}

	public void setFouls(int fouls) {
		this.fouls = fouls;
	}

	public int getFoulsReceived() {
		return foulsReceived;
	}

	public void setFoulsReceived(int foulsReceived) {
		this.foulsReceived = foulsReceived;
	}

	public String getDifference() {
		return difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "PlayerStats [" + (game != null ? "game=" + game + ", " : "") + "local_team=" + local_team + ", "
				+ (number != null ? "number=" + number + ", " : "") + (name != null ? "name=" + name + ", " : "")
				+ (minutes != null ? "minutes=" + minutes + ", " : "") + "points=" + points + ", "
				+ (fg2 != null ? "fg2=" + fg2 + ", " : "") + (fg2Rate != null ? "fg2Rate=" + fg2Rate + ", " : "")
				+ (fg3 != null ? "fg3=" + fg3 + ", " : "") + (fg3Rate != null ? "fg3Rate=" + fg3Rate + ", " : "")
				+ (fg1 != null ? "fg1=" + fg1 + ", " : "") + (fg1Rate != null ? "fg1Rate=" + fg1Rate + ", " : "")
				+ "totalRebounds=" + totalRebounds + ", defensiveRebounds=" + defensiveRebounds + ", offensiveRebounds="
				+ offensiveRebounds + ", assists=" + assists + ", steals=" + steals + ", loses=" + loses
				+ ", transitions=" + transitions + ", blocks=" + blocks + ", blocksReceived=" + blocksReceived
				+ ", slams=" + slams + ", fouls=" + fouls + ", foulsReceived=" + foulsReceived + ", "
				+ (difference != null ? "difference=" + difference + ", " : "") + (rate != null ? "rate=" + rate : "")
				+ "]";
	}
}
