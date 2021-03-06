package com.victorlh.spotify.apiclient.models.objects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AlbumRestrictionObject {

	MARKET("market"),
	PRODUCT("product"),
	EXPLICIT("explicit");

	public final String reason;
}
