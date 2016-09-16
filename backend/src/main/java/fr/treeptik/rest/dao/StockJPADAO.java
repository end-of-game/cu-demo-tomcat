package fr.treeptik.rest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import fr.treeptik.rest.model.PersistentStock;

@Repository
public class StockJPADAO implements StockDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public PersistentStock save(PersistentStock persistentStock) {
		entityManager.persist(persistentStock);
		return persistentStock;
	}

	@Override
	public List<PersistentStock> list() {
		Query query = entityManager.createNativeQuery("select p.* from persistentstock as p, (select max(id) as id , name from persistentstock group by name) as tempo where tempo.id = p.id", PersistentStock.class);
		query.setMaxResults(15);
		return query.getResultList();
	}

}
