/**
 * 
 */
package com.jayaprabahar.bestmusicdb.artist.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jayaprabahar.bestmusicdb.artist.dao.Artist;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.repository </p>
 * <p> Title : ArtistRepository.java </p>
 * <p> Description: Repository class for Artist </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

	List<Artist> findAllByArtistNameIgnoreCase(String artistName);

	Page<Artist> findAllByArtistNameContainingIgnoreCase(String artistName, Pageable pageable);

}
