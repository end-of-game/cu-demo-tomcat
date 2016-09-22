package fr.treeptik.rest.service;

import fr.treeptik.rest.model.PersistentStock;

import java.util.List;

public interface StockMessageService {

    PersistentStock save(PersistentStock persistentStock);

    List<PersistentStock> list();

    void sendMessage();
}
