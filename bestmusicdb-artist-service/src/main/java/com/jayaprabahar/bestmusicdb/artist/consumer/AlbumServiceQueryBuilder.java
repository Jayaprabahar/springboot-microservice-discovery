/**
 * 
 */
package com.jayaprabahar.bestmusicdb.artist.consumer;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.artist </p>
 * <p> Title : AlbumServiceQueryBuilder.java </p>
 * <p> Description: Simple util method to create queryMap from filter and pageable information </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
public class AlbumServiceQueryBuilder {

	/**
	 * MultiValueMap creator for album service filter
	 * 
	 * @param artistId
	 * @param genreLike
	 * @param pageable
	 * @return
	 */
	public static MultiValueMap<String, String> buildAlbumServiceQuery(Long artistId, Optional<String> genreLike, Pageable pageable) {
		MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<>();
		queryMap.add("genre", genreLike.orElse(""));
		queryMap.add("page", String.valueOf(pageable.getPageNumber()));
		queryMap.add("size", String.valueOf(pageable.getPageSize()));
		pageable.getSort().forEach(order -> queryMap.add("sort", order.getProperty() + "," + order.getDirection()));

		return queryMap;
	}
	
	/**
	 * MultiValueMap creator for discog service filter
	 * 
	 * @param key
	 * @param secret
	 * @param artistName
	 * @param albumName
	 * @param pageable
	 * @return
	 */
	public static MultiValueMap<String, String> buildDiscogQueryUrl(String key, String secret, String artistName, String albumName) {
		MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<>();
		queryMap.add("key", key);
		queryMap.add("secret", secret);
		queryMap.add("artist", artistName);
		queryMap.add("title", albumName);
		
		// Always hit the master release
		queryMap.add("release", "master");

		return queryMap;
	}

}
