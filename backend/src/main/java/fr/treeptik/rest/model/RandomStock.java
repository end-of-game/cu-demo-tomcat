package fr.treeptik.rest.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by nicolas on 13/06/2016.
 */
public class RandomStock {

    private static Random randomGenerator = new Random();

    public final static List<Stock> stockList = new ArrayList<>();

    static {
        stockList.add(new Stock("IBM"));
        stockList.add(new Stock("ATT"));
        stockList.add(new Stock("PSA"));
    }

    public static Stock aleajactaest() {
        int alea = randomGenerator.nextInt(stockList.size());
        return stockList.get(alea);
    }

}