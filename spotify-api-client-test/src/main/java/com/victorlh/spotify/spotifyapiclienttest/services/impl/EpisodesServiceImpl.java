package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.models.EpisodeObject;
import com.victorlh.spotify.apiclient.models.ListEpisodesObject;
import com.victorlh.spotify.apiclient.services.episodes.EpisodesApiService;
import com.victorlh.spotify.apiclient.services.episodes.models.EpisodeRequest;
import com.victorlh.spotify.apiclient.services.episodes.models.ListEpisodesRequest;
import com.victorlh.spotify.spotifyapiclienttest.exceptions.SpotifyApiExceptionRuntime;
import com.victorlh.spotify.spotifyapiclienttest.services.EpisodesService;
import com.victorlh.spotify.spotifyapiclienttest.services.SpotifyClientService;
import org.springframework.stereotype.Service;

@Service
public class EpisodesServiceImpl implements EpisodesService {

	private final SpotifyClientService spotifyClientService;

	public EpisodesServiceImpl(SpotifyClientService spotifyClientService) {
		this.spotifyClientService = spotifyClientService;
	}

	@Override
	public ListEpisodesObject getListEpisodes(ListEpisodesRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		EpisodesApiService episodesApiService = spotifyApiClient.getEpisodesApiService();
		try {
			return episodesApiService.getListEpisodes(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public EpisodeObject getEpisode(EpisodeRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		EpisodesApiService episodesApiService = spotifyApiClient.getEpisodesApiService();
		try {
			return episodesApiService.getEpisode(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}
}
