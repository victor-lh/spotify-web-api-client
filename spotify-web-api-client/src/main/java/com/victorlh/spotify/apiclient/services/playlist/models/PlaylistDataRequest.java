package com.victorlh.spotify.apiclient.services.playlist.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PlaylistDataRequest {

	private final String name;
	private final String description;
	@JsonProperty("public")
	private final Boolean publicPlaylist;
	private final Boolean collaborative;
}
