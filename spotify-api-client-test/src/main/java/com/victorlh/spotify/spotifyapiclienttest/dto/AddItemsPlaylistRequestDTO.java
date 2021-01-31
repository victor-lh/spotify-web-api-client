package com.victorlh.spotify.spotifyapiclienttest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItemsPlaylistRequestDTO {

	private List<String> uris;
	private Integer position;

}
