/**
 * 
 */
package com.jayaprabahar.bestmusicdb.artist.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jayaprabahar.bestmusicdb.artist.dao.Artist;
import com.jayaprabahar.bestmusicdb.artist.errorhandling.ArtistAlreadyExistException;
import com.jayaprabahar.bestmusicdb.artist.errorhandling.ArtistNotFoundException;
import com.jayaprabahar.bestmusicdb.artist.repository.ArtistRepository;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.service </p>
 * <p> Title : ArtistService.java </p>
 * <p> Description: Service class for Artist </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Service
public class ArtistService {

	private ArtistRepository artistRepository;

	/**
	 * @param artistRepository
	 */
	public ArtistService(ArtistRepository artistRepository) {
		this.artistRepository = artistRepository;
	}

	/**
	 * Creates new artist throw error if already exist
	 * 
	 * @param artist
	 * @return
	 */
	public Artist createArtist(Artist artist) {
		if (CollectionUtils.isEmpty(artistRepository.findAllByArtistNameIgnoreCase(artist.getArtistName()))) {
			return artistRepository.save(Artist
					.builder()
					.artistName(artist.getArtistName())
					.build());
		} else {
			throw new ArtistAlreadyExistException(artist.getArtistName());
		}
	}

	/**
	 * Updates existing artist or throw error if not found
	 * 
	 * @param artistId
	 * @param artist
	 * @return
	 */
	public Artist updateArtist(Long artistId, Artist artist) {
		Artist entity = artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException(artistId));
		entity.setArtistName(artist.getArtistName());

		return artistRepository.save(entity);
	}

	/**
	 * Filter artists based on artist name like, other pagination queries
	 *  
	 * @param artistName
	 * @param pageable
	 * @return
	 */
	public Page<Artist> getAllArtists(String artistName, Pageable pageable) {
		return artistRepository.findAllByArtistNameContainingIgnoreCase(artistName, pageable);
	}

	/**
	 * Find artist information by atistId
	 * 
	 * @param artistId
	 * @return
	 */
	public Artist getArtistById(Long artistId) {
		return artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException(artistId));
	}

}
