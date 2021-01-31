package com.victorlh.spotify.apiclient.services.player.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PlayPlaybackRequest {

	@Singular
	@JsonProperty("device_id")
	private final List<String> deviceIds;
	@JsonProperty("context_uri")
	private final String contextUri;
	@Singular(value = "uri")
	private final List<String> uris;
	private final Offset offset;
	@JsonProperty("position_ms")
	private final Long positionMs;

	@Builder
	@Getter
	@ToString
	public static class Offset {
		private final String uri;
		private final Integer position;
	}

}
