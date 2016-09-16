package fr.treeptik.rest.dao;

import java.util.List;

import fr.treeptik.rest.model.PersistentStock;

public interface StockDAO {

	List<PersistentStock> list();

	PersistentStock save(PersistentStock persistentStock);

}
