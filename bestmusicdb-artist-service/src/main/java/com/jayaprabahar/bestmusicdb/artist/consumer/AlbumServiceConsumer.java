/**
 * 
 */
package com.jayaprabahar.bestmusicdb.artist.consumer;

import java.util.concurrent.TimeUnit;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

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
		// Added delay which is expected
		TcpClient tcpClient = TcpClient
				  .create()
				  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
				  .doOnConnected(connection -> {
				      connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
				      connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
				  });
				 
		return WebClient.builder()
				.baseUrl("http://album-service/albums/")
				.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)));
	}

}
