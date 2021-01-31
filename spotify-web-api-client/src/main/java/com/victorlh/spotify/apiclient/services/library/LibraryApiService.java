package com.victorlh.spotify.apiclient.services.library;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.objects.SavedAlbumObject;
import com.victorlh.spotify.apiclient.models.objects.SavedShowObject;
import com.victorlh.spotify.apiclient.models.objects.SavedTrackObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.services.AbstractApiService;
import com.victorlh.spotify.apiclient.services.library.models.GetUserSavedAlbumsRequest;
import com.victorlh.spotify.apiclient.services.library.models.GetUserSavedShowsRequest;
import com.victorlh.spotify.apiclient.services.library.models.GetUserSavedTracksRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class LibraryApiService extends AbstractApiService {

	private static final String ME_PATH = "me";
	private static final String ALBUMS_PATH = "albums";
	private static final String TRACKS_PATH = "tracks";
	private static final String SHOWS_PATH = "shows";
	private static final String CONTAINS_PATH = "contains";

	@Builder
	public LibraryApiService(SpotifyApiClient spotifyApiClient) {
		super(spotifyApiClient);
	}

	public PagingObject<SavedAlbumObject> getUserSavedAlbums(GetUserSavedAlbumsRequest request) throws SpotifyGeneralApiException {
		log.trace("Call LibraryApiService#getUserSavedAlbums: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, ALBUMS_PATH);
		addLimitToUriBuilder(uriBuilder, request.getLimit());
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SavedAlbumObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public PagingObject<SavedAlbumObject> getUserSavedAlbums(String paginationUrl) throws SpotifyGeneralApiException {
		log.trace("Call LibraryApiService#getUserSavedAlbums: {}", paginationUrl);
		if (StringUtils.isEmpty(paginationUrl)) {
			throw new IllegalArgumentException();
		}

		URI uri = URI.create(paginationUrl);

		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SavedAlbumObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public void saveAlbumsCurrentUser(List<String> ids) throws SpotifyGeneralApiException {
		log.trace("Call LibraryApiService#saveAlbumsCurrentUser: {}", ids);
		if (ids == null) {
			throw new IllegalArgumentException();
		}

		URI uri = getUri(ME_PATH, ALBUMS_PATH);
		doPut(uri, ids);
	}

	public void removeAlbumsCurrentUser(List<String> ids) throws SpotifyGeneralApiException {
		log.trace("Call LibraryApiService#removeAlbumsCurrentUser: {}", ids);
		if (ids == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, ALBUMS_PATH);
		addIdsToUriBuilder(uriBuilder, ids);
		URI uri = getUri(uriBuilder);
		doDelete(uri);
	}

	public Map<String, Boolean> checkUserSavedAlbums(List<String> ids) throws SpotifyGeneralApiException {
		log.trace("Call LibraryApiService#checkUserSavedAlbums: {}", ids);
		if (ids == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, ALBUMS_PATH, CONTAINS_PATH);
		addIdsToUriBuilder(uriBuilder, ids);
		URI uri = getUri(uriBuilder);
		return getResponseMap(ids, uri);
	}

	public PagingObject<SavedTrackObject> getUserSavedTracks(GetUserSavedTracksRequest request) throws SpotifyGeneralApiException {
		log.trace("Call LibraryApiService#getUserSavedTracks: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, TRACKS_PATH);
		addLimitToUriBuilder(uriBuilder, request.getLimit());
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SavedTrackObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public PagingObject<SavedTrackObject> getUserSavedTracks(String paginationUrl) throws SpotifyGeneralApiException {
		log.trace("Call LibraryApiService#getUserSavedTracks: {}", paginationUrl);
		if (StringUtils.isEmpty(paginationUrl)) {
			throw new IllegalArgumentException();
		}

		URI uri = URI.create(paginationUrl);

		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SavedTrackObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public void saveTracksCurrentUser(List<String> ids) throws SpotifyGeneralApiException {
		log.trace("Call LibraryApiService#saveTracksCurrentUser: {}", ids);
		if (ids == null) {
			throw new IllegalArgumentException();
		}

		URI uri = getUri(ME_PATH, TRACKS_PATH);
		doPut(uri, ids);
	}

	public void removeTracksCurrentUser(List<String> ids) throws SpotifyGeneralApiException {
		log.trace("Call LibraryApiService#removeTracksCurrentUser: {}", ids);
		if (ids == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, TRACKS_PATH);
		addIdsToUriBuilder(uriBuilder, ids);
		URI uri = getUri(uriBuilder);
		doDelete(uri);
	}

	public Map<String, Boolean> checkUserSavedTracks(List<String> ids) throws SpotifyGeneralApiException {
		log.trace("Call LibraryApiService#checkUserSavedTracks: {}", ids);
		if (ids == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, TRACKS_PATH, CONTAINS_PATH);
		addIdsToUriBuilder(uriBuilder, ids);
		URI uri = getUri(uriBuilder);
		return getResponseMap(ids, uri);
	}

	public PagingObject<SavedShowObject> getUserSavedShows(GetUserSavedShowsRequest request) throws SpotifyGeneralApiException {
		log.trace("Call LibraryApiService#getUserSavedShows: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, SHOWS_PATH);
		addLimitToUriBuilder(uriBuilder, request.getLimit());
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SavedShowObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public PagingObject<SavedShowObject> getUserSavedShows(String paginationUrl) throws SpotifyGeneralApiException {
		log.trace("Call LibraryApiService#getUserSavedShows: {}", paginationUrl);
		if (StringUtils.isEmpty(paginationUrl)) {
			throw new IllegalArgumentException();
		}

		URI uri = URI.create(paginationUrl);

		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SavedShowObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public void saveShowsCurrentUser(List<String> ids) throws SpotifyGeneralApiException {
		log.trace("Call LibraryApiService#saveShowsCurrentUser: {}", ids);
		if (ids == null) {
			throw new IllegalArgumentException();
		}

		URI uri = getUri(ME_PATH, SHOWS_PATH);
		doPut(uri, ids);
	}

	public void removeShowsCurrentUser(List<String> ids) throws SpotifyGeneralApiException {
		log.trace("Call LibraryApiService#removeShowsCurrentUser: {}", ids);
		if (ids == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, SHOWS_PATH);
		addIdsToUriBuilder(uriBuilder, ids);
		URI uri = getUri(uriBuilder);
		doDelete(uri);
	}

	public Map<String, Boolean> checkUserSavedShows(List<String> ids) throws SpotifyGeneralApiException {
		log.trace("Call LibraryApiService#checkUserSavedShows: {}", ids);
		if (ids == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, SHOWS_PATH, CONTAINS_PATH);
		addIdsToUriBuilder(uriBuilder, ids);
		URI uri = getUri(uriBuilder);
		return getResponseMap(ids, uri);
	}

	private Map<String, Boolean> getResponseMap(List<String> ids, URI uri) throws SpotifyGeneralApiException {
		HttpResponseWrapper response = doGet(uri);
		Boolean[] dataResult = response.parseResponse(Boolean[].class);
		HashMap<String, Boolean> result = new HashMap<>();
		for (int i = 0; i < ids.size(); i++) {
			result.put(ids.get(i), dataResult[i]);
		}
		return Collections.unmodifiableMap(result);
	}

}
