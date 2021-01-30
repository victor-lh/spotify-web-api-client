package com.victorlh.spotify.apiclient.services.player.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class TransferPlaybackRequest {

	@JsonProperty("device_ids")
	@Singular
	private final List<String> deviceIds;
	private final Boolean play;
}
