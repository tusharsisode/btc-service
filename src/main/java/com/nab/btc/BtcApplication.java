package com.nab.btc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * @author Tushar Sisode
 *
 */
@SpringBootApplication
@EnableCaching
@EnableCircuitBreaker
public class BtcApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BtcApplication.class, args);
	}

}
