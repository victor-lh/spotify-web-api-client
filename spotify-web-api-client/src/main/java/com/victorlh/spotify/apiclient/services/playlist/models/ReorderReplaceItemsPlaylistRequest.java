package com.victorlh.spotify.apiclient.services.playlist.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ReorderReplaceItemsPlaylistRequest {

	@Singular("uri")
	@JsonIgnore
	private final List<String> uris;
	@JsonProperty("range_start")
	private final Integer rangeStart;
	@JsonProperty("range_length")
	private final Integer rangeLength;
	@JsonProperty("insert_before")
	private final Integer insertBefore;
	@JsonProperty("snapshot_id")
	private final String snapshotId;

}
