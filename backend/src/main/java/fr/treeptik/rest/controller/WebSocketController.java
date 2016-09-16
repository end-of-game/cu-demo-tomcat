package fr.treeptik.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.treeptik.rest.model.PersistentStock;
import fr.treeptik.rest.service.StockMessageService;

@Controller
public class WebSocketController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private StockMessageService stockMessageService;

	public void refresh() {
		List<PersistentStock> persistentStockList = stockMessageService.list();
		simpMessagingTemplate.convertAndSend("/topic/results", persistentStockList);
	}

	@RequestMapping("/")
	public String start() {
		return "index";
	}

	@Scheduled(fixedDelay = 2000)
	public void schedule() throws Exception {
		refresh();
	}

}
