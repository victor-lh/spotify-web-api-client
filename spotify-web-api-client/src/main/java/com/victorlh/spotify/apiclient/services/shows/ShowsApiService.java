package com.victorlh.spotify.apiclient.services.shows;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.ListSimplifiedShowObject;
import com.victorlh.spotify.apiclient.models.PagingObject;
import com.victorlh.spotify.apiclient.models.ShowObject;
import com.victorlh.spotify.apiclient.models.SimplifiedEpisodeObject;
import com.victorlh.spotify.apiclient.services.AbstractApiService;
import com.victorlh.spotify.apiclient.services.shows.models.MultipleShowsRequest;
import com.victorlh.spotify.apiclient.services.shows.models.ShowEpisodesRequest;
import com.victorlh.spotify.apiclient.services.shows.models.ShowRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;

@Slf4j
public class ShowsApiService extends AbstractApiService {

	private static final String SHOWS_PATH = "shows";
	private static final String EPISODES_PATH = "episodes";

	@Builder
	public ShowsApiService(SpotifyApiClient spotifyApiClient) {
		super(spotifyApiClient);
	}


	public ListSimplifiedShowObject getMultipleShows(MultipleShowsRequest request) throws SpotifyGeneralApiException {
		log.trace("Call ShowsApiService#getListShows: {}", request);
		assert request != null;

		URIBuilder uriBuilder = getUriBuilder(SHOWS_PATH);
		addIdsToUriBuilder(uriBuilder, request.getIds());
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListSimplifiedShowObject.class);
	}

	public ShowObject getShow(ShowRequest request) throws SpotifyGeneralApiException {
		log.trace("Call ShowsApiService#getShow: {}", request);
		assert request != null;
		String id = request.getId();
		assert StringUtils.isNotEmpty(id);

		URIBuilder uriBuilder = getUriBuilder(SHOWS_PATH, id);
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ShowObject.class);
	}

	public PagingObject<SimplifiedEpisodeObject> getShowEpisodes(ShowEpisodesRequest request) throws SpotifyGeneralApiException {
		log.trace("Call ShowsApiService#getShowEpisodes: {}", request);
		assert request != null;
		String id = request.getId();
		assert StringUtils.isNotEmpty(id);

		URIBuilder uriBuilder = getUriBuilder(SHOWS_PATH, id, EPISODES_PATH);
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SimplifiedEpisodeObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public PagingObject<SimplifiedEpisodeObject> getShowEpisodes(String paginationUrl) throws SpotifyGeneralApiException {
		log.trace("Call ShowsApiService#getShowEpisodes: {}", paginationUrl);
		assert StringUtils.isNotEmpty(paginationUrl);

		URI uri = URI.create(paginationUrl);

		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SimplifiedEpisodeObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}
}
