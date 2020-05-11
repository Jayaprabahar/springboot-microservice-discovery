/**
 * 
 */
package com.jayaprabahar.bestmusicdb.artist.consumer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.controller </p>
 * <p> Title : AlbumServiceConsumer.java </p>
 * <p> Description: Creates reactive webclient to consume Album service </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Component
public class AlbumServiceConsumer {

	/**
	 * @return
	 */
	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder().baseUrl("http://album-service/albums/");
	}

}
