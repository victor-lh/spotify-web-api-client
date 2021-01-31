package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.models.lists.ListDevicesObject;
import com.victorlh.spotify.apiclient.models.objects.CurrentlyPlayingContextObject;
import com.victorlh.spotify.apiclient.models.objects.CurrentlyPlayingObject;
import com.victorlh.spotify.apiclient.models.objects.PlayHistoryObject;
import com.victorlh.spotify.apiclient.models.pagination.CursorPagingObject;
import com.victorlh.spotify.apiclient.services.player.PlayerApiService;
import com.victorlh.spotify.apiclient.services.player.models.*;
import com.victorlh.spotify.spotifyapiclienttest.exceptions.SpotifyApiExceptionRuntime;
import com.victorlh.spotify.spotifyapiclienttest.services.PlayerService;
import com.victorlh.spotify.spotifyapiclienttest.services.SpotifyClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

	private final SpotifyClientService spotifyClientService;

	@Autowired
	public PlayerServiceImpl(SpotifyClientService spotifyClientService) {
		this.spotifyClientService = spotifyClientService;
	}

	@Override
	public CurrentlyPlayingContextObject getPlaybackInformation(GetPlaybackInformationRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlayerApiService playerApiService = spotifyApiClient.getPlayerApiService();
		try {
			return playerApiService.getPlaybackInformation(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public ListDevicesObject getAvailableDevices() {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlayerApiService playerApiService = spotifyApiClient.getPlayerApiService();
		try {
			return playerApiService.getAvailableDevices();
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void transferPlayback(TransferPlaybackRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlayerApiService playerApiService = spotifyApiClient.getPlayerApiService();
		try {
			playerApiService.transferPlayback(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public CurrentlyPlayingObject getCurrentlyPlayingTrack(GetCurrentlyPlayingTrackRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlayerApiService playerApiService = spotifyApiClient.getPlayerApiService();
		try {
			return playerApiService.getCurrentlyPlayingTrack(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void playPlayback(PlayPlaybackRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlayerApiService playerApiService = spotifyApiClient.getPlayerApiService();
		try {
			playerApiService.playPlayback(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void pausePlayback(String deviceId) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlayerApiService playerApiService = spotifyApiClient.getPlayerApiService();
		try {
			playerApiService.pausePlayback(deviceId);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void nextTrackPlayback(String deviceId) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlayerApiService playerApiService = spotifyApiClient.getPlayerApiService();
		try {
			playerApiService.nextTrackPlayback(deviceId);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void previousTrackPlayback(String deviceId) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlayerApiService playerApiService = spotifyApiClient.getPlayerApiService();
		try {
			playerApiService.previousTrackPlayback(deviceId);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void seekToPositionCurrentlyPlayingTrack(SeekPositionCurrentlyPlayingTrackRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlayerApiService playerApiService = spotifyApiClient.getPlayerApiService();
		try {
			playerApiService.seekToPositionCurrentlyPlayingTrack(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void setRepeatMode(SetRepeatModeRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlayerApiService playerApiService = spotifyApiClient.getPlayerApiService();
		try {
			playerApiService.setRepeatMode(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void setVolume(SetVolumeRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlayerApiService playerApiService = spotifyApiClient.getPlayerApiService();
		try {
			playerApiService.setVolume(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void toggleShuffle(ToggleShuffleRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlayerApiService playerApiService = spotifyApiClient.getPlayerApiService();
		try {
			playerApiService.toggleShuffle(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public CursorPagingObject<PlayHistoryObject> getRecentlyPlayedTrack(GetRecentlyPlayedTracksRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlayerApiService playerApiService = spotifyApiClient.getPlayerApiService();
		try {
			return playerApiService.getRecentlyPlayedTrack(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public CursorPagingObject<PlayHistoryObject> getRecentlyPlayedTrack(String paginationUrl) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlayerApiService playerApiService = spotifyApiClient.getPlayerApiService();
		try {
			return playerApiService.getRecentlyPlayedTrack(paginationUrl);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void addItemToQueue(AddItemToQueueRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		PlayerApiService playerApiService = spotifyApiClient.getPlayerApiService();
		try {
			playerApiService.addItemToQueue(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}
}
