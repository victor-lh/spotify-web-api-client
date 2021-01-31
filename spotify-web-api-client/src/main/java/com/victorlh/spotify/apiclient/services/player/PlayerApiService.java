package com.victorlh.spotify.apiclient.services.player;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.enums.PlayableType;
import com.victorlh.spotify.apiclient.models.lists.ListDevicesObject;
import com.victorlh.spotify.apiclient.models.objects.CurrentlyPlayingContextObject;
import com.victorlh.spotify.apiclient.models.objects.CurrentlyPlayingObject;
import com.victorlh.spotify.apiclient.services.AbstractApiService;
import com.victorlh.spotify.apiclient.services.player.models.GetCurrentlyPlayingTrackRequest;
import com.victorlh.spotify.apiclient.services.player.models.GetPlaybackInformationRequest;
import com.victorlh.spotify.apiclient.services.player.models.PlayPlaybackRequest;
import com.victorlh.spotify.apiclient.services.player.models.TransferPlaybackRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
			uriBuilder.addParameter("device_id", join);
		}
		URI uri = getUri(uriBuilder);

		doPut(uri, request);
	}

	public void pausePlayback(String deviceId) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#pausePlayback: {}", deviceId);

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH, PAUSE_PATH);
		if (StringUtils.isNotEmpty(deviceId)) {
			uriBuilder.addParameter("device_id", deviceId);
		}
		URI uri = getUri(uriBuilder);
		doPut(uri, null);
	}

	public void nextTrackPlayback(String deviceId) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#nextTrackPlayback: {}", deviceId);

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH, NEXT_PATH);
		if (StringUtils.isNotEmpty(deviceId)) {
			uriBuilder.addParameter("device_id", deviceId);
		}
		URI uri = getUri(uriBuilder);
		doPost(uri, null);
	}

	public void previousTrackPlayback(String deviceId) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#previousTrackPlayback: {}", deviceId);

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH, PREVIOUS_PATH);
		if (StringUtils.isNotEmpty(deviceId)) {
			uriBuilder.addParameter("device_id", deviceId);
		}
		URI uri = getUri(uriBuilder);
		doPost(uri, null);
	}

	private void addAdditionalTypes(URIBuilder uriBuilder, List<PlayableType> additionalTypes) {
		if (additionalTypes != null && !additionalTypes.isEmpty()) {
			String types = additionalTypes.stream().map(Enum::name).collect(Collectors.joining(","));
			uriBuilder.addParameter("additional_types", types);
		}
	}
}
