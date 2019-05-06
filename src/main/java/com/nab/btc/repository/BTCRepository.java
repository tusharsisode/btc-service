package com.nab.btc.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nab.btc.model.BTC;

/**
 * @author Tushar Sisode
 *
 */
@Repository
public class BTCRepository {

	/**
	 * @return
	 */
	@Cacheable("btc")
	public List<BTC> fetchBTCQuotes() {
		// Read the JSON file containing the BTC currency data
		List<BTC> btcList = null;
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<BTC>> mapType = new TypeReference<List<BTC>>() {
		};
		InputStream is = TypeReference.class.getResourceAsStream("/json/btc.json");
		try {
			btcList = mapper.readValue(is, mapType);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return btcList;
	}

}
