package com.victorlh.spotify.apiclient.services.follow.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class UnfollowArtistsOrUsersRequest {

	public enum FollowType {
		artist, user
	}

	private final FollowType type;
	@Singular
	private final List<String> ids;
}
