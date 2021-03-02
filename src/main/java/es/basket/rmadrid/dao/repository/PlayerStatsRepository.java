package es.basket.rmadrid.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.basket.rmadrid.dao.entity.PlayerStats;

public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {

}
