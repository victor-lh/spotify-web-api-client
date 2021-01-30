package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.pagination.ListArtistsCursorPagingObject;
import com.victorlh.spotify.apiclient.services.follow.models.*;

import java.util.Map;

public interface FollowService {

	void followPlaylist(FollowPlaylistRequest request);

	void unfollowPlaylist(String playlistId);

	Map<String, Boolean> checkUsersFollowPlaylist(CheckUsersFollowPlaylistRequest request);

	ListArtistsCursorPagingObject getUserFollowedArtists(UserFollowedArtistsRequest request);

	ListArtistsCursorPagingObject getUserFollowedArtists(String paginationUrl);

	void followArtistsOrUsers(FollowArtistsOrUsersRequest request);


	void unfollowArtistsOrUsers(UnfollowArtistsOrUsersRequest request);

	Map<String, Boolean> getFollowingStateArtistOrUsers(StatusFollowingArtistsOrUsersRequest request);
}
