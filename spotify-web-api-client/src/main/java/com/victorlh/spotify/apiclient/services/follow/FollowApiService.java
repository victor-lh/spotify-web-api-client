package com.victorlh.spotify.apiclient.services.follow;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.pagination.ListArtistsCursorPagingObject;
import com.victorlh.spotify.apiclient.services.AbstractApiService;
import com.victorlh.spotify.apiclient.services.follow.models.*;
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
public class FollowApiService extends AbstractApiService {

	private static final String PLAYLIST_PATH = "playlists";
	private static final String FOLLOWERS_PATH = "followers";
	private static final String CONTAINS_PATH = "contains";
	private static final String FOLLOWING_PATH = "following";
	private static final String ME_PATH = "me";

	@Builder
	public FollowApiService(SpotifyApiClient spotifyApiClient) {
		super(spotifyApiClient);
	}

	public void followPlaylist(FollowPlaylistRequest request) throws SpotifyGeneralApiException {
		log.trace("Call FollowApiService#followPlaylist: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}
		String playlistId = request.getPlaylistId();
		if (StringUtils.isEmpty(playlistId)) {
			throw new IllegalArgumentException();
		}

		URI uri = getUri(PLAYLIST_PATH, playlistId, FOLLOWERS_PATH);
		Boolean publicPlaylist = request.getPublicPlaylist();
		HashMap<String, Object> body = new HashMap<>();
		if (publicPlaylist != null) {
			body.put("public", publicPlaylist);
		}
		doPut(uri, body);
	}

	public void unfollowPlaylist(String playlistId) throws SpotifyGeneralApiException {
		log.trace("Call FollowApiService#unfollowPlaylist: {}", playlistId);
		if (StringUtils.isEmpty(playlistId)) {
			throw new IllegalArgumentException();
		}

		URI uri = getUri(PLAYLIST_PATH, playlistId, FOLLOWERS_PATH);
		doDelete(uri);
	}

	public Map<String, Boolean> checkUsersFollowPlaylist(CheckUsersFollowPlaylistRequest request) throws SpotifyGeneralApiException {
		log.trace("Call FollowApiService#checkUsersFollowPlaylist: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}
		String playlistId = request.getPlaylistId();
		if (StringUtils.isEmpty(playlistId)) {
			throw new IllegalArgumentException();
		}

		List<String> ids = request.getIds();
		URIBuilder uriBuilder = getUriBuilder(PLAYLIST_PATH, playlistId, FOLLOWERS_PATH, CONTAINS_PATH);
		addIdsToUriBuilder(uriBuilder, ids);
		URI uri = getUri(uriBuilder);

		return getResponseMap(ids, uri);
	}

	public ListArtistsCursorPagingObject getUserFollowedArtists(UserFollowedArtistsRequest request) throws SpotifyGeneralApiException {
		log.trace("Call FollowApiService#getUserFollowedArtists: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, FOLLOWING_PATH);
		addLimitToUriBuilder(uriBuilder, request.getLimit());
		uriBuilder.addParameter("type", "artist");
		String after = request.getAfter();
		if (StringUtils.isNotBlank(after)) {
			uriBuilder.addParameter("after", after);
		}
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListArtistsCursorPagingObject.class);
	}

	public ListArtistsCursorPagingObject getUserFollowedArtists(String paginationUrl) throws SpotifyGeneralApiException {
		log.trace("Call FollowApiService#getUserFollowedArtists: {}", paginationUrl);
		if (StringUtils.isEmpty(paginationUrl)) {
			throw new IllegalArgumentException();
		}

		URI uri = URI.create(paginationUrl);

		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListArtistsCursorPagingObject.class);
	}

	public void followArtistsOrUsers(FollowArtistsOrUsersRequest request) throws SpotifyGeneralApiException {
		log.trace("Call FollowApiService#followArtistsOrUsers: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}
		FollowArtistsOrUsersRequest.FollowType type = request.getType();
		if(type == null) {
			throw new IllegalArgumentException();
		}

		List<String> ids = request.getIds();
		URIBuilder uriBuilder = getUriBuilder(ME_PATH, FOLLOWING_PATH);
		uriBuilder.addParameter("type", type.name());
		URI uri = getUri(uriBuilder);

		HashMap<String, Object> data = new HashMap<>();
		data.put("ids", ids);

		doPut(uri, data);
	}

	public void unfollowArtistsOrUsers(UnfollowArtistsOrUsersRequest request) throws SpotifyGeneralApiException {
		log.trace("Call FollowApiService#UnfollowArtistsOrUsersRequest: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}
		UnfollowArtistsOrUsersRequest.FollowType type = request.getType();
		if(type == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, FOLLOWING_PATH);
		addIdsToUriBuilder(uriBuilder, request.getIds());
		uriBuilder.addParameter("type", type.name());
		URI uri = getUri(uriBuilder);

		doDelete(uri);
	}

	public Map<String, Boolean> getFollowingStateArtistOrUsers(StatusFollowingArtistsOrUsersRequest request) throws SpotifyGeneralApiException {
		log.trace("Call FollowApiService#getFollowingStateArtistOrUsers: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		List<String> ids = request.getIds();
		StatusFollowingArtistsOrUsersRequest.FollowType type = request.getType();
		URIBuilder uriBuilder = getUriBuilder(ME_PATH, FOLLOWING_PATH, CONTAINS_PATH);
		addIdsToUriBuilder(uriBuilder, ids);
		uriBuilder.addParameter("type", type.name());
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
