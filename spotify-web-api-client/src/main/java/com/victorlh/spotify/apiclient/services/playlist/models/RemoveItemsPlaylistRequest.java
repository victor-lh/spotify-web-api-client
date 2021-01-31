package com.victorlh.spotify.apiclient.services.playlist.models;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemoveItemsPlaylistRequest {

	@JsonProperty("snapshot_id")
	private final String snapshotId;
	@Singular
	private final List<TrackDelete> tracks;

	@Builder
	@Getter
	@ToString
	public static class TrackDelete {
		private final String uri;
		@Singular
		private final List<Integer> positions;
	}

}
