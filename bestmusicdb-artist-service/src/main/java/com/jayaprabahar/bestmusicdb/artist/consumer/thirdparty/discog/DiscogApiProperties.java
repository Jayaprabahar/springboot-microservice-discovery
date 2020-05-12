/**
 * 
 */
package com.jayaprabahar.bestmusicdb.artist.consumer.thirdparty.discog;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.artist.consumer.thirdparty.discog </p>
 * <p> Title : DiscogApiProperties.java </p>
 * <p> Description: Java beans hold discog api properties </p>
 * <p> Created: May 12, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@ConfigurationProperties(prefix = "discog.api")
@Configuration
@Getter
@Setter
public class DiscogApiProperties {
	
	private String url;
	private String key;
	private String secret;
}
