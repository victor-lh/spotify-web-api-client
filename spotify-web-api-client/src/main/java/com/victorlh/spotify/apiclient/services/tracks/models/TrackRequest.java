package com.victorlh.spotify.apiclient.services.tracks.models;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class TrackRequest {

	private final String id;
	private final CountryCode market;

}
