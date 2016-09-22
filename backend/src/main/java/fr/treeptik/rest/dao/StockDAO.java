package fr.treeptik.rest.dao;

import fr.treeptik.rest.model.PersistentStock;

import java.util.List;

public interface StockDAO {

    List<PersistentStock> list();

    PersistentStock save(PersistentStock persistentStock);

}
