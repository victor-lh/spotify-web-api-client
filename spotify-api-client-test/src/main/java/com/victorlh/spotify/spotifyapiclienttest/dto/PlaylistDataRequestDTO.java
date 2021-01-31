package com.victorlh.spotify.spotifyapiclienttest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDataRequestDTO {
	private String name;
	private String description;
	@JsonProperty("public")
	private Boolean publicPlaylist;
	private Boolean collaborative;
}
