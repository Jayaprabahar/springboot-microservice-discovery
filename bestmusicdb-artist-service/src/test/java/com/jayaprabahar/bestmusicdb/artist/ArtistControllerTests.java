/**
 * 
 */
package com.jayaprabahar.bestmusicdb.artist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayaprabahar.bestmusicdb.artist.dao.Artist;
import com.jayaprabahar.bestmusicdb.artist.errorhandling.ArtistAlreadyExistException;
import com.jayaprabahar.bestmusicdb.artist.errorhandling.ArtistNotFoundException;
import com.jayaprabahar.bestmusicdb.artist.repository.ArtistRepository;
import com.jayaprabahar.bestmusicdb.artist.util.JsonUtils;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.artist </p>
 * <p> Title : ArtistControllerTests.java </p>
 * <p> Description: TODO </p>
 * <p> Created: May 11, 2020 </p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class ArtistControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private JsonUtils jsonUtils;

	@Autowired
	private ArtistRepository artistRepository;
	
	@Test
	@Order(1)
	void testNewArtist() throws Exception {
		String relativeUrl = "/artists/";
		
		Artist artist = Artist.builder().artistName("Ricky Martin").build();

		mockMvc.perform(post(relativeUrl)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(artist)))
				.andExpect(status().isOk());

		List<Artist> artists = artistRepository.findAllByArtistNameIgnoreCase("Ricky Martin");

		assertEquals(1, artists.size());
		assertEquals("Ricky Martin", artists.get(0).getArtistName());
	}

	@Test
	@Order(2)
	void testNewArtist_whenDuplicateInsertion() throws Exception {
		String relativeUrl = "/artists/";
		
		Artist artist = Artist.builder().artistName("Ricky Martin").build();

		mockMvc.perform(post(relativeUrl)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(artist)))
				.andExpect(status().isConflict())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ArtistAlreadyExistException));
	}

	@Test
	@Order(3)
	public void testUpdateArtist() throws Exception {
		String relativeUrl = "/artists/1";
		
		Artist artist = Artist.builder().artistName("Katy Perry").build();
		
		MvcResult mvcResult = mockMvc.perform(put(relativeUrl)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(artist)))
				.andExpect(status().isOk())
				.andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals("{\"artistId\":1,\"artistName\":\"Katy Perry\"}", content);
		
		Artist artist1 = jsonUtils.mapFromJson(content, Artist.class);
		assertEquals("Katy Perry", artist1.getArtistName());
		
		List<Artist> artists = artistRepository.findAllByArtistNameIgnoreCase("Katy Perry");
		assertEquals(1, artists.size());
		assertEquals("Katy Perry", artists.get(0).getArtistName());
	}
	
	@Test
	@Order(4)
	public void testUpdateArtist_NonExistResource() throws Exception {
		//100 is non existing id, assuming not so many entries are created
		String relativeUrl = "/artists/100";
		
		Artist artist = Artist.builder().artistName("Michael Jackson").build();
		
		mockMvc.perform(put(relativeUrl)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(artist)))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ArtistNotFoundException));
	}
	
	@Test
	@Order(5)
	public void testFilterArtists() throws Exception {
		String relativeUrl = "/artists";
		
		List<Artist> artists = List.of(Artist.builder().artistName("Dilaxsha").build(),
				Artist.builder().artistName("Dilaxsha J").build(),
				Artist.builder().artistName("Dilaxsha Jayaprabahar").build(),
				Artist.builder().artistName("Jayaprabahar Dilaxsha").build(),
				Artist.builder().artistName("Jayaprabahar").build(),
				Artist.builder().artistName("Anonymous Artist").build()
				);
		
		assertEquals(artists.size(), artistRepository.saveAll(artists).size());
		
		MvcResult mvcResult = mockMvc.perform(get(relativeUrl)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString("")))
				.andExpect(status().isOk())
				.andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
		List<Artist> responseList = jsonUtils.deserializeArtistPagableFromJson(content).toList();
		
		// Assert that response contains all the input entries 
		assertTrue(responseList.containsAll(artists));
		
		relativeUrl = "/artists?artistName=Dilaxsha";
		
		mvcResult = mockMvc.perform(get(relativeUrl)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString("")))
				.andExpect(status().isOk())
				.andReturn();
		
		content = mvcResult.getResponse().getContentAsString();
		responseList = jsonUtils.deserializeArtistPagableFromJson(content).toList();
		
		// Assert that filtered response contains all the filtered input entries 
		assertTrue(responseList.containsAll(artists.stream().filter(e -> e.getArtistName().contains("Dilaxsha")).collect(Collectors.toList())));
	}
	
}
