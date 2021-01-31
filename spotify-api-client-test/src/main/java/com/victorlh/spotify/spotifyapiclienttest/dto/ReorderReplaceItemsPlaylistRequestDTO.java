package com.victorlh.spotify.spotifyapiclienttest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReorderReplaceItemsPlaylistRequestDTO {

	private List<String> uris;
	@JsonProperty("range_start")
	private Integer rangeStart;
	@JsonProperty("range_length")
	private Integer rangeLength;
	@JsonProperty("insert_before")
	private Integer insertBefore;

}
