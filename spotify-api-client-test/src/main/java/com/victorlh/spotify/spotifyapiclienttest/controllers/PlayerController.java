package com.victorlh.spotify.spotifyapiclienttest.controllers;

import com.neovisionaries.i18n.CountryCode;
import com.victorlh.spotify.apiclient.models.enums.PlayableType;
import com.victorlh.spotify.apiclient.models.enums.RepeatState;
import com.victorlh.spotify.apiclient.models.lists.ListDevicesObject;
import com.victorlh.spotify.apiclient.models.objects.CurrentlyPlayingContextObject;
import com.victorlh.spotify.apiclient.models.objects.CurrentlyPlayingObject;
import com.victorlh.spotify.apiclient.models.objects.PlayHistoryObject;
import com.victorlh.spotify.apiclient.models.pagination.CursorPagingObject;
import com.victorlh.spotify.apiclient.services.player.models.*;
import com.victorlh.spotify.spotifyapiclienttest.dto.PlayPlaybackRequestDTO;
import com.victorlh.spotify.spotifyapiclienttest.services.PlayerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PlayerController {

	private final PlayerService playerService;

	@Autowired
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}

	@GetMapping("/me/player")
	CurrentlyPlayingContextObject getPlaybackInformation(@RequestParam(value = "market", required = false) String market,
	                                                     @RequestParam(value = "additional_types", required = false) List<String> playableTypes) {
		GetPlaybackInformationRequest.GetPlaybackInformationRequestBuilder builder = GetPlaybackInformationRequest.builder().market(market == null ? null : CountryCode.getByAlpha2Code(market));
		if (playableTypes != null) {
			builder = builder.additionalTypes(playableTypes.stream().map(PlayableType::valueOf).collect(Collectors.toList()));
		}
		GetPlaybackInformationRequest request = builder.build();
		return playerService.getPlaybackInformation(request);
	}

	@PutMapping("/me/player")
	void transferPlayback(@RequestParam(value = "deviceIds") List<String> deviceId, @RequestParam(value = "play", required = false) Boolean play) {
		TransferPlaybackRequest request = TransferPlaybackRequest.builder().deviceIds(deviceId).play(play).build();
		playerService.transferPlayback(request);
	}

	@GetMapping("/me/player/devices")
	ListDevicesObject getAvailableDevices() {
		return playerService.getAvailableDevices();
	}

	@GetMapping("/me/player/currently-playing")
	CurrentlyPlayingObject getCurrentlyPlayingTrack(@RequestParam(value = "market", required = false) String market,
	                                                @RequestParam(value = "additional_types", required = false) List<String> playableTypes) {
		GetCurrentlyPlayingTrackRequest.GetCurrentlyPlayingTrackRequestBuilder builder = GetCurrentlyPlayingTrackRequest.builder().market(market == null ? null : CountryCode.getByAlpha2Code(market));
		if (playableTypes != null) {
			builder = builder.additionalTypes(playableTypes.stream().map(PlayableType::valueOf).collect(Collectors.toList()));
		}
		GetCurrentlyPlayingTrackRequest request = builder.build();
		return playerService.getCurrentlyPlayingTrack(request);
	}

	@PutMapping("/me/player/play")
	void playPlayback(@RequestBody PlayPlaybackRequestDTO dto) {
		PlayPlaybackRequest.Offset offset = null;
		if (dto.getOffset() != null) {
			offset = PlayPlaybackRequest.Offset.builder().position(dto.getOffset().getPosition()).uri(dto.getOffset().getUri()).build();
		}

		PlayPlaybackRequest.PlayPlaybackRequestBuilder builder = PlayPlaybackRequest.builder()
				.contextUri(dto.getContextUri())
				.offset(offset)
				.positionMs(dto.getPositionMs());
		List<String> uris = dto.getUris();
		if (uris != null && !uris.isEmpty()) {
			builder = builder.uris(uris);
		}
		List<String> deviceIds = dto.getDeviceIds();
		if (deviceIds != null && !deviceIds.isEmpty()) {
			builder = builder.deviceIds(deviceIds);
		}
		PlayPlaybackRequest request = builder.build();
		playerService.playPlayback(request);
	}

	@PutMapping("/me/player/pause")
	void pausePlayback(@RequestParam(value = "deviceId", required = false) String deviceId) {
		playerService.pausePlayback(deviceId);
	}

	@PostMapping("/me/player/next")
	void nextTrackPlayback(@RequestParam(value = "deviceId", required = false) String deviceId) {
		playerService.nextTrackPlayback(deviceId);
	}

	@PostMapping("/me/player/previous")
	void previousTrackPlayback(@RequestParam(value = "deviceId", required = false) String deviceId) {
		playerService.previousTrackPlayback(deviceId);
	}

	@PutMapping("/me/player/seek")
	void seekToPositionCurrentlyPlayingTrack(@RequestParam(value = "position", required = false) Long position,
	                                         @RequestParam(value = "deviceId", required = false) String deviceId) {
		SeekPositionCurrentlyPlayingTrackRequest request = SeekPositionCurrentlyPlayingTrackRequest.builder().positionMs(position).deviceId(deviceId).build();
		playerService.seekToPositionCurrentlyPlayingTrack(request);
	}

	@PutMapping("/me/player/repeat")
	void setRepeatMode(@RequestParam(value = "state", required = false) RepeatState state,
	                   @RequestParam(value = "deviceId", required = false) String deviceId) {
		SetRepeatModeRequest request = SetRepeatModeRequest.builder().state(state).deviceId(deviceId).build();
		playerService.setRepeatMode(request);
	}

	@PutMapping("/me/player/volume")
	void setVolume(@RequestParam(value = "volume", required = false) Integer volumePercent,
	               @RequestParam(value = "deviceId", required = false) String deviceId) {
		SetVolumeRequest request = SetVolumeRequest.builder().volumePercent(volumePercent).deviceId(deviceId).build();
		playerService.setVolume(request);
	}

	@PutMapping("/me/player/shuffle")
	void setToggleShuffle(@RequestParam(value = "state", required = false) Boolean state,
	                      @RequestParam(value = "deviceId", required = false) String deviceId) {
		ToggleShuffleRequest request = ToggleShuffleRequest.builder().shuffle(state).deviceId(deviceId).build();
		playerService.toggleShuffle(request);
	}

	@GetMapping("/me/player/recently-played")
	CursorPagingObject<PlayHistoryObject> setToggleShuffle(@RequestParam(value = "next", required = false) String next,
	                                                       @RequestParam(value = "limit", required = false) Integer limit,
	                                                       @RequestParam(value = "after", required = false) Long after,
	                                                       @RequestParam(value = "before", required = false) Long before) {
		if (StringUtils.isNotEmpty(next)) {
			return playerService.getRecentlyPlayedTrack(next);
		}
		GetRecentlyPlayedTracksRequest request = GetRecentlyPlayedTracksRequest.builder().limit(limit).after(after).before(before).build();
		return playerService.getRecentlyPlayedTrack(request);
	}

	@PostMapping("/me/player/queue")
	void addItemToQueue(@RequestParam(value = "uri", required = false) String uri,
	                    @RequestParam(value = "deviceId", required = false) String deviceId) {
		AddItemToQueueRequest request = AddItemToQueueRequest.builder().uri(uri).deviceId(deviceId).build();
		playerService.addItemToQueue(request);
	}
}
