package com.victorlh.spotify.apiclient.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TrackMode {

	MAYOR(1), MENOR(0);

	@JsonValue
	private final int value;
}
