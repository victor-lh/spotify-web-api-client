package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.exceptions.SpotifyWebApiClientException;
import com.victorlh.spotify.apiclient.models.ListSimplifiedShowObject;
import com.victorlh.spotify.apiclient.models.PagingObject;
import com.victorlh.spotify.apiclient.models.ShowObject;
import com.victorlh.spotify.apiclient.models.SimplifiedEpisodeObject;
import com.victorlh.spotify.apiclient.services.shows.ShowsApiService;
import com.victorlh.spotify.apiclient.services.shows.models.ListShowsRequest;
import com.victorlh.spotify.apiclient.services.shows.models.ShowEpisodesRequest;
import com.victorlh.spotify.apiclient.services.shows.models.ShowRequest;
import com.victorlh.spotify.spotifyapiclienttest.services.ShowsService;
import com.victorlh.spotify.spotifyapiclienttest.services.SpotifyClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowsServiceImpl implements ShowsService {

	private final SpotifyClientService spotifyClientService;

	@Autowired
	public ShowsServiceImpl(SpotifyClientService spotifyClientService) {
		this.spotifyClientService = spotifyClientService;
	}

	@Override
	public ListSimplifiedShowObject getListShows(ListShowsRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		ShowsApiService showsApiService = spotifyApiClient.getShowsApiService();
		try {
			return showsApiService.getListShows(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyWebApiClientException(e);
		}
	}

	@Override
	public ShowObject getShow(ShowRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		ShowsApiService showsApiService = spotifyApiClient.getShowsApiService();
		try {
			return showsApiService.getShow(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyWebApiClientException(e);
		}
	}

	@Override
	public PagingObject<SimplifiedEpisodeObject> getShowEpisodes(ShowEpisodesRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		ShowsApiService showsApiService = spotifyApiClient.getShowsApiService();
		try {
			return showsApiService.getShowEpisodes(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyWebApiClientException(e);
		}
	}

	@Override
	public PagingObject<SimplifiedEpisodeObject> getShowEpisodes(String paginationUrl) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		ShowsApiService showsApiService = spotifyApiClient.getShowsApiService();
		try {
			return showsApiService.getShowEpisodes(paginationUrl);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyWebApiClientException(e);
		}
	}
}
