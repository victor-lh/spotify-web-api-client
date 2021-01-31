package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.models.objects.AlbumObject;
import com.victorlh.spotify.apiclient.models.lists.ListAlbumsObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.models.objects.SimplifiedTrackObject;
import com.victorlh.spotify.apiclient.services.albums.AlbumsApiService;
import com.victorlh.spotify.apiclient.services.albums.models.AlbumRequest;
import com.victorlh.spotify.apiclient.services.albums.models.AlbumTracksRequest;
import com.victorlh.spotify.apiclient.services.albums.models.MultipleAlbumsRequest;
import com.victorlh.spotify.spotifyapiclienttest.exceptions.SpotifyApiExceptionRuntime;
import com.victorlh.spotify.spotifyapiclienttest.services.AlbumService;
import com.victorlh.spotify.spotifyapiclienttest.services.SpotifyClientService;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {

	private final SpotifyClientService spotifyClientService;

	public AlbumServiceImpl(SpotifyClientService spotifyClientService) {
		this.spotifyClientService = spotifyClientService;
	}

	@Override
	public ListAlbumsObject getAlbumsList(MultipleAlbumsRequest multipleAlbumsRequest) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		AlbumsApiService albumsApiService = spotifyApiClient.getAlbumsApiService();
		try {
			return albumsApiService.getMultipleAlbums(multipleAlbumsRequest);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public AlbumObject getAlbum(AlbumRequest albumRequest) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		AlbumsApiService albumsApiService = spotifyApiClient.getAlbumsApiService();
		try {
			return albumsApiService.getAlbum(albumRequest);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<SimplifiedTrackObject> getAlbumTracks(AlbumTracksRequest albumTracksRequest) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		AlbumsApiService albumsApiService = spotifyApiClient.getAlbumsApiService();
		try {
			return albumsApiService.getAlbumTracks(albumTracksRequest);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<SimplifiedTrackObject> getAlbumTracks(String next) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		AlbumsApiService albumsApiService = spotifyApiClient.getAlbumsApiService();
		try {
			return albumsApiService.getAlbumTracks(next);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}
}
