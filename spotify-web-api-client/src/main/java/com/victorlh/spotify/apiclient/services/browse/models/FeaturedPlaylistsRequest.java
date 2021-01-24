package com.victorlh.spotify.apiclient.services.browse.models;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class FeaturedPlaylistsRequest {

	private final CountryCode country;
	private final String locale;
	private final Date timestamp;
	private final Integer limit;

}
