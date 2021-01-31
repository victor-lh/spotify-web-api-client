package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.models.lists.ListArtistsObject;
import com.victorlh.spotify.apiclient.models.lists.ListTracksObject;
import com.victorlh.spotify.apiclient.models.objects.ArtistObject;
import com.victorlh.spotify.apiclient.models.objects.SimplifiedAlbumObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.services.artists.ArtistApiService;
import com.victorlh.spotify.apiclient.services.artists.models.ArtistAlbumsRequest;
import com.victorlh.spotify.apiclient.services.artists.models.ArtistTopTracksRequest;
import com.victorlh.spotify.apiclient.services.artists.models.MultipleArtistsRequest;
import com.victorlh.spotify.spotifyapiclienttest.exceptions.SpotifyApiExceptionRuntime;
import com.victorlh.spotify.spotifyapiclienttest.services.ArtistService;
import com.victorlh.spotify.spotifyapiclienttest.services.SpotifyClientService;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {

	private final SpotifyClientService spotifyClientService;

	public ArtistServiceImpl(SpotifyClientService spotifyClientService) {
		this.spotifyClientService = spotifyClientService;
	}

	@Override
	public ListArtistsObject getMultipleArtist(MultipleArtistsRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		ArtistApiService artistApiService = spotifyApiClient.getArtistApiService();
		try {
			return artistApiService.getMultipleArtists(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public ArtistObject getArtist(String artistId) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		ArtistApiService artistApiService = spotifyApiClient.getArtistApiService();
		try {
			return artistApiService.getArtist(artistId);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public ListTracksObject getTopTracks(ArtistTopTracksRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		ArtistApiService artistApiService = spotifyApiClient.getArtistApiService();
		try {
			return artistApiService.getArtistTopTracks(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public ListArtistsObject getRelatedArtists(String artistId) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		ArtistApiService artistApiService = spotifyApiClient.getArtistApiService();
		try {
			return artistApiService.getRelatedArtists(artistId);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<SimplifiedAlbumObject> getAlbums(ArtistAlbumsRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		ArtistApiService artistApiService = spotifyApiClient.getArtistApiService();
		try {
			return artistApiService.getAlbums(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<SimplifiedAlbumObject> getAlbums(String paginationUrl) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		ArtistApiService artistApiService = spotifyApiClient.getArtistApiService();
		try {
			return artistApiService.getAlbums(paginationUrl);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}
}
