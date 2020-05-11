/**
 * 
 */
package com.jayaprabahar.bestmusicdb.artist.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.errorhandling </p>
 * <p> Title : ArtistNotFoundException.java </p>
 * <p> Description: Exception class to throw artist already exist exception </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ArtistAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1002792388398477653L;

	/**
	 * 
	 */
	public ArtistAlreadyExistException(String artistName) {
		super("Artist " + artistName + " already exist.");
	}

}
