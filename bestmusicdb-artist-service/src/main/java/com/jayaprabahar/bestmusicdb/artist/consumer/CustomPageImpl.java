/**
 * 
 */
package com.jayaprabahar.bestmusicdb.artist.consumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

/**
 * <p> Project : com.jayaprabahar.bestmusicdb.artist.consumer </p>
 * <p> Title : CustomPageImpl.java </p>
 * <p> Description: Custom PageImpl. Added to resolve no default construtor error due to ParameterizedTypeReference<PageImpl<Album>> error</p>
 * <p> Created: May 11, 2020 </p>
 * <p> Copyright: KLM Royal Dutch Airlines. All Rights Reserved. (c) 2020 </p>
 * <p> Company: AIRFRANCE-KLM </p>
 * 
 * @version 6.0.0
 * @author <a href="mailto:Jayaprabahar.Chandrasekaran@klm.com">Jayaprabahar</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CustomPageImpl<T> extends PageImpl<T> {

	private static final long serialVersionUID = -42427470695011725L;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public CustomPageImpl(@JsonProperty("content") List<T> content, @JsonProperty("number") int number, @JsonProperty("size") int size,
			@JsonProperty("totalElements") Long totalElements, @JsonProperty("pageable") JsonNode pageable, @JsonProperty("last") boolean last,
			@JsonProperty("totalPages") int totalPages, @JsonProperty("sort") JsonNode sort, @JsonProperty("first") boolean first,
			@JsonProperty("numberOfElements") int numberOfElements) {

		super(content, PageRequest.of(number, size), totalElements);
	}

	public CustomPageImpl(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	public CustomPageImpl(List<T> content) {
		super(content);
	}

	/**
	 * PageImpl does not have default constructor. So the implementation of it, has it
	 */
	public CustomPageImpl() {
		super(new ArrayList<>());
	}

}