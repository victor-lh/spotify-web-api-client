package com.victorlh.spotify.apiclient.services.episodes;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.EpisodeObject;
import com.victorlh.spotify.apiclient.models.ListEpisodesObject;
import com.victorlh.spotify.apiclient.services.AbstractApiService;
import com.victorlh.spotify.apiclient.services.episodes.models.EpisodeRequest;
import com.victorlh.spotify.apiclient.services.episodes.models.MultipleEpisodesRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;

@Slf4j
public class EpisodesApiService extends AbstractApiService {

	private static final String EPISODES_PATH = "episodes";

	@Builder
	public EpisodesApiService(SpotifyApiClient spotifyApiClient) {
		super(spotifyApiClient);
	}

	public ListEpisodesObject getMultipleEpisodes(MultipleEpisodesRequest request) throws SpotifyGeneralApiException {
		log.trace("Call EpisodesApiService#getListEpisodes: {}", request);
		assert request != null;

		URIBuilder uriBuilder = getUriBuilder(EPISODES_PATH);
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		addIdsToUriBuilder(uriBuilder, request.getIds());
		URI uri = getUri(uriBuilder);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListEpisodesObject.class);
	}

	public EpisodeObject getEpisode(EpisodeRequest request) throws SpotifyGeneralApiException {
		log.trace("Call EpisodesApiService#getEpisode: {}", request);
		assert request != null;
		String id = request.getId();
		assert StringUtils.isNotEmpty(id);

		URIBuilder uriBuilder = getUriBuilder(EPISODES_PATH, id);
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		URI uri = getUri(uriBuilder);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(EpisodeObject.class);
	}
}
