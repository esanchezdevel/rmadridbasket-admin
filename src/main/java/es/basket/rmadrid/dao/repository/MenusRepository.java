package es.basket.rmadrid.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.basket.rmadrid.dao.entity.Menus;

@Repository
public interface MenusRepository extends JpaRepository<Menus, Long> {

	@Query(value="SELECT * FROM menus WHERE menu=?1 ORDER BY item_order ASC", nativeQuery = true)
	List<Menus> findMenu(String menu);
}
