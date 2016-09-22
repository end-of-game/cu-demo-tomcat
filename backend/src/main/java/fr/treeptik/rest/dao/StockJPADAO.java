package fr.treeptik.rest.dao;

import fr.treeptik.rest.model.PersistentStock;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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
