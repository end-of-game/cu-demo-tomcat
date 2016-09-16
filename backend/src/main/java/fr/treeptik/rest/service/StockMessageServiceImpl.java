package fr.treeptik.rest.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.rest.model.PersistentStock;
import fr.treeptik.rest.dao.StockDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class StockMessageServiceImpl implements StockMessageService {

    private Logger logger = LoggerFactory.getLogger(StockMessageService.class);

    @Autowired
	private StockDAO stockDAO;

	@Override
	public PersistentStock save(PersistentStock persistentStock) {
		return stockDAO.save(persistentStock);
	}

	@Override
	public List<PersistentStock> list() {
		return stockDAO.list();
	}

	public String[] companiesNames = { "IBM", "Oracle", "Docker", "Treeptik", "Xebia",
			"MacDo", "CocaCola", "Apple", "Intel", "VmWare",
			"Hooli", "PipePiper", "AnaoTech", "Rothschild", "SG",};

	public PersistentStock generateRandomCompany() {
		PersistentStock stock = new PersistentStock();
		stock.setChanges(new Random().nextDouble());
		stock.setName(companiesNames[new Random().nextInt(15)]);
		stock.setPrice(new Random().nextDouble());
		stock.setValue(new Random().nextDouble());
		stock.setShare(new Random().nextInt());
		return stock;
	}

	@Scheduled(fixedDelay = 500)
	public void sendMessage() {
		logger.debug("Send new message");
		stockDAO.save(generateRandomCompany());
	}

}
