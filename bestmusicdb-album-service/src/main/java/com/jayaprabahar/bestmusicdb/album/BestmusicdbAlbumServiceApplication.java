package com.jayaprabahar.bestmusicdb.album;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.album </p>
 * <p> Title : BestmusicdbAlbumServiceApplication.java </p>
 * <p> Description: Album Service application. Also an eureka client </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@SpringBootApplication
@EnableEurekaClient
public class BestmusicdbAlbumServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BestmusicdbAlbumServiceApplication.class, args);
	}

}
