package com.victorlh.spotify.apiclient.services.playlist;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.objects.ImageObject;
import com.victorlh.spotify.apiclient.models.objects.PlaylistObject;
import com.victorlh.spotify.apiclient.models.objects.PlaylistTrackObject;
import com.victorlh.spotify.apiclient.models.objects.SimplifiedPlaylistObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.services.AbstractApiService;
import com.victorlh.spotify.apiclient.services.playlist.models.GetPlaylistItemsRequest;
import com.victorlh.spotify.apiclient.services.playlist.models.GetPlaylistRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
public class PlaylistApiService extends AbstractApiService {

	private static final String ME_PATH = "me";
	private static final String PLAYLISTS_PATH = "playlists";
	private static final String USERS_PATH = "users";
	private static final String TRACKS_PATH = "tracks";
	private static final String IMAGES_PATH = "images";

	@Builder
	public PlaylistApiService(SpotifyApiClient spotifyApiClient) {
		super(spotifyApiClient);
	}

	public PagingObject<SimplifiedPlaylistObject> getCurrentUserPlaylist() throws SpotifyGeneralApiException {
		return getCurrentUserPlaylist(null);
	}

	public PagingObject<SimplifiedPlaylistObject> getCurrentUserPlaylist(Integer limit) throws SpotifyGeneralApiException {
		log.trace("Call PlaylistApiService#getCurrentUserPlaylist: limit[{}]", limit);

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYLISTS_PATH);
		addLimitToUriBuilder(uriBuilder, limit);
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SimplifiedPlaylistObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public PagingObject<SimplifiedPlaylistObject> getUserPlaylist(String userId) throws SpotifyGeneralApiException {
		return getUserPlaylist(userId, null);
	}

	public PagingObject<SimplifiedPlaylistObject> getUserPlaylist(String userId, Integer limit) throws SpotifyGeneralApiException {
		log.trace("Call PlaylistApiService#getUserPlaylist: userId[{}] - limit[{}]", userId, limit);
		if (StringUtils.isEmpty(userId)) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(USERS_PATH, userId, PLAYLISTS_PATH);
		addLimitToUriBuilder(uriBuilder, limit);
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SimplifiedPlaylistObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public PagingObject<SimplifiedPlaylistObject> getPlaylistPagination(String paginationUrl) throws SpotifyGeneralApiException {
		log.trace("Call PlaylistApiService#getPlaylistPagination: {}", paginationUrl);
		if (StringUtils.isEmpty(paginationUrl)) {
			throw new IllegalArgumentException();
		}

		URI uri = URI.create(paginationUrl);

		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SimplifiedPlaylistObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public PlaylistObject getPlaylist(GetPlaylistRequest request) throws SpotifyGeneralApiException {
		log.trace("Call PlaylistApiService#getPlaylist: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}
		String playlistId = request.getPlaylistId();
		if (StringUtils.isEmpty(playlistId)) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(PLAYLISTS_PATH, playlistId);
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		addAdditionalTypes(uriBuilder, request.getAdditionalTypes());
		String fields = request.getFields();
		if (StringUtils.isNotEmpty(fields)) {
			uriBuilder.addParameter("fields", fields);
		}
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(PlaylistObject.class);
	}

	public PagingObject<PlaylistTrackObject> getPlaylistItems(GetPlaylistItemsRequest request) throws SpotifyGeneralApiException {
		log.trace("Call PlaylistApiService#getPlaylistItems: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}
		String playlistId = request.getPlaylistId();
		if (StringUtils.isEmpty(playlistId)) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(PLAYLISTS_PATH, playlistId, TRACKS_PATH);
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		addAdditionalTypes(uriBuilder, request.getAdditionalTypes());
		addLimitToUriBuilder(uriBuilder, request.getLimit(), 100);
		String fields = request.getFields();
		if (StringUtils.isNotEmpty(fields)) {
			uriBuilder.addParameter("fields", fields);
		}
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<PlaylistTrackObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public PagingObject<PlaylistTrackObject> getPlaylistItems(String paginationUrl) throws SpotifyGeneralApiException {
		log.trace("Call PlaylistApiService#getPlaylistItems: {}", paginationUrl);
		if (StringUtils.isEmpty(paginationUrl)) {
			throw new IllegalArgumentException();
		}

		URI uri = URI.create(paginationUrl);

		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<PlaylistTrackObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public List<ImageObject> getPlaylistImages(String playlistId) throws SpotifyGeneralApiException {
		log.trace("Call PlaylistApiService#getPlaylistImages: {}", playlistId);
		if (StringUtils.isEmpty(playlistId)) {
			throw new IllegalArgumentException();
		}

		URI uri = getUri(PLAYLISTS_PATH, playlistId, IMAGES_PATH);
		HttpResponseWrapper response = doGet(uri);
		TypeReference<List<ImageObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}
}
