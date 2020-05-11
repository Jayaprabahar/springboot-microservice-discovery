/**
 * 
 */
package com.jayaprabahar.bestmusicdb.artist.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.errorhandling </p>
 * <p> Title : ArtistNotFoundException.java </p>
 * <p> Description: Exception class to throw artist not found exception </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtistNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1002792388398477653L;

	/**
	 * 
	 */
	public ArtistNotFoundException(long artistId) {
		super("Artist with id " + artistId + " not found.");
	}

}
