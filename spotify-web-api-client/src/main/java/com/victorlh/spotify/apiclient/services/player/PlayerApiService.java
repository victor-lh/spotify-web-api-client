package com.victorlh.spotify.apiclient.services.player;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.enums.PlayableType;
import com.victorlh.spotify.apiclient.models.lists.ListDevicesObject;
import com.victorlh.spotify.apiclient.models.objects.CurrentlyPlayingContextObject;
import com.victorlh.spotify.apiclient.models.objects.CurrentlyPlayingObject;
import com.victorlh.spotify.apiclient.models.objects.PlayHistoryObject;
import com.victorlh.spotify.apiclient.models.pagination.CursorPagingObject;
import com.victorlh.spotify.apiclient.services.AbstractApiService;
import com.victorlh.spotify.apiclient.services.player.models.*;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
public class PlayerApiService extends AbstractApiService {

	private static final String ME_PATH = "me";
	private static final String PLAYER_PATH = "player";
	private static final String DEVICES_PATH = "devices";
	private static final String CURRENTLY_PLAYING_PATH = "currently-playing";
	private static final String PLAY_PATH = "play";
	private static final String PAUSE_PATH = "pause";
	private static final String NEXT_PATH = "next";
	private static final String PREVIOUS_PATH = "previous";
	private static final String SEEK_PATH = "seek";
	private static final String REPEAT_PATH = "repeat";
	private static final String VOLUME_PATH = "volume";
	private static final String SHUFFLE_PATH = "shuffle";
	private static final String RECENTLY_PLAYED_PATH = "recently-played";
	private static final String QUEUE_PATH = "queue";

	@Builder
	public PlayerApiService(SpotifyApiClient spotifyApiClient) {
		super(spotifyApiClient);
	}

	public CurrentlyPlayingContextObject getPlaybackInformation(GetPlaybackInformationRequest request) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#getPlaybackInformation: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH);
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		List<PlayableType> additionalTypes = request.getAdditionalTypes();
		addAdditionalTypes(uriBuilder, additionalTypes);
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(CurrentlyPlayingContextObject.class);
	}

	public ListDevicesObject getAvailableDevices() throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#getAvailableDevices");

		URI uri = getUri(ME_PATH, PLAYER_PATH, DEVICES_PATH);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListDevicesObject.class);
	}

	public void transferPlayback(TransferPlaybackRequest request) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#tranferPlayback: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URI uri = getUri(ME_PATH, PLAYER_PATH);
		doPut(uri, request);
	}

	public CurrentlyPlayingObject getCurrentlyPlayingTrack(GetCurrentlyPlayingTrackRequest request) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#getCurrentlyPlayingTrack: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH, CURRENTLY_PLAYING_PATH);
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		List<PlayableType> additionalTypes = request.getAdditionalTypes();
		addAdditionalTypes(uriBuilder, additionalTypes);
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(CurrentlyPlayingObject.class);
	}

	public void playPlayback(PlayPlaybackRequest request) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#playPlayback: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH, PLAY_PATH);
		List<String> deviceIds = request.getDeviceIds();
		if (deviceIds != null && !deviceIds.isEmpty()) {
			String join = String.join(",", deviceIds);
			addDeviceId(uriBuilder, join);
		}
		URI uri = getUri(uriBuilder);

		doPut(uri, request);
	}

	public void pausePlayback(String deviceId) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#pausePlayback: {}", deviceId);

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH, PAUSE_PATH);
		addDeviceId(uriBuilder, deviceId);
		URI uri = getUri(uriBuilder);
		doPut(uri, null);
	}

	public void nextTrackPlayback(String deviceId) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#nextTrackPlayback: {}", deviceId);

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH, NEXT_PATH);
		addDeviceId(uriBuilder, deviceId);
		URI uri = getUri(uriBuilder);
		doPost(uri, null);
	}

	public void previousTrackPlayback(String deviceId) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#previousTrackPlayback: {}", deviceId);

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH, PREVIOUS_PATH);
		addDeviceId(uriBuilder, deviceId);
		URI uri = getUri(uriBuilder);
		doPost(uri, null);
	}

	public void seekToPositionCurrentlyPlayingTrack(SeekPositionCurrentlyPlayingTrackRequest request) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#seekToPositionCurrentlyPlayingTrack: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH, SEEK_PATH);
		Long positionMs = request.getPositionMs();
		positionMs = positionMs != null ? positionMs : 0L;
		uriBuilder.addParameter("position_ms", Long.toString(positionMs));
		addDeviceId(uriBuilder, request.getDeviceId());
		URI uri = getUri(uriBuilder);
		doPut(uri, null);
	}

	public void setRepeatMode(SetRepeatModeRequest request) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#setRepeatMode: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH, REPEAT_PATH);
		uriBuilder.addParameter("state", request.getState().name());
		addDeviceId(uriBuilder, request.getDeviceId());
		URI uri = getUri(uriBuilder);
		doPut(uri, null);
	}

	public void setVolume(SetVolumeRequest request) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#setVolume: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH, VOLUME_PATH);
		uriBuilder.addParameter("volume_percent", String.valueOf(request.getVolumePercent()));
		addDeviceId(uriBuilder, request.getDeviceId());
		URI uri = getUri(uriBuilder);
		doPut(uri, null);
	}

	public void toggleShuffle(ToggleShuffleRequest request) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#toggleShuffle: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH, SHUFFLE_PATH);
		uriBuilder.addParameter("state", request.getShuffle().toString());
		addDeviceId(uriBuilder, request.getDeviceId());
		URI uri = getUri(uriBuilder);
		doPut(uri, null);
	}

	public CursorPagingObject<PlayHistoryObject> getRecentlyPlayedTrack(GetRecentlyPlayedTracksRequest request) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#getRecentlyPlayedTrack: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH, RECENTLY_PLAYED_PATH);
		addLimitToUriBuilder(uriBuilder, request.getLimit());
		Long after = request.getAfter();
		if (after != null) {
			uriBuilder.addParameter("after", String.valueOf(after));
		}
		Long before = request.getBefore();
		if (before != null) {
			uriBuilder.addParameter("before", String.valueOf(before));
		}
		URI uri = getUri(uriBuilder);
		HttpResponseWrapper response = doGet(uri);
		TypeReference<CursorPagingObject<PlayHistoryObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public CursorPagingObject<PlayHistoryObject> getRecentlyPlayedTrack(String paginationUrl) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#getRecentlyPlayedTrack: {}", paginationUrl);
		if (StringUtils.isEmpty(paginationUrl)) {
			throw new IllegalArgumentException();
		}

		URI uri = URI.create(paginationUrl);
		HttpResponseWrapper response = doGet(uri);
		TypeReference<CursorPagingObject<PlayHistoryObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public void addItemToQueue(AddItemToQueueRequest request) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#addItemToQueue: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH, QUEUE_PATH);
		addDeviceId(uriBuilder, request.getDeviceId());
		String itemUri = request.getUri();
		uriBuilder.addParameter("uri", itemUri);
		URI uri = getUri(uriBuilder);
		doPost(uri, null);
	}
}
