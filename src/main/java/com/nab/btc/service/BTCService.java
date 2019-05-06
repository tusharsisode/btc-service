package com.nab.btc.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nab.btc.model.BTC;
import com.nab.btc.repository.BTCRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Tushar Sisode
 *
 */
@Service
public class BTCService {

	@Autowired
	private BTCRepository btcRepository;

	/**
	 * @param inputDate
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "fallbackBTCQuote")
	public BTC filterBTCQuotes(String inputDate) {
		// Convert the inputDate in desired format
		String localDateyyyyMMdd = formatInputDate(inputDate);

		// Get BTC quotes from the repository
		List<BTC> btcQuotes = btcRepository.fetchBTCQuotes();

		// Filter BTC quote for the input date
		BTC filteredBtcQuote = btcQuotes.stream()
				.filter(d -> d.getDate().equals(localDateyyyyMMdd)).findAny()
				.orElse(new BTC());

		// Return filtered BTC Quote
		return filteredBtcQuote;
	}
	
	/**
	 * @return
	 */
	public BTC fallbackBTCQuote(String inputDate) {
		  return new BTC();
	}

	/**
	 * @param inputDate
	 * @return
	 */
	private String formatInputDate(String inputDate) {
		DateTimeFormatter formatterMMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDateMMddyyyy = LocalDate.parse(inputDate, formatterMMddyyyy);

		DateTimeFormatter formatteryyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
		String localDateyyyyMMdd = formatteryyyyMMdd.format(localDateMMddyyyy);
		return localDateyyyyMMdd;
	}
}
