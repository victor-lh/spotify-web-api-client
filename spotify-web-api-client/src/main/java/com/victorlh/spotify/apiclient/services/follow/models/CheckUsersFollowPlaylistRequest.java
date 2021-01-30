package com.victorlh.spotify.apiclient.services.follow.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class CheckUsersFollowPlaylistRequest {

	private final String playlistId;
	@Singular
	private final List<String> ids;
}
