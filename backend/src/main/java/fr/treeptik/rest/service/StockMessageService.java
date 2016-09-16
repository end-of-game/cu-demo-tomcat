package fr.treeptik.rest.service;

import java.util.List;

import fr.treeptik.rest.model.PersistentStock;

public interface StockMessageService {

	PersistentStock save(PersistentStock persistentStock);

	List<PersistentStock> list();

	void sendMessage();
}
