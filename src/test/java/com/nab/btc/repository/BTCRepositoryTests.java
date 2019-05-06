package com.nab.btc.repository;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nab.btc.model.BTC;

/**
 * @author Tushar Sisode
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BTCRepositoryTests {

    @Autowired
    private BTCRepository btcRepository;
    
	@Test
	public void testFetchBTCQuotes() {
		List<BTC> btcQuotes = btcRepository.fetchBTCQuotes();
		assertFalse(btcQuotes.isEmpty()); // Test if returned list is not empty
	}

}
