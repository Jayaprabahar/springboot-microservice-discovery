package com.jayaprabahar.bestmusicdb.artist;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jayaprabahar.bestmusicdb.artist.controller.ArtistController;

@SpringBootTest
class BestmusicdbArtistServiceApplicationTests {
	
	@Autowired
	private ArtistController controller;

	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
