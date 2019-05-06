package com.nab.btc.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nab.btc.model.BTC;
import com.nab.btc.model.Quote;
import com.nab.btc.repository.BTCRepository;

/**
 * @author Tushar Sisode
 *
 */
@RunWith(SpringRunner.class)
public class BTCServiceTests {
	
    @TestConfiguration
    static class BTCServiceTestContextConfiguration {
  
        @Bean
        public BTCService btcService() {
            return new BTCService();
        }
    }
 
    @Autowired
    private BTCService btcService;
 
    @MockBean
    private BTCRepository btcRepository;
    
    @Before
    public void setUp() {
    	List<BTC> btcQuotes = new ArrayList<BTC>();
    	
    	BTC btc = new BTC();
    	btc.setCurrency("BTC");
    	btc.setDate("20190507");
    	
    	List<Quote> quotes = new ArrayList<Quote>();
    	Quote quote = new Quote();
    	quote.setTime("0915");
    	quote.setPrice("34.98");
    	quotes.add(quote);
		btc.setQuotes(quotes);

		btcQuotes.add(btc);
		
        Mockito.when(btcRepository.fetchBTCQuotes())
          .thenReturn(btcQuotes);
    }
    
	@Test
	public void testFilterBTCQuotes() {
		BTC btc = btcService.filterBTCQuotes("05/07/2019");
		assertEquals("20190507", btc.getDate());
		assertEquals("BTC", btc.getCurrency());
		assertEquals("0915", btc.getQuotes().get(0).getTime());
		assertEquals("34.98", btc.getQuotes().get(0).getPrice());
	}

}
