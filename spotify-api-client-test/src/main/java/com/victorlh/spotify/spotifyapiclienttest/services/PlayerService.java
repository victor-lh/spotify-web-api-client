package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.lists.ListDevicesObject;
import com.victorlh.spotify.apiclient.models.objects.CurrentlyPlayingContextObject;
import com.victorlh.spotify.apiclient.models.objects.CurrentlyPlayingObject;
import com.victorlh.spotify.apiclient.models.objects.PlayHistoryObject;
import com.victorlh.spotify.apiclient.models.pagination.CursorPagingObject;
import com.victorlh.spotify.apiclient.services.player.models.*;

public interface PlayerService {

	CurrentlyPlayingContextObject getPlaybackInformation(GetPlaybackInformationRequest request);

	ListDevicesObject getAvailableDevices();

	void transferPlayback(TransferPlaybackRequest request);

	CurrentlyPlayingObject getCurrentlyPlayingTrack(GetCurrentlyPlayingTrackRequest request);

	void playPlayback(PlayPlaybackRequest request);

	void pausePlayback(String deviceId);

	void nextTrackPlayback(String deviceId);

	void previousTrackPlayback(String deviceId);

	void seekToPositionCurrentlyPlayingTrack(SeekPositionCurrentlyPlayingTrackRequest request);

	void setRepeatMode(SetRepeatModeRequest request);

	void setVolume(SetVolumeRequest request);

	void toggleShuffle(ToggleShuffleRequest request);

	CursorPagingObject<PlayHistoryObject> getRecentlyPlayedTrack(GetRecentlyPlayedTracksRequest request);

	CursorPagingObject<PlayHistoryObject> getRecentlyPlayedTrack(String paginationUrl);

	void addItemToQueue(AddItemToQueueRequest request);
}
