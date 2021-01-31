package com.victorlh.spotify.apiclient.services.follow.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UserFollowedArtistsRequest {

	private final Integer limit;
	private final String after;
}
