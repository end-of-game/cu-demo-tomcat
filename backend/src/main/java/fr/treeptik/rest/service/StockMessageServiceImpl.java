package fr.treeptik.rest.service;

import fr.treeptik.rest.dao.StockDAO;
import fr.treeptik.rest.model.PersistentStock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@Transactional
public class StockMessageServiceImpl implements StockMessageService {

    public String[] companiesNames = {"IBM", "Oracle", "Docker", "Treeptik", "Xebia",
            "MacDo", "CocaCola", "Apple", "Intel", "VmWare",
            "Hooli", "PiedPiper", "AnaoTech", "Rothschild", "SG",};
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
