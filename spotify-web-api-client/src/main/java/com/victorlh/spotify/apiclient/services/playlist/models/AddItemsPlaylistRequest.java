package com.victorlh.spotify.apiclient.services.playlist.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class AddItemsPlaylistRequest {

	@Singular("uri")
	private final List<String> uris;
	private final Integer position;

}
