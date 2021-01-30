package com.victorlh.spotify.apiclient.services.player;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.enums.PlayableType;
import com.victorlh.spotify.apiclient.models.lists.ListDevicesObject;
import com.victorlh.spotify.apiclient.models.objects.CurrentlyPlayingContextObject;
import com.victorlh.spotify.apiclient.services.AbstractApiService;
import com.victorlh.spotify.apiclient.services.player.models.GetPlaybackInformationRequest;
import com.victorlh.spotify.apiclient.services.player.models.TransferPlaybackRequest;
import lombok.Builder;
import lombok.SneakyThrows;
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

	@Builder
	public PlayerApiService(SpotifyApiClient spotifyApiClient) {
		super(spotifyApiClient);
	}

	public CurrentlyPlayingContextObject getPlaybackInformation(GetPlaybackInformationRequest request) throws SpotifyGeneralApiException {
		log.trace("Call PlayerApiService#getPlaybackInformation: {}", request);

		URIBuilder uriBuilder = getUriBuilder(ME_PATH, PLAYER_PATH);
		if (request != null) {
			addMarketToUriBuilder(uriBuilder, request.getMarket());
			List<PlayableType> additionalTypes = request.getAdditionalTypes();
			if (additionalTypes != null && !additionalTypes.isEmpty()) {
				String types = additionalTypes.stream().map(Enum::name).collect(Collectors.joining(","));
				uriBuilder.addParameter("additional_types", types);
			}
		}
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
		if(request == null) {
			throw new IllegalArgumentException();
		}

		URI uri = getUri(ME_PATH, PLAYER_PATH);
		doPut(uri, request);
	}
}
