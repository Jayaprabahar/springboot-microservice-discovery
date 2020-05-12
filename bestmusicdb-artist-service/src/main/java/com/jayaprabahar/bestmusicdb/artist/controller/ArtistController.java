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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayaprabahar.bestmusicdb.artist.consumer.AlbumServiceQueryBuilder;
import com.jayaprabahar.bestmusicdb.artist.consumer.CustomPageImpl;
import com.jayaprabahar.bestmusicdb.artist.consumer.thirdparty.discog.DiscogApiProperties;
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
	private WebClient.Builder webClientBuilder;
	private DiscogApiProperties discogApiProperties; 

	/**
	 * @param artistService
	 * @param webClientBuilder
	 * @param discogApiProperties
	 */
	@Autowired
	public ArtistController(ArtistService artistService, WebClient.Builder webClientBuilder, DiscogApiProperties discogApiProperties) {
		this.artistService = artistService;
		this.webClientBuilder = webClientBuilder;
		this.discogApiProperties = discogApiProperties;
	}

	/**
	 * Creates new artist throw error if already exist
	 * 
	 * @param artist
	 * @return
	 */
	@PostMapping
	private Artist newArtist(@RequestBody Artist artist) {
		return artistService.createArtist(artist);
	}

	/**
	 * Updates existing artist or throw error if not found
	 * 
	 * @param artistId
	 * @param artist
	 * @return
	 */
	@PutMapping("/{artistId}")
	public Artist updateArtist(@PathVariable("artistId") Long artistId, @RequestBody Artist artist) {
		return artistService.updateArtist(artistId, artist);
	}

	/**
	 * Filter artists based on artist name like, other pagination queries
	 * 
	 * @param artistName
	 * @param pageable
	 * @return
	 */
	@GetMapping
	public Page<Artist> filterArtists(@RequestParam("artistName") Optional<String> artistName, Pageable pageable) {
		return artistService.getAllArtists(artistName.orElse(""), pageable);
	}

	/**
	 * Creates new album for the artist id or throw error if already exist
	 * 
	 * @param artistId
	 * @param album
	 * @return
	 */
	@PostMapping("/{artistId}/albums")
	public Album newAlbumForArtist(@PathVariable("artistId") Long artistId, @RequestBody Album album) {
		Album newAlbum = webClientBuilder.build()
				.post()
				.uri("/{artistId}", artistId)
				.bodyValue(album)
				.retrieve()
				.bodyToMono(Album.class)
				.block();
		newAlbum.setArtistName(artistService.getArtistById(artistId).getArtistName());
		return newAlbum;
	}

	/**
	 * Updates existing album for the artist id or throw error if album not found
	 * 
	 * @param artistId
	 * @param albumId
	 * @param album
	 * @return
	 */
	@PutMapping("/{artistId}/albums/{albumId}")
	public Album updateAlbumForArtist(@PathVariable("artistId") Long artistId, @PathVariable("albumId") Long albumId, @RequestBody Album album) {
		Album newAlbum = webClientBuilder.build()
				.put()
				.uri("/{artistId}/{albumId}", artistId, albumId)
				.bodyValue(album)
				.retrieve()
				.bodyToMono(Album.class)
				.block();
		newAlbum.setArtistName(artistService.getArtistById(artistId).getArtistName());
		return newAlbum;
	}

	/**
	 * Filter albums based on artist id, genre & other pagination queries
	 * 
	 * @param artistId
	 * @param genreLike
	 * @param pageable
	 * @return
	 */
	@GetMapping("/{artistId}/albums")
	public Page<Album> getAlbumsForArtist(@PathVariable("artistId") Long artistId, @RequestParam("genre") Optional<String> genreLike, Pageable pageable) {
		Page<Album> albums = webClientBuilder
				.codecs(configurer -> configurer.defaultCodecs().enableLoggingRequestDetails(true))
				.build()
				.get()
				.uri(uriBuilder -> uriBuilder.path("/" + artistId)
						.queryParams(AlbumServiceQueryBuilder.buildAlbumServiceQuery(artistId, genreLike, pageable))
						.build())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<CustomPageImpl<Album>>() {
				}).block();

		// Extended to add resource URL from discog		
		// albums.forEach(e -> e.setArtistName(artistService.getArtistById(artistId).getArtistName()));
		
		albums.forEach(album -> {
			String artistName = artistService.getArtistById(artistId).getArtistName();
			album.setArtistName(artistName);
			album.setResourceUrl(getAlbumResourceFromDiscogs(artistName, album.getTitle()));
		});

		return albums;
	}

	/**
	 * Calls and fetches discog resource URL
	 * 
	 * @param artistName
	 * @param albumName
	 * @return
	 */
	private String getAlbumResourceFromDiscogs(String artistName, String albumName) {
		String responseSpec = webClientBuilder
				.baseUrl(discogApiProperties.getUrl())
				.codecs(configurer -> configurer.defaultCodecs().enableLoggingRequestDetails(true))
				.build()
				.get()
				.uri(uriBuilder -> uriBuilder.path("/")
						.queryParams(AlbumServiceQueryBuilder.buildDiscogQueryUrl(discogApiProperties.getKey(), discogApiProperties.getSecret(), artistName, albumName))
						.build())
				.retrieve()
				.bodyToMono(String.class).block();

		try {
			return new ObjectMapper().readTree(responseSpec).get("resource_url").asText("");
		} catch (JsonProcessingException e) {
		}
		return null;
	}

}
