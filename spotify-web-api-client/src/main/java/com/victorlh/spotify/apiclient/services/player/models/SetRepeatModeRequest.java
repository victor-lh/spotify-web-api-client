package com.victorlh.spotify.apiclient.services.player.models;

import com.victorlh.spotify.apiclient.models.enums.RepeatState;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SetRepeatModeRequest {

	private final String deviceId;
	private final RepeatState state;
}
