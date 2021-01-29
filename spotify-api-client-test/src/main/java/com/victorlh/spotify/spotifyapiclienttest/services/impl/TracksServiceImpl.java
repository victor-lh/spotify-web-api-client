package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.exceptions.SpotifyWebApiClientException;
import com.victorlh.spotify.apiclient.models.objects.AudioFeaturesObject;
import com.victorlh.spotify.apiclient.models.lists.ListAudiosFeaturesObject;
import com.victorlh.spotify.apiclient.models.lists.ListTracksObject;
import com.victorlh.spotify.apiclient.models.objects.TrackObject;
import com.victorlh.spotify.apiclient.services.tracks.TracksApiService;
import com.victorlh.spotify.apiclient.services.tracks.models.AudioFeaturesMultipleTracksRequest;
import com.victorlh.spotify.apiclient.services.tracks.models.AudioFeaturesTrackRequest;
import com.victorlh.spotify.apiclient.services.tracks.models.MultipleTracksRequest;
import com.victorlh.spotify.apiclient.services.tracks.models.TrackRequest;
import com.victorlh.spotify.spotifyapiclienttest.services.SpotifyClientService;
import com.victorlh.spotify.spotifyapiclienttest.services.TracksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TracksServiceImpl implements TracksService {

	private final SpotifyClientService spotifyClientService;

	@Autowired
	public TracksServiceImpl(SpotifyClientService spotifyClientService) {
		this.spotifyClientService = spotifyClientService;
	}

	@Override
	public ListTracksObject getMultipleTracks(MultipleTracksRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		TracksApiService tracksApiService = spotifyApiClient.getTracksApiService();
		try {
			return tracksApiService.getMultipleTracks(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyWebApiClientException(e);
		}
	}

	@Override
	public TrackObject getTrack(TrackRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		TracksApiService tracksApiService = spotifyApiClient.getTracksApiService();
		try {
			return tracksApiService.getTrack(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyWebApiClientException(e);
		}
	}

	@Override
	public ListAudiosFeaturesObject getAudioFeaturesMultipleTracks(AudioFeaturesMultipleTracksRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		TracksApiService tracksApiService = spotifyApiClient.getTracksApiService();
		try {
			return tracksApiService.getAudioFeaturesMultipleTracks(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyWebApiClientException(e);
		}
	}

	@Override
	public AudioFeaturesObject getAudioFeaturesTrack(AudioFeaturesTrackRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		TracksApiService tracksApiService = spotifyApiClient.getTracksApiService();
		try {
			return tracksApiService.getAudioFeaturesTrack(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyWebApiClientException(e);
		}
	}
}
