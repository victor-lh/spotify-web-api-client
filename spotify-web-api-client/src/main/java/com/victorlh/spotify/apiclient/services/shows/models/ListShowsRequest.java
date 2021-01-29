package com.victorlh.spotify.apiclient.services.shows.models;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class ListShowsRequest {

	@Singular
	private final List<String> ids;
	private final CountryCode market;

}
