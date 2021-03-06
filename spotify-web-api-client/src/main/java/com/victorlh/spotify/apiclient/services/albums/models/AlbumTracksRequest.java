package com.victorlh.spotify.apiclient.services.albums.models;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class AlbumTracksRequest {

	@NonNull
	private final String albumId;
	private final CountryCode market;
	private final Integer limit;
}
