/**
 * 
 */
package com.jayaprabahar.bestmusicdb.album.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jayaprabahar.bestmusicdb.album.dao.Album;
import com.jayaprabahar.bestmusicdb.album.dao.AlbumKey;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.repository </p>
 * <p> Title : AlbumRepository.java </p>
 * <p> Description: Album repository class </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, AlbumKey> {

	// Find albums by artistId & title
	List<Album> findAllByArtistIdAndTitle(Long artistId, String title);

	// Find albums by artistId & albumId
	List<Album> findAllByArtistIdAndAlbumId(Long artistId, Long albumId);

	// Find albums by artistId, genre & pagination criteria with contains and case insensitive search
	Page<Album> findAllByArtistIdAndGenreContainingIgnoreCase(Long artistId, String genre, Pageable pageable);

}
