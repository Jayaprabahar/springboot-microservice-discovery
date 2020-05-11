/**
 * 
 */
package com.jayaprabahar.bestmusicdb.album.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.errorhandling </p>
 * <p> Title : AlbumAlreadyExistException.java </p>
 * <p> Description: Exception class for Album Already Exist error </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class AlbumAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5420732350812547968L;

	/**
	 * 
	 */
	public AlbumAlreadyExistException(String albumName, Long artistId) {
		super("Album " + albumName + " already exist for artist id " + artistId);
	}

}
