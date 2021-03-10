package es.basket.rmadrid.dao.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "games")
@EntityListeners(AuditingEntityListener.class)
public class Games extends BaseEntity {

	private String local;

	private String visitor;

	@Column(name = "score_local")
	private long scoreLocal;
	
	@Column(name = "score_visitor")
	private long scoreVisitor;

    @ManyToOne
	@JoinColumn(name = "tournament_id")
	private Tournaments tournament;
    
    private String round;
    
    private Date date;
	
	private String court;
	
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<PlayerStats> playerStats;
	
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getVisitor() {
		return visitor;
	}

	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}

	public long getScoreLocal() {
		return scoreLocal;
	}

	public void setScoreLocal(long scoreLocal) {
		this.scoreLocal = scoreLocal;
	}

	public long getScoreVisitor() {
		return scoreVisitor;
	}

	public void setScoreVisitor(long scoreVisitor) {
		this.scoreVisitor = scoreVisitor;
	}

	public Tournaments getTournament() {
		return tournament;
	}

	public void setTournament(Tournaments tournament) {
		this.tournament = tournament;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCourt() {
		return court;
	}

	public void setCourt(String court) {
		this.court = court;
	}

	public List<PlayerStats> getPlayerStats() {
		return playerStats;
	}

	public void setPlayerStats(List<PlayerStats> playerStats) {
		this.playerStats = playerStats;
	}
}
