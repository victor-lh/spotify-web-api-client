package com.victorlh.spotify.apiclient.services.tracks.models;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class MultipleTracksRequest {

	@Singular
	private final List<String> ids;
	private final CountryCode market;

}
