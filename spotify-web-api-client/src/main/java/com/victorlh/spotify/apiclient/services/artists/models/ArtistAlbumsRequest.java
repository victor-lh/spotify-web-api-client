package com.victorlh.spotify.apiclient.services.artists.models;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
public class ArtistAlbumsRequest {

	public enum AlbumTypes {
		album, single, appears_on, compilation;
	}

	@NonNull
	private final String artistId;
	private final CountryCode market;
	private final Integer limit;
	@Singular
	private final List<AlbumTypes> types;
}
