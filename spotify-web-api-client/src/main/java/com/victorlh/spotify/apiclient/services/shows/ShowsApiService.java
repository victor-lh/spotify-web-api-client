package com.victorlh.spotify.apiclient.services.shows;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.lists.ListSimplifiedShowsObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.models.objects.ShowObject;
import com.victorlh.spotify.apiclient.models.objects.SimplifiedEpisodeObject;
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


	public ListSimplifiedShowsObject getMultipleShows(MultipleShowsRequest request) throws SpotifyGeneralApiException {
		log.trace("Call ShowsApiService#getListShows: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(SHOWS_PATH);
		addIdsToUriBuilder(uriBuilder, request.getIds());
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListSimplifiedShowsObject.class);
	}

	public ShowObject getShow(ShowRequest request) throws SpotifyGeneralApiException {
		log.trace("Call ShowsApiService#getShow: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}
		String id = request.getId();
		if (StringUtils.isEmpty(id)) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(SHOWS_PATH, id);
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ShowObject.class);
	}

	public PagingObject<SimplifiedEpisodeObject> getShowEpisodes(ShowEpisodesRequest request) throws SpotifyGeneralApiException {
		log.trace("Call ShowsApiService#getShowEpisodes: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}
		String id = request.getId();
		if (StringUtils.isEmpty(id)) {
			throw new IllegalArgumentException();
		}

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
		if (StringUtils.isEmpty(paginationUrl)) {
			throw new IllegalArgumentException();
		}

		URI uri = URI.create(paginationUrl);

		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SimplifiedEpisodeObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}
}
