package com.victorlh.spotify.apiclient.services.player.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GetRecentlyPlayedTracksRequest {

	private final Integer limit;
	private final Long after;
	private final Long before;

}
