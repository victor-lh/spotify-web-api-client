package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.exceptions.SpotifyWebApiClientException;
import com.victorlh.spotify.apiclient.models.pagination.ListArtistsCursorPagingObject;
import com.victorlh.spotify.apiclient.services.follow.FollowApiService;
import com.victorlh.spotify.apiclient.services.follow.models.*;
import com.victorlh.spotify.spotifyapiclienttest.exceptions.SpotifyApiExceptionRuntime;
import com.victorlh.spotify.spotifyapiclienttest.services.FollowService;
import com.victorlh.spotify.spotifyapiclienttest.services.SpotifyClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FollowServiceImpl implements FollowService {

	private final SpotifyClientService spotifyClientService;

	@Autowired
	public FollowServiceImpl(SpotifyClientService spotifyClientService) {
		this.spotifyClientService = spotifyClientService;
	}

	@Override
	public void followPlaylist(FollowPlaylistRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		FollowApiService followApiService = spotifyApiClient.getFollowApiService();
		try {
			followApiService.followPlaylist(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void unfollowPlaylist(String playlistId) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		FollowApiService followApiService = spotifyApiClient.getFollowApiService();
		try {
			followApiService.unfollowPlaylist(playlistId);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public Map<String, Boolean> checkUsersFollowPlaylist(CheckUsersFollowPlaylistRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		FollowApiService followApiService = spotifyApiClient.getFollowApiService();
		try {
			return followApiService.checkUsersFollowPlaylist(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public ListArtistsCursorPagingObject getUserFollowedArtists(UserFollowedArtistsRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		FollowApiService followApiService = spotifyApiClient.getFollowApiService();
		try {
			return followApiService.getUserFollowedArtists(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public ListArtistsCursorPagingObject getUserFollowedArtists(String paginationUrl) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		FollowApiService followApiService = spotifyApiClient.getFollowApiService();
		try {
			return followApiService.getUserFollowedArtists(paginationUrl);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void followArtistsOrUsers(FollowArtistsOrUsersRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		FollowApiService followApiService = spotifyApiClient.getFollowApiService();
		try {
			followApiService.followArtistsOrUsers(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void unfollowArtistsOrUsers(UnfollowArtistsOrUsersRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		FollowApiService followApiService = spotifyApiClient.getFollowApiService();
		try {
			followApiService.unfollowArtistsOrUsers(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public Map<String, Boolean> getFollowingStateArtistOrUsers(StatusFollowingArtistsOrUsersRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		FollowApiService followApiService = spotifyApiClient.getFollowApiService();
		try {
			return followApiService.getFollowingStateArtistOrUsers(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}
}
