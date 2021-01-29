package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.models.objects.ArtistObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.models.objects.TrackObject;
import com.victorlh.spotify.apiclient.services.personalization.PersonalizationApiService;
import com.victorlh.spotify.apiclient.services.personalization.models.GetUserTopRequest;
import com.victorlh.spotify.spotifyapiclienttest.exceptions.SpotifyApiExceptionRuntime;
import com.victorlh.spotify.spotifyapiclienttest.services.PersonalizationService;
import com.victorlh.spotify.spotifyapiclienttest.services.SpotifyClientService;
import org.springframework.stereotype.Service;

@Service
public class PersonalizationServiceImpl implements PersonalizationService {

	private final SpotifyClientService spotifyClientService;

	public PersonalizationServiceImpl(SpotifyClientService spotifyClientService) {
		this.spotifyClientService = spotifyClientService;
	}

	@Override
	public PagingObject<TrackObject> getUserTopTracks(GetUserTopRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PersonalizationApiService personalizationApiService = spotifyApiClient.getPersonalizationApiService();
		try {
			return personalizationApiService.getUserTopTracks(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<TrackObject> getUserTopTracks(String paginationUri) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PersonalizationApiService personalizationApiService = spotifyApiClient.getPersonalizationApiService();
		try {
			return personalizationApiService.getUserTopTracks(paginationUri);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<ArtistObject> getUserTopArtist(GetUserTopRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PersonalizationApiService personalizationApiService = spotifyApiClient.getPersonalizationApiService();
		try {
			return personalizationApiService.getUserTopArtist(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<ArtistObject> getUserTopArtist(String paginationUri) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PersonalizationApiService personalizationApiService = spotifyApiClient.getPersonalizationApiService();
		try {
			return personalizationApiService.getUserTopArtist(paginationUri);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}
}
