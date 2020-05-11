/**
 * 
 */
package com.jayaprabahar.bestmusicdb.album.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.errorhandling </p>
 * <p> Title : AlbumNotFoundException.java </p>
 * <p> Description: Exception class for Album not found error </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlbumNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7743595196593297186L;

	/**
	 * 
	 */
	public AlbumNotFoundException(Long artistId, Long albumId) {
		super("Album with artistId" + artistId + ", albumId " + albumId + " not found.");
	}

}
