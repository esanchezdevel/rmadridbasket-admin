package es.basket.rmadrid.dao.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tournaments")
@EntityListeners(AuditingEntityListener.class)
public class Tournaments extends BaseEntity {

	private String name;

	private String season;
	
	@OneToMany(mappedBy = "tournament")
    private List<Games> games;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public List<Games> getGames() {
		return games;
	}

	public void setGames(List<Games> games) {
		this.games = games;
	}
}
