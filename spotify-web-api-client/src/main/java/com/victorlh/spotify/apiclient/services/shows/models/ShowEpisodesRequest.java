package com.victorlh.spotify.apiclient.services.shows.models;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ShowEpisodesRequest {

	private final String id;
	private final CountryCode market;
	private final Integer limit;

}
