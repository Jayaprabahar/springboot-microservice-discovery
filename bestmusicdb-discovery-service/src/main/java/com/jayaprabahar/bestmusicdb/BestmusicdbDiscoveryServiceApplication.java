package com.jayaprabahar.bestmusicdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb </p>
 * <p> Title : BestmusicdbDiscoveryServiceApplication.java </p>
 * <p> Description: Discovery service application </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@SpringBootApplication
@EnableEurekaServer
public class BestmusicdbDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BestmusicdbDiscoveryServiceApplication.class, args);
	}

}
