package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.models.objects.EpisodeObject;
import com.victorlh.spotify.apiclient.models.lists.ListEpisodesObject;
import com.victorlh.spotify.apiclient.services.episodes.EpisodesApiService;
import com.victorlh.spotify.apiclient.services.episodes.models.EpisodeRequest;
import com.victorlh.spotify.apiclient.services.episodes.models.MultipleEpisodesRequest;
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
	public ListEpisodesObject getListEpisodes(MultipleEpisodesRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		EpisodesApiService episodesApiService = spotifyApiClient.getEpisodesApiService();
		try {
			return episodesApiService.getMultipleEpisodes(request);
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
