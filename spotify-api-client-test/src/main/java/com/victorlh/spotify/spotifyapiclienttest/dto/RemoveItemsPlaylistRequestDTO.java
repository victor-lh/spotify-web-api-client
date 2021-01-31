package com.victorlh.spotify.spotifyapiclienttest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveItemsPlaylistRequestDTO {

	@JsonProperty("snapshot_id")
	private String snapshotId;
	private List<TrackDelete> tracks;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class TrackDelete {
		private String uri;
		private Integer position;
	}
}
