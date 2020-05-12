/**
 * 
 */
package com.jayaprabahar.bestmusicdb.artist.util;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayaprabahar.bestmusicdb.artist.consumer.CustomPageImpl;
import com.jayaprabahar.bestmusicdb.artist.dao.Artist;
import com.jayaprabahar.bestmusicdb.artist.dto.Album;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.artist.util </p>
 * <p> Title : JsonUtils.java </p>
 * <p> Description: Holds utility methods for Json related operations </p>
 * <p> Created: May 12, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Component
public class JsonUtils {

	/**
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public <T> T mapFromJson(String json, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
		return new ObjectMapper().readValue(json, clazz);
	}

	/**
	 * Deserialize String having entities of Page
	 *   
	 * @param <T>
	 * @param json
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public <T> Page<Artist> deserializeArtistPagableFromJson(String json) throws JsonMappingException, JsonProcessingException {
		return new ObjectMapper().readValue(json, new TypeReference<CustomPageImpl<Artist>>() {
		});
	}

	/**
	 * @param <T>
	 * @param json
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public <T> Page<Album> deserializeAlbumPagableFromJson(String json) throws JsonMappingException, JsonProcessingException {
		return new ObjectMapper().readValue(json, new TypeReference<CustomPageImpl<Album>>() {
		});
	}

}
