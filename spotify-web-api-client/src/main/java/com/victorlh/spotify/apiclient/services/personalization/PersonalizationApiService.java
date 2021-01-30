package com.victorlh.spotify.apiclient.services.personalization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.objects.ArtistObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.models.objects.TrackObject;
import com.victorlh.spotify.apiclient.services.AbstractApiService;
import com.victorlh.spotify.apiclient.services.personalization.models.GetUserTopRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;

@Slf4j
public class PersonalizationApiService extends AbstractApiService {

	private static final String ME_PATH = "me";
	private static final String TOP_PATH = "top";
	private static final String ARTIS_PATH = "artists";
	private static final String TRACKS_PATH = "tracks";

	@Builder
	public PersonalizationApiService(SpotifyApiClient spotifyApiClient) {
		super(spotifyApiClient);
	}

	public PagingObject<TrackObject> getUserTopTracks(GetUserTopRequest request) throws SpotifyGeneralApiException {
		log.trace("Call PersonalizationApiService#getUserTopTracks: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URI uri = getURI(TRACKS_PATH, request);
		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<TrackObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public PagingObject<TrackObject> getUserTopTracks(String paginationUri) throws SpotifyGeneralApiException {
		log.trace("Call PersonalizationApiService#getUserTopTracks: {}", paginationUri);
		if(StringUtils.isEmpty(paginationUri)) {
			throw new IllegalArgumentException();
		}

		URI uri = URI.create(paginationUri);
		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<TrackObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public PagingObject<ArtistObject> getUserTopArtist(GetUserTopRequest request) throws SpotifyGeneralApiException {
		log.trace("Call PersonalizationApiService#getUserTopArtist: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URI uri = getURI(ARTIS_PATH, request);
		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<ArtistObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public PagingObject<ArtistObject> getUserTopArtist(String paginationUri) throws SpotifyGeneralApiException {
		log.trace("Call PersonalizationApiService#getUserTopArtist: {}", paginationUri);
		if (StringUtils.isEmpty(paginationUri)) {
			throw new IllegalArgumentException();
		}

		URI uri = URI.create(paginationUri);
		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<ArtistObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	private URI getURI(String path, GetUserTopRequest request) {
		URIBuilder uriBuilder = getUriBuilder(ME_PATH, TOP_PATH, path);
		addLimitToUriBuilder(uriBuilder, request.getLimit());
		String timeRange = request.getTimeRange();
		if (StringUtils.isNotEmpty(timeRange)) {
			uriBuilder.addParameter("time_range", timeRange);
		}

		return getUri(uriBuilder);
	}

}
