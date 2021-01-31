package com.victorlh.spotify.apiclient.services.player.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SeekPositionCurrentlyPlayingTrackRequest {

	private final String deviceId;
	private final Long positionMs;
}
