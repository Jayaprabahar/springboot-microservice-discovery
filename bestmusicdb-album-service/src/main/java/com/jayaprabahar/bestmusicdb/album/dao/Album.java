/**
 * 
 */
package com.jayaprabahar.bestmusicdb.album.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.dao </p>
 * <p> Title : Album.java </p>
 * <p> Description: Album entity class </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Entity
@IdClass(AlbumKey.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album {

	@Id
	Long artistId;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long albumId;

	String title;

	int year;

	String genre;

}
