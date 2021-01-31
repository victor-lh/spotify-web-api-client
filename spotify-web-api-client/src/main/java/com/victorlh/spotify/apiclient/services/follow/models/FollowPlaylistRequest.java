package com.victorlh.spotify.apiclient.services.follow.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class FollowPlaylistRequest {

	private final String playlistId;
	private final Boolean publicPlaylist;
}
