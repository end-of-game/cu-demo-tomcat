package fr.treeptik.rest.model;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class RandomStockTest {
    @Test
    public void testStockList() {
        assertThat(RandomStock.stockList, hasItem(hasProperty("name", is("IBM"))));
    }
    
    @Test
    public void testAleajactaest() {
        Stock stock = RandomStock.aleajactaest();
        
        assertNotNull(stock);
        assertThat(RandomStock.stockList, hasItem(stock));
    }
}
