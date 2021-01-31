package com.victorlh.spotify.apiclient.services.player.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class AddItemToQueueRequest {

	private final String uri;
	private final String deviceId;

}
