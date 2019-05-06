package com.nab.btc.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nab.btc.model.BTC;
import com.nab.btc.model.Quote;
import com.nab.btc.service.BTCService;


/**
 * @author Tushar Sisode
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BTCController.class)
public class BTCControllerTests {
	
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private BTCService btcService;
    
	@Test
	public void testGetBTCQuotes() throws Exception {
    	BTC btc = new BTC();
    	btc.setCurrency("BTC");
    	btc.setDate("20190507");
    	
    	List<Quote> quotes = new ArrayList<Quote>();
    	Quote quote = new Quote();
    	quote.setTime("0915");
    	quote.setPrice("34.98");
    	quotes.add(quote);
		btc.setQuotes(quotes);

        given(btcService.filterBTCQuotes("05/07/2019"))
          .willReturn(btc);
        
	    mvc.perform(get("/btc?inputDate=05/07/2019")
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      .andExpect(status().isOk())
	    	      .andExpect(jsonPath("$.date",is(btc.getDate())))
	    	      .andExpect(jsonPath("$.currency", is(btc.getCurrency())));
	}

}
