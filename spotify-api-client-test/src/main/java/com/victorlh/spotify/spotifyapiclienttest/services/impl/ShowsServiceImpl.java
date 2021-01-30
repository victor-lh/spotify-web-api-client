package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.exceptions.SpotifyWebApiClientException;
import com.victorlh.spotify.apiclient.models.lists.ListSimplifiedShowsObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.models.objects.ShowObject;
import com.victorlh.spotify.apiclient.models.objects.SimplifiedEpisodeObject;
import com.victorlh.spotify.apiclient.services.shows.ShowsApiService;
import com.victorlh.spotify.apiclient.services.shows.models.MultipleShowsRequest;
import com.victorlh.spotify.apiclient.services.shows.models.ShowEpisodesRequest;
import com.victorlh.spotify.apiclient.services.shows.models.ShowRequest;
import com.victorlh.spotify.spotifyapiclienttest.exceptions.SpotifyApiExceptionRuntime;
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
	public ListSimplifiedShowsObject getListShows(MultipleShowsRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		ShowsApiService showsApiService = spotifyApiClient.getShowsApiService();
		try {
			return showsApiService.getMultipleShows(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public ShowObject getShow(ShowRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		ShowsApiService showsApiService = spotifyApiClient.getShowsApiService();
		try {
			return showsApiService.getShow(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<SimplifiedEpisodeObject> getShowEpisodes(ShowEpisodesRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		ShowsApiService showsApiService = spotifyApiClient.getShowsApiService();
		try {
			return showsApiService.getShowEpisodes(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<SimplifiedEpisodeObject> getShowEpisodes(String paginationUrl) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		ShowsApiService showsApiService = spotifyApiClient.getShowsApiService();
		try {
			return showsApiService.getShowEpisodes(paginationUrl);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}
}
