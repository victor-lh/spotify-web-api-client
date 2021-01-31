package com.victorlh.spotify.apiclient.services.search.models;

import com.neovisionaries.i18n.CountryCode;
import com.victorlh.spotify.apiclient.models.enums.SpotifyType;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class SearchRequest {

	//TODO - Modificar por un builder de query
	private final String query;
	@Singular
	private final List<SpotifyType> types;
	private final CountryCode market;
	private final Integer limit;
	private final Boolean includeExternal;
}
