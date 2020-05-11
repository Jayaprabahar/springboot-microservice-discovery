/**
 * 
 */
package com.jayaprabahar.bestmusicdb.album.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jayaprabahar.bestmusicdb.album.dao.Album;
import com.jayaprabahar.bestmusicdb.album.errorhandling.AlbumAlreadyExistException;
import com.jayaprabahar.bestmusicdb.album.errorhandling.AlbumNotFoundException;
import com.jayaprabahar.bestmusicdb.album.repository.AlbumRepository;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.service </p>
 * <p> Title : AlbumService.java </p>
 * <p> Description: Service class for Album </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Service
public class AlbumService {

	private AlbumRepository albumRepository;

	/**
	 * @param albumRepository
	 */
	public AlbumService(AlbumRepository albumRepository) {
		this.albumRepository = albumRepository;
	}

	/**
	 * @param artistId
	 * @param album
	 * @return
	 */
	public Album createAlbum(Long artistId, Album album) {
		if (CollectionUtils.isEmpty(albumRepository.findAllByArtistIdAndTitle(artistId, album.getTitle()))) {
			return albumRepository.save(Album.builder().artistId(artistId).title(album.getTitle()).year(album.getYear()).genre(album.getGenre()).build());
		} else {
			throw new AlbumAlreadyExistException(album.getTitle(), artistId);
		}
	}

	/**
	 * @param artistId
	 * @param albumId
	 * @param album
	 * @return
	 */
	public Album updateAlbum(Long artistId, Long albumId, Album album) {
		List<Album> filteredEntries = albumRepository.findAllByArtistIdAndAlbumId(artistId, albumId);

		if (CollectionUtils.isEmpty(filteredEntries)) {
			throw new AlbumNotFoundException(artistId, albumId);
		} else {
			album.setArtistId(artistId);
			album.setAlbumId(albumId);
			albumRepository.save(album);
		}
		return album;
	}

	/**
	 * @param genre
	 * @param artistId
	 * @param pageable
	 * @return
	 */
	public Page<Album> getAllAlbumsByGenreArtist(String genre, Long artistId, Pageable pageable) {
		return albumRepository.findAllByArtistIdAndGenreContainingIgnoreCase(artistId, genre, pageable);
	}

}
