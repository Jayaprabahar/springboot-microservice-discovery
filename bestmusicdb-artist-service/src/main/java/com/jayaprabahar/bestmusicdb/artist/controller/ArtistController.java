/**
 * 
 */
package com.jayaprabahar.bestmusicdb.artist.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.jayaprabahar.bestmusicdb.artist.consumer.AlbumServiceQueryBuilder;
import com.jayaprabahar.bestmusicdb.artist.consumer.CustomPageImpl;
import com.jayaprabahar.bestmusicdb.artist.dao.Artist;
import com.jayaprabahar.bestmusicdb.artist.dto.Album;
import com.jayaprabahar.bestmusicdb.artist.service.ArtistService;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.controller </p>
 * <p> Title : ArtistController.java </p>
 * <p> Description: Controller class for artist </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@RestController
@RequestMapping("/artists")
public class ArtistController {

	private ArtistService artistService;

	/**
	 * @param artistService
	 * @param webClientBuilder
	 */
	@Autowired
	public ArtistController(ArtistService artistService, WebClient.Builder webClientBuilder) {
		this.artistService = artistService;
		this.webClientBuilder = webClientBuilder;
	}

	/**
	 * @param artist
	 * @return
	 */
	@PostMapping
	private Artist newArtist(@RequestBody Artist artist) {
		return artistService.createArtist(artist);
	}

	/**
	 * @param artistId
	 * @param artist
	 * @return
	 */
	@PutMapping("/{artistId}")
	public Artist updateArtist(@PathVariable("artistId") Long artistId, @RequestBody Artist artist) {
		return artistService.updateArtist(artistId, artist);
	}

	/**
	 * @param artistName
	 * @param pageable
	 * @return
	 */
	@GetMapping
	public Page<Artist> listAndFilterArtists(@RequestParam("artistName") Optional<String> artistName, Pageable pageable) {
		return artistService.getAllArtists(artistName.orElse(""), pageable);
	}

}
