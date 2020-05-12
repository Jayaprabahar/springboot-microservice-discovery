/**
 * 
 */
package com.jayaprabahar.bestmusicdb.artist.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.dao </p>
 * <p> Title : Artist.java </p>
 * <p> Description: Entity class to handle database table information for artist </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	long artistId;
	
	String artistName;

}
