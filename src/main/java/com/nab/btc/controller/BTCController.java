package com.nab.btc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nab.btc.model.BTC;
import com.nab.btc.service.BTCService;

/**
 * @author Tushar Sisode
 *
 */
@RestController
public class BTCController {

	@Autowired
	private BTCService btcService;
	
	/**
	 * @param inputDate
	 * @return
	 */
	@CrossOrigin() //Not a good practice, but keeping globally open for this solution.
	@GetMapping("/btc")
	public BTC getBTCQuotes(@RequestParam(value = "inputDate") String inputDate) {
		final Logger logger = LoggerFactory.getLogger(BTCController.class);
		logger.info(".... Fetching BTC Quotes for the Input Date");

		// Filter the BTC data for the inputDate
		BTC filteredBtcQuotes = btcService.filterBTCQuotes(inputDate);
		logger.info(".... Filtered BTC Quotes for the Input Date");
		
		// Return the found BTC quote details for the inputDate
		return filteredBtcQuotes;
	}
}
