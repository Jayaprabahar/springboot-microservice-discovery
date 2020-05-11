/**
 * 
 */
package com.jayaprabahar.bestmusicdb.album.dao;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.dao </p>
 * <p> Title : AlbumKey.java </p>
 * <p> Description: Combination primary key for album table</p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3420300011493606193L;

	Long artistId;

	Long albumId;
}
