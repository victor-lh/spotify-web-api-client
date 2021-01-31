package com.victorlh.spotify.spotifyapiclienttest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayPlaybackRequestDTO {

	@JsonProperty("device_id")
	private List<String> deviceIds;
	@JsonProperty("context_uri")
	private String contextUri;
	private List<String> uris;
	private Offset offset;
	@JsonProperty("position_ms")
	private Long positionMs;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Offset {
		private String uri;
		private Integer position;
	}

}
