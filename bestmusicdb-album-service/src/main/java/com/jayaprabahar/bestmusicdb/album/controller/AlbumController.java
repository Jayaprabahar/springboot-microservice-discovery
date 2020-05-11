/**
 * 
 */
package com.jayaprabahar.bestmusicdb.album.controller;

import java.util.Optional;

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

import com.jayaprabahar.bestmusicdb.album.dao.Album;
import com.jayaprabahar.bestmusicdb.album.service.AlbumService;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.controller </p>
 * <p> Title : AlbumController.java </p>
 * <p> Description: Controller class for Album service </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@RestController
@RequestMapping("/albums")
public class AlbumController {

	private AlbumService albumService;

	/**
	 * @param albumService
	 */
	public AlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}

	/**
	 * @param artistId
	 * @param album
	 * @return
	 */
	@PostMapping("/{artistId}")
	private Album newAlbum(@PathVariable("artistId") Long artistId, @RequestBody Album album) {
		return albumService.createAlbum(artistId, album);
	}

	/**
	 * @param artistId
	 * @param albumId
	 * @param album
	 * @return
	 */
	@PutMapping("/{artistId}/{albumId}")
	public Album updateAlbum(@PathVariable("artistId") Long artistId, @PathVariable("albumId") Long albumId, @RequestBody Album album) {
		return albumService.updateAlbum(artistId, albumId, album);
	}

	/**
	 * @param artistId
	 * @param genreLike
	 * @param pageable
	 * @return
	 */
	@GetMapping("/{artistId}")
	public Page<Album> listAndFilterAlbums(@PathVariable("artistId") Long artistId, @RequestParam("genre") Optional<String> genreLike, Pageable pageable) {
		return albumService.getAllAlbumsByGenreArtist(genreLike.orElse(""), artistId, pageable);
	}

}
