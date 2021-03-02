package es.basket.rmadrid.dao.entity;

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
	
	@ManyToOne
	@JoinColumn(name = "game_id")
	private Games game;
	
	private boolean local_team;
	private String number;
	private String name;
	private String minutes;
	private String points;
	private String fg2;
	private String fg2Rate;
	private String fg3;
	private String fg3Rate;
	private String fg1;
	private String fg1Rate;
	private String totalRebounds;
	private String defensiveRebounds;
	private String offensiveRebounds;
	private String assists;
	private String steals;
	private String loses;
	private String transitions;
	private String blocks;
	private String blocksReceived;
	private String slams;
	private String fouls;
	private String foulsReceived;
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
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
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
	public String getTotalRebounds() {
		return totalRebounds;
	}
	public void setTotalRebounds(String totalRebounds) {
		this.totalRebounds = totalRebounds;
	}
	public String getDefensiveRebounds() {
		return defensiveRebounds;
	}
	public void setDefensiveRebounds(String defensiveRebounds) {
		this.defensiveRebounds = defensiveRebounds;
	}
	public String getOffensiveRebounds() {
		return offensiveRebounds;
	}
	public void setOffensiveRebounds(String offensiveRebounds) {
		this.offensiveRebounds = offensiveRebounds;
	}
	public String getAssists() {
		return assists;
	}
	public void setAssists(String assists) {
		this.assists = assists;
	}
	public String getSteals() {
		return steals;
	}
	public void setSteals(String steals) {
		this.steals = steals;
	}
	public String getLoses() {
		return loses;
	}
	public void setLoses(String loses) {
		this.loses = loses;
	}
	public String getTransitions() {
		return transitions;
	}
	public void setTransitions(String transitions) {
		this.transitions = transitions;
	}
	public String getBlocks() {
		return blocks;
	}
	public void setBlocks(String blocks) {
		this.blocks = blocks;
	}
	public String getBlocksReceived() {
		return blocksReceived;
	}
	public void setBlocksReceived(String blocksReceived) {
		this.blocksReceived = blocksReceived;
	}
	public String getSlams() {
		return slams;
	}
	public void setSlams(String slams) {
		this.slams = slams;
	}
	public String getFouls() {
		return fouls;
	}
	public void setFouls(String fouls) {
		this.fouls = fouls;
	}
	public String getFoulsReceived() {
		return foulsReceived;
	}
	public void setFoulsReceived(String foulsReceived) {
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
		return "PlayerStats [" + (number != null ? "number=" + number + ", " : "")
				+ (name != null ? "name=" + name + ", " : "") + (minutes != null ? "minutes=" + minutes + ", " : "")
				+ (points != null ? "points=" + points + ", " : "") + (fg2 != null ? "fg2=" + fg2 + ", " : "")
				+ (fg2Rate != null ? "fg2Rate=" + fg2Rate + ", " : "") + (fg3 != null ? "fg3=" + fg3 + ", " : "")
				+ (fg3Rate != null ? "fg3Rate=" + fg3Rate + ", " : "") + (fg1 != null ? "fg1=" + fg1 + ", " : "")
				+ (fg1Rate != null ? "fg1Rate=" + fg1Rate + ", " : "")
				+ (totalRebounds != null ? "totalRebounds=" + totalRebounds + ", " : "")
				+ (defensiveRebounds != null ? "defensiveRebounds=" + defensiveRebounds + ", " : "")
				+ (offensiveRebounds != null ? "offensiveRebounds=" + offensiveRebounds + ", " : "")
				+ (assists != null ? "assists=" + assists + ", " : "")
				+ (steals != null ? "steals=" + steals + ", " : "") + (loses != null ? "loses=" + loses + ", " : "")
				+ (transitions != null ? "transitions=" + transitions + ", " : "")
				+ (blocks != null ? "blocks=" + blocks + ", " : "")
				+ (blocksReceived != null ? "blocksReceived=" + blocksReceived + ", " : "")
				+ (slams != null ? "slams=" + slams + ", " : "") + (fouls != null ? "fouls=" + fouls + ", " : "")
				+ (foulsReceived != null ? "foulsReceived=" + foulsReceived + ", " : "")
				+ (difference != null ? "difference=" + difference + ", " : "") + (rate != null ? "rate=" + rate : "")
				+ "]";
	}
}
