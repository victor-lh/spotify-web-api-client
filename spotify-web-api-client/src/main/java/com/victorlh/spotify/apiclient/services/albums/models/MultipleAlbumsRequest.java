package com.victorlh.spotify.apiclient.services.albums.models;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
public class MultipleAlbumsRequest {

	@NonNull
	@Singular
	private final List<String> ids;
	private final CountryCode market;
}
