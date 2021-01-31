package com.victorlh.spotify.apiclient.services.library.models;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GetUserSavedTracksRequest {

	private final Integer limit;
	private final CountryCode market;
}
