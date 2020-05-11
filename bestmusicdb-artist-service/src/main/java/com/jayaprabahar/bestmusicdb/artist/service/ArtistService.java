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
	 * @param artist
	 * @return
	 */
	public Artist createArtist(Artist artist) {
		if (CollectionUtils.isEmpty(artistRepository.findAllByArtistNameIgnoreCase(artist.getArtistName()))) {
			return artistRepository.save(Artist.builder().artistName(artist.getArtistName()).build());
		} else {
			throw new ArtistAlreadyExistException(artist.getArtistName());
		}
	}

	/**
	 * @param artistId
	 * @param artist
	 * @return
	 */
	public Artist updateArtist(Long artistId, Artist artist) {
		artistRepository.findById(artistId).ifPresentOrElse(entity -> {
			artist.setArtistId(entity.getArtistId());
			artistRepository.save(artist);
		}, () -> new ArtistNotFoundException(artistId));

		return artist;
	}

	/**
	 * @param artistName
	 * @param pageable
	 * @return
	 */
	public Page<Artist> getAllArtists(String artistName, Pageable pageable) {
		return artistRepository.findAllByArtistNameContainingIgnoreCase(artistName, pageable);
	}

	/**
	 * @param artistId
	 * @return
	 */
	public Artist getArtistById(Long artistId) {
		return artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException(artistId));
	}

}