package com.victorlh.spotify.apiclient.services.tracks;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.objects.AudioFeaturesObject;
import com.victorlh.spotify.apiclient.models.lists.ListAudiosFeaturesObject;
import com.victorlh.spotify.apiclient.models.lists.ListTracksObject;
import com.victorlh.spotify.apiclient.models.objects.TrackObject;
import com.victorlh.spotify.apiclient.services.AbstractApiService;
import com.victorlh.spotify.apiclient.services.tracks.models.AudioFeaturesMultipleTracksRequest;
import com.victorlh.spotify.apiclient.services.tracks.models.AudioFeaturesTrackRequest;
import com.victorlh.spotify.apiclient.services.tracks.models.MultipleTracksRequest;
import com.victorlh.spotify.apiclient.services.tracks.models.TrackRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;

@Slf4j
public class TracksApiService extends AbstractApiService {

	private static final String TRACKS_PATH = "tracks";
	private static final String AUDIO_FEATURES_PATH = "audio-features";
	private static final String AUDIO_ANALYSIS_PATH = "audio-analysis";

	@Builder
	public TracksApiService(SpotifyApiClient spotifyApiClient) {
		super(spotifyApiClient);
	}

	public ListTracksObject getMultipleTracks(MultipleTracksRequest request) throws SpotifyGeneralApiException {
		log.trace("Call TracksApiService#getMultipleTracks: {}", request);
		assert request != null;

		URIBuilder uriBuilder = getUriBuilder(TRACKS_PATH);
		addIdsToUriBuilder(uriBuilder, request.getIds());
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListTracksObject.class);
	}

	public TrackObject getTrack(TrackRequest request) throws SpotifyGeneralApiException {
		log.trace("Call TracksApiService#getTrack: {}", request);
		assert request != null;
		String id = request.getId();
		assert StringUtils.isNotEmpty(id);

		URIBuilder uriBuilder = getUriBuilder(TRACKS_PATH, id);
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(TrackObject.class);
	}

	public ListAudiosFeaturesObject getAudioFeaturesMultipleTracks(AudioFeaturesMultipleTracksRequest request) throws SpotifyGeneralApiException {
		log.trace("Call TracksApiService#getAudioFeaturesMultipleTracks: {}", request);
		assert request != null;

		URIBuilder uriBuilder = getUriBuilder(AUDIO_FEATURES_PATH);
		addIdsToUriBuilder(uriBuilder, request.getIds(), 100);
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListAudiosFeaturesObject.class);
	}

	public AudioFeaturesObject getAudioFeaturesTrack(AudioFeaturesTrackRequest request) throws SpotifyGeneralApiException {
		log.trace("Call TracksApiService#getAudioFeaturesTrack: {}", request);
		assert request != null;
		String id = request.getId();
		assert StringUtils.isNotEmpty(id);

		URI uri = getUri(AUDIO_FEATURES_PATH, id);

		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(AudioFeaturesObject.class);
	}

	private Object getAudioAnalysisTrack(String id) throws SpotifyGeneralApiException {
		//TODO - Implementar model de respuesta
		log.trace("Call TracksApiService#getAudioAnalysisTrack: {}", id);
		assert StringUtils.isNotEmpty(id);

		URI uri = getUri(AUDIO_ANALYSIS_PATH, id);
		HttpResponseWrapper response = doGet(uri);
		return response.responseBodyString();
	}
}
