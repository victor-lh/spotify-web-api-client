package com.victorlh.spotify.apiclient.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TrackRestrictionObject {

	MARKET("market"),
	PRODUCT("product"),
	EXPLICIT("explicit");

	public final String reason;
}
