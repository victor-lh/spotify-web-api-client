package com.victorlh.spotify.apiclient.services.artists.models;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class ArtistTopTracksRequest {

	@NonNull
	private final String artistId;
	private final CountryCode market;
}
