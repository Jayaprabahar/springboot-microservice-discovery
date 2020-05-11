/**
 * 
 */
package com.jayaprabahar.bestmusicdb.artist.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.dto </p>
 * <p> Title : Album.java </p>
 * <p> Description: Data Transfer Object to imitate Album entity coming from other service </p>
 * <p> Created: May 11, 2020 </p>
 * <p> Copyright: KLM Royal Dutch Airlines. All Rights Reserved. (c) 2020 </p>
 * <p> Company: AIRFRANCE-KLM </p>
 * 
 * @version 6.0.0
 * @author <a href="mailto:Jayaprabahar.Chandrasekaran@klm.com">Jayaprabahar</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 944750091233331186L;

	Long artistId;

	Long albumId;
	
	String artistName;

	String title;

	int year;

	String genre;

}
