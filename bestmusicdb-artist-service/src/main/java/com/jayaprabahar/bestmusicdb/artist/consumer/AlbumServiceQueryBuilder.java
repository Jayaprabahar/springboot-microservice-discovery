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
 * <p> Copyright: KLM Royal Dutch Airlines. All Rights Reserved. (c) 2020 </p>
 * <p> Company: AIRFRANCE-KLM </p>
 * 
 * @version 6.0.0
 * @author <a href="mailto:Jayaprabahar.Chandrasekaran@klm.com">Jayaprabahar</a>
 */
public class AlbumServiceQueryBuilder {

	/**
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

}
