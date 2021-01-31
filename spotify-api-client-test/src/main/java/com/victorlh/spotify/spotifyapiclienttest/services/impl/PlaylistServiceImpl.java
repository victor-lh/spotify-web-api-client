package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.models.objects.*;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.services.playlist.PlaylistApiService;
import com.victorlh.spotify.apiclient.services.playlist.models.*;
import com.victorlh.spotify.spotifyapiclienttest.exceptions.SpotifyApiExceptionRuntime;
import com.victorlh.spotify.spotifyapiclienttest.services.PlaylistService;
import com.victorlh.spotify.spotifyapiclienttest.services.SpotifyClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService {

	private final SpotifyClientService spotifyClientService;

	@Autowired
	public PlaylistServiceImpl(SpotifyClientService spotifyClientService) {
		this.spotifyClientService = spotifyClientService;
	}

	@Override
	public PagingObject<SimplifiedPlaylistObject> getCurrentUserPlaylist(Integer limit) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlaylistApiService playlistApiService = spotifyApiClient.getPlaylistApiService();
		try {
			return playlistApiService.getCurrentUserPlaylist(limit);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<SimplifiedPlaylistObject> getUserPlaylist(String userId, Integer limit) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlaylistApiService playlistApiService = spotifyApiClient.getPlaylistApiService();
		try {
			return playlistApiService.getUserPlaylist(userId, limit);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<SimplifiedPlaylistObject> getPlaylistPagination(String paginationUrl) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlaylistApiService playlistApiService = spotifyApiClient.getPlaylistApiService();
		try {
			return playlistApiService.getPlaylistPagination(paginationUrl);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PlaylistObject getPlaylist(String playlistId, GetPlaylistRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlaylistApiService playlistApiService = spotifyApiClient.getPlaylistApiService();
		try {
			return playlistApiService.getPlaylist(playlistId, request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<PlaylistTrackObject> getPlaylistItems(String playlistId, GetPlaylistItemsRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlaylistApiService playlistApiService = spotifyApiClient.getPlaylistApiService();
		try {
			return playlistApiService.getPlaylistItems(playlistId, request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<PlaylistTrackObject> getPlaylistItems(String paginationUrl) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlaylistApiService playlistApiService = spotifyApiClient.getPlaylistApiService();
		try {
			return playlistApiService.getPlaylistItems(paginationUrl);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public List<ImageObject> getPlaylistImages(String playlistId) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlaylistApiService playlistApiService = spotifyApiClient.getPlaylistApiService();
		try {
			return playlistApiService.getPlaylistImages(playlistId);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PlaylistObject createPlaylist(String userId, PlaylistDataRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlaylistApiService playlistApiService = spotifyApiClient.getPlaylistApiService();
		try {
			return playlistApiService.createPlaylist(userId, request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void editPlaylistData(String playlistId, PlaylistDataRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlaylistApiService playlistApiService = spotifyApiClient.getPlaylistApiService();
		try {
			playlistApiService.editPlaylistData(playlistId, request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PlaylistSnapshotObject addItemsPlaylist(String playlistId, AddItemsPlaylistRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlaylistApiService playlistApiService = spotifyApiClient.getPlaylistApiService();
		try {
			return playlistApiService.addItemsPlaylist(playlistId, request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PlaylistSnapshotObject removeItemsPlaylist(String playlistId, RemoveItemsPlaylistRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlaylistApiService playlistApiService = spotifyApiClient.getPlaylistApiService();
		try {
			return playlistApiService.removeItemsPlaylist(playlistId, request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PlaylistSnapshotObject reorderReplaceItemsPlaylist(String playlistId, ReorderReplaceItemsPlaylistRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlaylistApiService playlistApiService = spotifyApiClient.getPlaylistApiService();
		try {
			return playlistApiService.reorderReplaceItemsPlaylist(playlistId, request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}
}
